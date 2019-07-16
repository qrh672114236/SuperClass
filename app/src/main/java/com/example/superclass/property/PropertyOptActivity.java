package com.example.superclass.property;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

/**
 * 性能优化
 *
 *
 * @1.开机优化
 *      调试 ：（logcat:Displayed-》No filters 会显示耗时时间）
 *      a.伪优化：（1）主题背景设置图片 在style windowBackground
 *
 *      b.application优化：（1）使用懒加载 使用时才初始化  (2) Thread *(API不能存在handler ，且对异步要求不高的)
 *
 *
 *
 * @2.绘制优化
 *      调试：开发者选项->profileGPD rending
 *      （工具 Android/sdk/tools/bin/ui/viewwer.bat   Android/sdk/tools/monitor.bat）
 *      a.减少xml转换对象时间
 *
 *      b.减少重复绘制 （背景重叠）=>（1）减少背景过多设置
 *                                 (2) 主题  <item name="android:windowBackground">null</item>
 *      c.减少复杂布局=>merge include clip
 *
 */
public class PropertyOptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //（1）减少背景过多设置

        getWindow().setBackgroundDrawable(null);

        setContentView(R.layout.activity_property_opt);


    }
}
