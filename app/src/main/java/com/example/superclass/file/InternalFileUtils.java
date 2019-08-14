package com.example.superclass.file;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 内部存储 internal storage
 */
public class InternalFileUtils {
    String TAG = "File";



    /**
     * 文件文字写入
     */
    public void writeFile(Context context, String fileContent, String fileName) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历创建文件
     *
     * @param context
     */
    public void checkAllFile(Context context) {
        String[] files = context.fileList();
        for (String file : files) {
            Log.e(TAG, "file is " + file);
        }
    }

    /**
     * 删除文件
     */
    public void  deleteFile(Context context,String fileName){
        if(context.deleteFile(fileName)) {
            Log.e(TAG, "delete file "+ fileName +"sucessfully");
        } else {
            Log.e(TAG, "failed to deletefile " + fileName);
        }
    }

    /**
     * 创建目录
     */
    public  void mkDir(Context context ,String dirName){
        File workDir = context.getDir(dirName, Context.MODE_PRIVATE);
        Log.e(TAG, "workdir "+ workDir.getAbsolutePath());
    }


}
