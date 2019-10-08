package com.example.superclass.ioc;

import java.lang.reflect.Method;

public class InjectUtils {

    public static void inject(Object context){
        injectLayout(context);
        injectView(context);
        injectClick(context);
    }

    private static void injectLayout(Object context) {
        int layoutId=0;
        Class<?> aClass = context.getClass();
        ContentView annotation  = aClass.getAnnotation(ContentView.class);
        if (annotation!=null){
            int value = annotation.value();

            try {
                Method setContentView = context.getClass().getMethod("setContentView", int.class);
                setContentView.invoke(aClass,value);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void injectView(Object context) {
    }

    private static void injectClick(Object context) {
    }
}
