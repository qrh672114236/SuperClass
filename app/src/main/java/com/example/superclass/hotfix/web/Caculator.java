package com.example.superclass.hotfix.web;

import com.example.superclass.hotfix.Replace;

public class Caculator {
    //修复类
    @Replace(clazz = "com.example.superclass.hotfix.Caculator",method = "calulator")
    public int calulator(){

       return 10;
    }
}
