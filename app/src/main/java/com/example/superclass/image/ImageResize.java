package com.example.superclass.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//图片尺寸 缩放
public class ImageResize {
    public static Bitmap resizeBitmap(Context context, int id, int maxW, int maxH, boolean hasAlpha,Bitmap reusable) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        //需要拿得到系统处理的信息  比如解码出宽高,....
        options.inJustDecodeBounds = true;
        //我们把原来的解码参数改了再去生成bitmap
        BitmapFactory.decodeResource(resources, id, options);
        //取到宽高
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calcuteInSampleSize(w, h, maxW, maxH);

        if(!hasAlpha){
            options.inPreferredConfig=Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds=false;
        //易变功能
        options.inMutable=true;
        options.inBitmap=reusable;

        return BitmapFactory.decodeResource(resources,id,options);


    }

    //返回结果是原来解码的图片的大小  是我们需要的大小的   最接近2的几次方倍
    private static int calcuteInSampleSize(int w, int h, int maxW, int maxH) {
        int inSampleSize = 1;
        if (w > maxW && h > maxH) {
            inSampleSize = 2;
            while (w / inSampleSize > maxW && h / inSampleSize > maxH){
                inSampleSize*=2;
            }
        }
        inSampleSize/=2;
        return inSampleSize;
    }
}








