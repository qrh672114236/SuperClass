package com.example.superclass.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadClass {
    /**
     * 线程
     */
    private static class UseThread extends  Thread{
        @Override
        public void run() {
            super.run();
        }
    }

    private static class UseRun implements  Runnable{

        @Override
        public void run() {
            //runnable里面的终止判断
            Thread.currentThread().isInterrupted();

        }
    }


    private static class UseCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "这是call任务";
        }
    }


    public void test() {
        //thread
        UseThread thread=new UseThread();
        thread.start();

        //runnable
        UseRun run=new UseRun();
        new Thread(run).start();
        
        //call
        UseCall call = new UseCall();
        FutureTask futureTask = new FutureTask(call);
        new Thread(futureTask).start();
        try {
             String result= (String) futureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 锁
     */
    //对象锁(三种)
    private int count=0;
    private Object obj=new Object();

    private synchronized  void addCount1(){
        count++;
    }
    private  void addCount2(){
        synchronized (obj){
            count++;
        }
    }
    private void addCount3(){
        synchronized (this){
            count++;
        }
    }
    //类锁
    public static synchronized  void synClass(){ }

    private static Object obj2=new Object();

    public void synClass2(){
        synchronized (obj2){

        }
    }


}
