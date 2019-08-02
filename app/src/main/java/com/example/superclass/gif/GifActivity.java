package com.example.superclass.gif;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.superclass.R;

import java.io.File;
import java.io.InputStream;

/**
 * Gif加载
 *
 * JAVA(glide) :消耗内存
 *
 * c  ：不消耗内存 但增大安装包
 *
 */
public class GifActivity extends AppCompatActivity {
    Bitmap bitmap;

    ImageView imageView;

    GifHandler gifHandler;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int mNextFrame = gifHandler.updateFrame(bitmap);

            handler.sendEmptyMessageDelayed(1,mNextFrame);
            imageView.setImageBitmap(bitmap);
        }
    };


    public void ndkLoadGif(View view){
//        File file=new File(Environment.getExternalStorageDirectory(),"demo.gif");
         Environment.getExternalStorageDirectory();

//        gifHandler=new GifHandler(file.getPath());
//
//        int width=gifHandler.getWidth();
//        int height=gifHandler.getHeight();
//        bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
//
//        //下一帧 刷新
//        int mNextFrame = gifHandler.updateFrame(bitmap);
//        handler.sendEmptyMessageDelayed(1,mNextFrame);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        imageView=findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ndkLoadGif(v);
            }
        });
    }
}
