package com.example.superclass.aop;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.superclass.R;
import com.example.superclass.aop.annotation.BehaviorTrace;
import com.example.superclass.aop.annotation.UserInfoBehaviorTrace;
import com.example.superclass.aop.proxy.ILogin;
import com.example.superclass.aop.proxy.MyHandler;

import java.lang.reflect.Proxy;
import java.util.Random;

public class AopJavaActivity extends AppCompatActivity  implements ILogin {

    private ILogin proxyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_java);

        //动态代 理
        proxyLogin=(ILogin) Proxy.newProxyInstance(this.getClassLoader(),new Class[]{ILogin.class},new MyHandler(this));


        findViewById(R.id.shake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosomething();
            }
        });




        findViewById(R.id.taobao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proxyLogin.toLogin();
            }
        });





    }
    @UserInfoBehaviorTrace("摇一摇")
    @BehaviorTrace("摇一摇")
    private void dosomething() {
        SystemClock.sleep(new Random().nextInt(2000));
    }

    @Override
    public void toLogin() {
        //正常登录 判断的业务逻辑 在MyHandler
    }
}
