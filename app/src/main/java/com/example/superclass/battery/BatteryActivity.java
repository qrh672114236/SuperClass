package com.example.superclass.battery;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.superclass.R;

/**
 * 电池优化   BatteryHistory
 *
 * 工具 docker battery cmd: docker run -d -p 9999:9999 bhaavan/battery-historian  Replace Google CDN    http://localhost:9999/
 *
 *a. 先重置 ： adb shell dumpsys batterystats --reset
 *
 *b. 获取完整的 wakelock 信息：adb shell dumpsys batterystats --enable full-wake-history
 *
 *c.拔掉USB（让设备不处于充电状态），等待一段时间
 *      获得报告:
 *         >7.0
 *          	adb bugreport bugreport.zip  (adb bugreport >D:/bugreport.txt)
 * 	       <=6.0
 * 	            adb bugreport > bugreport.txt
 *
 *
 *git:      运行 go run cmd/battery-historian/battery-historian.go
 *
 *尽量少用 weakLock 使用alarm的形式
 *
 * PARTIAL_WAKE_LOCK: 灭屏，关闭键盘背光的情况下，CPU依然保持运行。
 *
 * PROXIMITY_SCREEN_OFF_WAKE_LOCK： 基于距离感应器熄灭屏幕。最典型的运用场景是我们贴近耳朵打电话时，屏幕会自动熄灭。
 *
 * 使用jobService
 *
 */
public class BatteryActivity extends AppCompatActivity {

    private PowerManager.WakeLock locationLock;
    private Intent alarmIntent;
    private String tag="s";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        //使用WeakLock
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        //判断是否支持
        pm.isWakeLockLevelSupported(PowerManager.PARTIAL_WAKE_LOCK);
       // 只唤醒cpu
        locationLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,tag);
        locationLock.acquire();

        //使用 alarm
        alarmKeep();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放
//        LocationManager.getInstance().destoryLocation();
        //注销广播接收者
        unregisterReceiver(alarmReceiver);
        //释放weakLock
//        if (null != locationLock) {
//            locationLock.release();
//        }
    }

    private void alarmKeep() {
        alarmIntent = new Intent();
        alarmIntent.setAction("LOCATION");
        //创建延迟意图
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        //获得闹钟管理器
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //动态注册广播接受者
        IntentFilter filter = new IntentFilter();
        filter.addAction("LOCATION");
        registerReceiver(alarmReceiver,filter);
        //设置一个 每隔 5s 发送一个广播
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                5_000,broadcast);
    }

    BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(),"LOCATION")){
//                LocationManager.getInstance().startLocation(LocationService.this);
            }
        }
    };
}
