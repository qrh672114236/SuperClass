package com.example.superclass.battery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

/**
 * 主动获得充电状态和网络状态
 */
public class Battery {

    /**
     * 请求设置白名单6.0以后
     */
    public static void addWhile(Activity activity){
        PowerManager  powerManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        //应用是否在白名单
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (!powerManager.isIgnoringBatteryOptimizations(activity.getPackageName()));
            //方法1.弹出菜单 选
//            Intent intent=new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//            activity.startActivity(intent);
            //方法2
            Intent intent=new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:"+activity.getPackageName()));
            activity.startActivity(intent);


        }
    }

    /**
     * 是否在充电
     */
    public static boolean isPlugged(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intent = context.registerReceiver(null, filter);
        int isPlugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);


        boolean acPlugged = isPlugged == BatteryManager.BATTERY_PLUGGED_AC;
        boolean usbPlugged = isPlugged == BatteryManager.BATTERY_PLUGGED_USB;
        boolean writeliessPlugged = isPlugged == BatteryManager.BATTERY_PLUGGED_WIRELESS;

        return acPlugged || usbPlugged || writeliessPlugged;
    }

    /**
     * 是否在使用wifi
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return null != activeNetworkInfo && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}

