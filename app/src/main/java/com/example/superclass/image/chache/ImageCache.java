package com.example.superclass.image.chache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.LruCache;

import com.example.superclass.BuildConfig;
import com.example.superclass.image.chache.disk.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 图片缓存 ： 内存->复用池->磁盘->网络
 */
public class ImageCache {
    private static ImageCache instance;
    private Context context;
    private LruCache<String, Bitmap> memoryCache;
    private DiskLruCache diskLruCache;

    BitmapFactory.Options options = new BitmapFactory.Options();

    /**
     * 定义一个复用池
     */
    public static Set<WeakReference<Bitmap>> reuseablePool;

    //二级缓存引用队列
    ReferenceQueue referenceQueue;
    Thread clearReferenceQueue;
    boolean shutDown;

    private ReferenceQueue<Bitmap> getReferenceQueue(){
        if (null==referenceQueue){
            //当弱引用需要被回收的时候，会进入到这个队列中
            referenceQueue=new ReferenceQueue<Bitmap>();
            //单开个线程 ，去扫描引用队列中 GC扫到的内容 ，交到native层去释放
            clearReferenceQueue=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!shutDown){
                        try {
                            //remove 是阻塞线程
                            Reference<Bitmap> reference=referenceQueue.remove();
                            Bitmap bitmap=reference.get();
                            if (null!=bitmap&&!bitmap.isRecycled()){
                                bitmap.recycle();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            clearReferenceQueue.start();
        }
        return referenceQueue;
    };

    public static ImageCache getInstance() {
        if (null == instance) {
            synchronized (ImageCache.class) {
                if (null == instance) {
                    instance = new ImageCache();
                }
            }
        }
        return instance;
    }

    public void init(Context context, String dir) {
        this.context = context.getApplicationContext();

        reuseablePool= Collections.synchronizedSet(new HashSet<WeakReference<Bitmap>>());

        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获得程序最大可使用内存 M
        int memoryClass = manager.getMemoryClass();


        //标示能够缓存的内存最大值 单位是byte
        memoryCache = new LruCache<String, Bitmap>(memoryClass / 8 * 1024 * 1024) {
            /**
             * @return value 占用的内存大小
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //19之前 复用是必须同等大小，才能复用 之后 复用图片 大小小于原图 也可以复用
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
                    return value.getAllocationByteCount();
                }
                return value.getByteCount();
            }

            /**
             * 当lru满了 ，bitmap从lru中移除对象 会回调
             */
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                if (oldValue.isMutable()) {//如果是设置成能服用的内存块，拉倒java管理
                    //3.0以下 Bitmap native
                    //3.0-8.0 java
                    //8.0开始 native
                    //把图片放到复用池
                    reuseablePool.add(new WeakReference<Bitmap>(oldValue,getReferenceQueue()));
                } else {
                    //oldValue 就是移除的对象
                    oldValue.recycle();
                }

            }
        };
          //valueCount 表示一个key对应valueCount个文件
        try {
            diskLruCache=DiskLruCache.open(new File(dir), BuildConfig.VERSION_CODE,1 ,10*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        getReferenceQueue();
    }

    public void putBitmapToMemory(String key, Bitmap bitmap) {
        memoryCache.put(key, bitmap);
    }

    public Bitmap getBitmapFromMemory(String key) {
        return memoryCache.get(key);
    }

    public void clearMemoryCache() {
        memoryCache.evictAll();
    }
    //复用池处理
    public Bitmap getReuseable(int w,int h,int inSampleSize){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.HONEYCOMB){
            return null;
        }
        Bitmap reuseable=null;
        Iterator<WeakReference<Bitmap>> iterator = reuseablePool.iterator();
        while (iterator.hasNext()){
            Bitmap bitmap=iterator.next().get();
            if (null!=bitmap){
                if (checkInBitmap(bitmap,w,h,inSampleSize)){
                    reuseable=bitmap;
                    iterator.remove();
                    break;
                }else{
                    iterator.remove();
                }
            }
        }
        return reuseable;
    }

    private boolean checkInBitmap(Bitmap bitmap, int w, int h, int inSampleSize) {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT){
            return bitmap.getWidth()==w && bitmap.getHeight()==h &&inSampleSize==1;
        }
        if (inSampleSize>1){
            w/=inSampleSize;
            h/=inSampleSize;
        }
        int byteCount=w*h*getPixelsCount(bitmap.getConfig());

        return byteCount<=bitmap.getAllocationByteCount();
    }

    private int getPixelsCount(Bitmap.Config config) {
        if (config==Bitmap.Config.ARGB_8888){
            return 4;
        }
        return 2;
    }

    /**
     * 磁盘缓存的处理
     */
    //磁盘存储
    public void putBitMapToDisk(String key,Bitmap bitmap){
        DiskLruCache.Snapshot snapshot=null;
        OutputStream os=null;

        try {
            snapshot=diskLruCache.get(key);
            //如果缓存中有这个文件 不处理
            if (null==snapshot){
                //如果没有 生成文件
                DiskLruCache.Editor editor=diskLruCache.edit(key);
                if (null!=editor){
                    os=editor.newOutputStream(0);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,50,os);
                    editor.commit();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=snapshot){
                snapshot.close();
            }
            if (null!=os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //磁盘读取
    public Bitmap getBitmapFromDisk(String key,Bitmap reuseable){
        DiskLruCache.Snapshot snapshot=null;
        Bitmap bitmap=null;
        try {
            snapshot=diskLruCache.get(key);
            if (null==snapshot){
                return null;
            }
            InputStream in=snapshot.getInputStream(0);
            //解码图片
            options.inMutable=true;
            options.inBitmap=reuseable;
            bitmap=BitmapFactory.decodeStream(in,null,options);
            if (null!=bitmap){
                memoryCache.put(key,bitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=snapshot){
                snapshot.close();
            }
        }
        return bitmap;

    }
}
