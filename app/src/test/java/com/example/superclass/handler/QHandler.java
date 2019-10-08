package com.example.superclass.handler;


public class QHandler {
    private QLooper qLooper;

    private QMessageQueue messageQueue;

    public QHandler(){
        qLooper=QLooper.myLooper();
        if (qLooper==null){
            throw new RuntimeException("");
        }

        messageQueue=qLooper.mQueue;
    }

    public void handleMessage(QMessage msg){

    }

    public void sendMessage(QMessage msg) {
        //将消息放入消息队列
        enqueueMessage(msg);
    }

    private void enqueueMessage(QMessage msg) {
        //当前的handler
        msg.target=this;
        //将消息传入
        messageQueue.enqueueMessage(msg);


    }

    public void dispatchMessage(QMessage msg) {
        handleMessage(msg);

    }
}
