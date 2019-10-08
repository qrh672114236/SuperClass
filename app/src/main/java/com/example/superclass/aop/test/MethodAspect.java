package com.example.superclass.aop.test;


import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodAspect {
    private static final String TAG = "ConstructorAspect";
    //普通方法
    @Pointcut("call(* com.example.superclass.aop.test.Animal.fly(..))")
    public void callMethod() {}

    //构造方法
    @Before("execution(android.aspectjdemo.animal.Animal.new(..))")
    public void beforeConstructorExecution(JoinPoint joinPoint) {
        Log.e(TAG, "before->" + joinPoint.getThis().toString() + "#" + joinPoint.getSignature().getName());
    }

    //带返回值的方法
    @Around("get(int android.aspectjdemo.animal.Animal.age)")
    public int aroundFieldGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行原代码
        Object obj = joinPoint.proceed();
        int age = Integer.parseInt(obj.toString());
        Log.e(TAG, "age: " + age);
        return 100;
    }

    //带参数的方法
    @Around("set(int android.aspectjdemo.animal.Animal.age)")
    public void aroundFieldSet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "around->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
    }

    //static 方法
    @Before("staticinitialization(android.aspectjdemo.animal.Animal)")
    public void beforeStaticBlock(JoinPoint joinPoint) {
        Log.d(TAG, "beforeStaticBlock: ");
    }

    //捕获异常
    /**
     * handler
     * 不支持@After、@Around
     */
    @Before("handler(java.lang.ArithmeticException)")
    public void handler() {
        Log.e(TAG, "handler");
    }

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
    }




}
