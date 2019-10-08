package com.example.superclass.handler;


import java.util.concurrent.ArrayBlockingQueue;

public class QMessageQueue {
    //阻塞队列
    ArrayBlockingQueue<Object> blockingDeque=new ArrayBlockingQueue<>(50);

    public void enqueueMessage(QMessage msg) {
        try {
            blockingDeque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public QMessage next() {
        try {
           return (QMessage) blockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
