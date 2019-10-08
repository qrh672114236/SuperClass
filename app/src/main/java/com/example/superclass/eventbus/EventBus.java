package com.example.superclass.eventbus;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class EventBus {
    private static EventBus instance=new EventBus();

    private Map<Object, List<SubscribleMethod>> cacheMap;

    private Handler handler;

    //线程池
    private ExecutorService executorService;

    public static EventBus getDefault(){
        return instance;
    }


    private EventBus(){
        this.cacheMap=new HashMap<>();
        handler=new Handler();
    }


    public void register(Object subscriber){
        Class<?> aClass = subscriber.getClass();
        List<SubscribleMethod> subscribleMethods = cacheMap.get(subscriber);
        if(subscribleMethods==null){
            subscribleMethods=getSubscribleMethod(subscriber);
            cacheMap.put(subscriber,subscribleMethods);
        }

    }
    //遍历能够接受事件的方法
    private List<SubscribleMethod> getSubscribleMethod(Object subscriber) {
        List<SubscribleMethod> list=new ArrayList<>();

        Class<?> aClass = subscriber.getClass();
        //可能是父类订阅 所以要往上找
        while (aClass!=null){
            //判断父类是在哪个包下 如果是系统的就不需要
            String name = aClass.getName();
            if(name.startsWith("java")||name.startsWith("javax")||name.startsWith("android")||name.startsWith("androidx")){break;}

            Method[] declaredMethods = aClass.getDeclaredMethods();

            for (Method declaredMethod : declaredMethods) {
                Subsribe annotation = declaredMethod.getAnnotation(Subsribe.class);

                if (annotation==null){
                    continue;
                }
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

                //判断有几个参数
                if (parameterTypes.length!=1){
                    throw new RuntimeException("只能接收一个参数");
                }

                ThreadMode threadMode = annotation.threadMode();
                SubscribleMethod subscribleMethod = new SubscribleMethod(declaredMethod, threadMode, parameterTypes[0]);
                list.add(subscribleMethod);
            }
            aClass=aClass.getSuperclass();
        }
            return list;
    }


    public void unregister(Object subscriber){
        Class<?> aClass = subscriber.getClass();
        List<SubscribleMethod> list = cacheMap.get(subscriber);

        if(list!=null){
            cacheMap.remove(subscriber);
        }
    }

    public void post(final Object obj){
        Set<Object> set = cacheMap.keySet();

        Iterator<Object> iterator = set.iterator();

        while (iterator.hasNext()){
            final Object next = iterator.next();
            //拿到所有添加注解的方法
            List<SubscribleMethod> subscribleMethods = cacheMap.get(next);
            for (final SubscribleMethod subscribleMethod : subscribleMethods) {
                //判断这个方法是否应该接收事件
                if(subscribleMethod.getEventType().isAssignableFrom(obj.getClass())){
                    switch (subscribleMethod.getThreadMode()){
                        case MAIN:
                            //可以判断是主线程
                            if (Looper.myLooper()==Looper.getMainLooper()){
                                invoke(subscribleMethod,next,obj);
                            }else{
                                //子线程发送消息 主线程接收消息
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod,next,obj);
                                    }
                                });
                            }

                            break;
                        case ASYNC:
                            //接收方法在子线程
                            if (Looper.myLooper()==Looper.getMainLooper()){
                                    executorService.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            invoke(subscribleMethod,next,obj);
                                        }
                                    });
                            }else{
                                invoke(subscribleMethod,next,obj);
                            }

                            break;
                        case POSTING:
                            break;
                        case BACKGROUND:
                            break;
                        case MAIN_ORDERED:
                            break;

                    }
                    invoke(subscribleMethod,next,obj);

                }

            }
        }
    }

    private void invoke(SubscribleMethod subscribleMethod, Object next, Object obj) {
        Method method = subscribleMethod.getMethod();

        try {
            method.invoke(next,obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
