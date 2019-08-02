package com.example.superclass.reinforce;

import android.app.Application;
import android.content.Context;

public class ProxyApplication  extends Application {
    /**
     * ActivityThread创建app之后调用第一个方法
     *
     * 进行解密操作 把数据交给android 加载
     */

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
