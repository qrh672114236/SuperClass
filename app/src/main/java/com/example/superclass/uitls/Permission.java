package com.example.superclass.uitls;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private static final int MY_PERMISSIONS_SINGLE = 1;
    private static final int MY_PERMISSIONS_MULTIPLE = 2;
    String[] permissions = new String[]{
            Manifest.permission.READ_CALENDAR,              //读取用户日历
            Manifest.permission.WRITE_CALENDAR,             //写入用户日历
            Manifest.permission.CAMERA,                     //相机
            Manifest.permission.READ_CONTACTS,              //读取用户联系人
            Manifest.permission.WRITE_CONTACTS,             //写入用户联系人
            Manifest.permission.GET_ACCOUNTS,               //访问账户列表在AccountsService
            Manifest.permission.ACCESS_FINE_LOCATION,       //允许访问精良位置
            Manifest.permission.ACCESS_COARSE_LOCATION,     //访问callId或wifi的粗略位置
            Manifest.permission.RECORD_AUDIO,               //允许录制音频
            Manifest.permission.READ_PHONE_STATE,           //获取电话状态
            Manifest.permission.CALL_PHONE,                 //允许打电话
            Manifest.permission.READ_CALL_LOG,              //允许查看电话拨打日志
            Manifest.permission.ADD_VOICEMAIL,              //语音邮件
            Manifest.permission.USE_SIP,                    //SIP服务
            Manifest.permission.PROCESS_OUTGOING_CALLS,     //修改或终止即将离任的电话
            Manifest.permission.BODY_SENSORS,               //传感器
            Manifest.permission.SEND_SMS,                   //信息
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,            //允许一个应用程序监视传入WAP推送消息。
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.READ_EXTERNAL_STORAGE,       //外部存储器读取数据
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();

    public void checkPermission(Activity context, int permissionIndex) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context.getApplicationContext(), permissions[permissionIndex]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{permissions[permissionIndex]}, MY_PERMISSIONS_SINGLE);
            }
        }
    }

    public void checkPermissions() {
        mPermissionList.clear();
    }


}

class eP {
    public enum ePermissions{
        READ_CALENDAR,              //读取用户日历
        WRITE_CALENDAR,             //写入用户日历
        CAMERA,                     //相机
        READ_CONTACTS,              //读取用户联系人
        WRITE_CONTACTS,             //写入用户联系人
        GET_ACCOUNTS,               //访问账户列表在AccountsService
        ACCESS_FINE_LOCATION,       //允许访问精良位置
        ACCESS_COARSE_LOCATION,     //访问callId或wifi的粗略位置
        RECORD_AUDIO,               //允许录制音频
        READ_PHONE_STATE,           //获取电话状态
        CALL_PHONE,                 //允许打电话
        READ_CALL_LOG,              //允许查看电话拨打日志
        ADD_VOICEMAIL,              //语音邮件
        USE_SIP,                    //SIP服务
        PROCESS_OUTGOING_CALLS,     //修改或终止即将离任的电话
        BODY_SENSORS,               //传感器
        SEND_SMS,                   //信息
        RECEIVE_SMS,
        READ_SMS,
        RECEIVE_WAP_PUSH,            //允许一个应用程序监视传入WAP推送消息。
        RECEIVE_MMS,
        READ_EXTERNAL_STORAGE,       //外部存储器读取数据
        WRITE_EXTERNAL_STORAGE
    }

}