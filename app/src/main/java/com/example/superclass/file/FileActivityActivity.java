package com.example.superclass.file;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

/**
 * 文件存储
 *
 * 外部存储 external Storage
 *
 *
 *
 *
 *
 * 内部存储 internal storage
 *
 *      (默认只能app内部访问：sp SQLite)
 *      1.获取本目录 ： getFilesDir()
 *
 *      2.data/data/包名/cache  getCacheDir().getAbsolutePath();
 *
 *
 *
 *
 *
 */
public class FileActivityActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_activity);


        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);


    }




}
