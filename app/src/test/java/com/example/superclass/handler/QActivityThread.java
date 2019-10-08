package com.example.superclass.handler;

import org.junit.Test;

public class QActivityThread {
    @Test
    public void main(){
        //ThreadLocal 创建一个Looper
        QLooper.prepare();


        //创建Handler
        final QHandler handler=new QHandler(){
            @Override
            public void handleMessage(QMessage msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                QMessage msg=new QMessage();
                msg.what=1;
                msg.obj="这是一条消息";
                handler.sendMessage(msg);

            }
        }).start();

        //获取消息
        QLooper.loop();

    }
}
