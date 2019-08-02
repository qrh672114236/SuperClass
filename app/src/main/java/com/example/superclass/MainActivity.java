 package com.example.superclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.superclass.gif.GifActivity;
import com.example.superclass.image.ImageOptActivity;

 public class MainActivity extends AppCompatActivity {

    /**
     * 这个程序负责记录
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,GifActivity.class));


    }

}
