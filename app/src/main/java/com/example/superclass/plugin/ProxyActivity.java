package com.example.superclass.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.pluginstand.InterfaceActivity;

import java.lang.reflect.Constructor;

public class ProxyActivity extends Activity {
    //需要加载的Activity的全类名
    private String className;
    private InterfaceActivity interfaceActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");

        try {
            Class<?> clazz = getClassLoader().loadClass(className);
            //获取构造方法
            Constructor constructor = clazz.getConstructor(new Class[]{});

            Object obj = constructor.newInstance(new Object[]{});

            interfaceActivity= (InterfaceActivity) obj;

            interfaceActivity.attach(this);
            //传递数据
//
//            Bundle bundle =new Bundle();
//            interfaceActivity.onCreate(bundle);
            interfaceActivity.onCreate(savedInstanceState);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //重写加载类
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.INSTANCE.getDexClassLoader();
    }
    //重写加载资源
    @Override
    public Resources getResources() {
        return PluginManager.INSTANCE.getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        interfaceActivity.onStart();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent m=new Intent(this,ProxyActivity.class);
        m.putExtra("className",className);
        super.startActivity(m);
    }

    @Override
    protected void onResume() {
        super.onResume();
        interfaceActivity.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interfaceActivity.onDestory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        interfaceActivity.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        interfaceActivity.onSaveInstance(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        interfaceActivity.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         interfaceActivity.onTouchEvent(event);
        return super.onTouchEvent(event);

    }
}
