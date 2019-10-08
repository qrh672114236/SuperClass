package com.example.superclass.media.ffmpeg.dongnao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.superclass.R;

public class DNFFmpegActivity extends AppCompatActivity {


    private DNPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnffmpeg);
        player=new DNPlayer();
        SurfaceView surfaceView=(SurfaceView) findViewById(R.id.surfaceView);
        player.setSurfaceView(surfaceView);
        player.setDataSource("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");
        player.setOnPrepareListener(new DNPlayer.onPrepareListener() {
            @Override
            public void onPrepare() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DNFFmpegActivity.this,"开始播放",Toast.LENGTH_SHORT).show();
                    }
                });
                player.start();
            }
        });


    }

    public void start(View view) {
        player.prepare();

    }


}
