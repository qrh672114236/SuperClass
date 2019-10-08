package com.example.superclass.aop.proxy;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
    private Object target;
    private Context context;

    public MyHandler( Context context) {
        this.target = context;
        this.context = context;
    }

    /**
     * 拦截Object对象的所有方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Boolean spIsLogin=false;
        Object result=null;
        if (spIsLogin){
            result=method.invoke(target,args);
        }else{
            context.startActivity(new Intent(context,LoginActivity.class));
        }
        return result;
    }
}
