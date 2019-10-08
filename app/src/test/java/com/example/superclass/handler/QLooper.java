package com.example.superclass.handler;

public class QLooper {

    static final ThreadLocal<QLooper> sThreadLocal = new ThreadLocal<QLooper>();

    final  QMessageQueue mQueue;

    private QLooper() {
        mQueue=new QMessageQueue();

    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("只有一个Looper 可以创建线程");
        }
        sThreadLocal.set(new QLooper());
    }

    public static QLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        //从全局ThreadLocalMap中获取唯一 loop对象
        QLooper looper=myLooper();
        QMessageQueue queue= looper.mQueue;

        while (true){
          QMessage msg= queue.next();
          if (msg!=null){
              msg.target.dispatchMessage(msg);
          }
        }
    }
}
