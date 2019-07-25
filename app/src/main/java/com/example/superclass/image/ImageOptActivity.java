package com.example.superclass.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;
import com.example.superclass.image.chache.ImageCache;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/***
 * 图片优化
 *
 * @图片压缩
 *          a.关闭透明通道
 *          b.使用libjpeg 压缩
 *          c.尺寸压缩
 *
 * @图片运行内存优化
 *
 * @长图优化
 */
public class ImageOptActivity extends AppCompatActivity {
    Bitmap inputBitmap=null;
//    static {
//        System.loadLibrary("native-lib");
//    }

    /**
     * 使用libjpeg压缩
     */
//    public native nativeCompress(Bitmap bitmap, int q, String path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_opt);
        //尺寸压缩
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        //使用libjpeg  （libturbojpeg.a）
//        nativeCompress(inputBitmap, 50, Environment.getExternalStorageDirectory() + "/girl2.jpg");

        //图片运行内存优化
//        Bitmap bitmap2=ImageResize.resizeBitmap(getApplicationContext(),R.mipmap.wyz_p,373,459,false);

        //三级缓存初始化
        ImageCache.getInstance().init(this,Environment.getExternalStorageDirectory() +"image");

        //长图优化
        BigView bigView=findViewById(R.id.big_view);
        InputStream is=null;

        try {
            is=getAssets().open("big.png");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    /**
     * 图片复用
     */
    public void pics(){
        BitmapFactory.Options options=new BitmapFactory.Options();
        //复用异变（复用内存）
        options.inMutable=true;

        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
        for (int i=0;i<100;i++){
            options.inBitmap=bitmap;
            bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round,options);

        }

    }


    /**
     * 压缩图片到制定文件
     *
     * @param bitmap 待压缩图片
     * @param format 压缩的格式
     * @param q      质量
     * @param path   文件地址
     */
    private void compress(Bitmap bitmap, Bitmap.CompressFormat format, int q, String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(format, q, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
