package com.example.superclass.hotfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

public class HotFixActivity extends AppCompatActivity {

    /**
     * 1.方法区 ：存储class 文件的类型信息  及 类静态变量
     *
     * 2.堆内存 ：存放数组 和 创建的对象 （new ）
     *
     * 3.java栈区：每个线程分配一个java栈 用来存放 方法中的局部变量 操作数 异常数据
     *            当执行方法时，jvm会根据方法的字节码组建栈帧，并将该栈帧压入java栈中
     *            方法执行完毕后会弹出栈帧并释放
     *
     *Andfix : native artMethod 获取两个方法  相互替换
     *         （方法区）int符号变量-> artMethod类型的方法表 -> java栈 栈帧压栈
     *
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_hot_fix);


    }
}
