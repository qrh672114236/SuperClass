package com.example.superclass.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

//Manifest 注册 action
public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if (TextUtils.equals(action,Intent.ACTION_POWER_CONNECTED)){
            //充电
        }else{
            //没充电
        }
    }
}
