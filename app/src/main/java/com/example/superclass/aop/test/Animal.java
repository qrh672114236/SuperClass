package com.example.superclass.aop.test;

import android.util.Log;

import org.aspectj.lang.annotation.Aspect;

public class Animal {

    private static final String TAG="Animal";

    private int age;


    static {

    }

    public void fly(){
        Log.e(TAG,this.toString()+"fly");
    }

    public  int getAge(){

        return  this.age;
    }

    public  void setAge(int age){
        this.age=age;
    }

    public void catchfun(){
        try {

        }catch (ArithmeticException e){

        }
    }


}


