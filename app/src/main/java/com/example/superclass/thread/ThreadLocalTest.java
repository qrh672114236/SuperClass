package com.example.superclass.thread;

public class ThreadLocalTest {

    public void test(){

        //本地线程 主线程
        final ThreadLocal<String> threadLocal=new ThreadLocal<String>(){

            @Override
            protected String initialValue() {
                return "初始化数据";
            }
        };

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //获取的是当前thread线程里面的局部变量 ：（由于第一次没有 所以获取的initialValue的值）
                String value= threadLocal.get();

                threadLocal.set("当前thread的局部变量");

            }
        });
        thread.start();

        //value="初始化数据";
        String value=threadLocal.get();
        //用完清理当前线程的局部变量
        threadLocal.remove();
    }
}
