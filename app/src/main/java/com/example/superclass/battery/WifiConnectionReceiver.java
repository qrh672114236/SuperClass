package com.example.superclass.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 被动获取是否是wifi
 */
public class WifiConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Battery.isWifi(context)) {
            Log.i("qq","当前正在使用winfi");
        } else {
            Log.i("qq","当前没有使用winfi");
        }
    }
}
