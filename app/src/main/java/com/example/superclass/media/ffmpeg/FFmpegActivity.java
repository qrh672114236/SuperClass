package com.example.superclass.media.ffmpeg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.superclass.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 音视频开发 ffmpeg
 */
public class FFmpegActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_1 = 1;
    private static final int MY_PERMISSIONS_REQUEST_2 = 2;

    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();

    static {
        System.loadLibrary("ffmpeg");
    }

    SurfaceView surfaceView;
    Fplayer fplayer;
    VideoView video_view;
    MediaController mMediaController;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPermissionList.clear();

        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(this, "已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST_1);
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_ffmpeg);
        surfaceView = findViewById(R.id.surfaceView);
        fplayer = new Fplayer();
        video_view = findViewById(R.id.video_view);
        version = findViewById(R.id.version);
        mMediaController = new MediaController(this);
        fplayer.setSurfaceView(surfaceView);

    }

    public void open(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "input.mp4");
        fplayer.start("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");
//        Uri uri = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Log.e("aaa","走了这里");
//            uri = FileProvider.getUriForFile(this, "com.example.superclass.fileProvider",file);
//        } else {
//            uri = Uri.fromFile(new File(file.getAbsolutePath()));
//        }
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            try {
//                if (!file.exists()) {
//                    return;
//                }
//
//                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/output.mp4");
//                Log.e("file==", file.getAbsolutePath());
//                video_view.setMediaController(new MediaController(this));
//                video_view.setVideoURI(uri);
//                video_view.start();
//                video_view.requestFocus();
//                version.setText(fplayer.test());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        //        fplayer.start(file.getAbsolutePath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_1) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}


