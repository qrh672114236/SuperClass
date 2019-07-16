package com.example.superclass.uitls;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import java.io.File;

public class DebugUtil {
    //检测方法耗时
    public void test(Context context){
        File file =new File(context.getExternalFilesDir(null),"abc.trace");
        Log.i("trace", "onCreate: " + file.getAbsolutePath());
        Debug.startMethodTracing(file.getAbsolutePath());
        func();
        Debug.stopMethodTracing();
        //adb pull 取出耗时文件 托到AS分析
    }

    public void  func(){
        //dosomething
    }

}
