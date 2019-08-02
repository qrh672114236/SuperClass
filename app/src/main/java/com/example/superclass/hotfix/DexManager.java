package com.example.superclass.hotfix;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class DexManager {

    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }

    public void load(File file){
        try {
            DexFile dexFile=DexFile.loadDex(file.getAbsolutePath(),new File(context.getCacheDir(),"opt").getAbsolutePath(),Context.MODE_PRIVATE);
            //当前dex里面的class 类名集合
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()){
                String clazzName=entries.nextElement();
                Class aClass  = dexFile.loadClass(clazzName, context.getClassLoader());

                if (aClass!=null){
                     fixClazz(aClass);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fixClazz(Class aClass) {
        Method[] methods=aClass.getMethods();
        for (Method method : methods) {
            Replace replace=method.getAnnotation(Replace.class);

            if (replace==null){
                continue;
            }
            //拿到从网络下载的 rithtMethod  本地的bug方法
            String clazzName=replace.clazz();
            String mothod=replace.method();

            try {
                Class<?> bugClass = Class.forName(clazzName);
                Method bugMethod = bugClass.getDeclaredMethod(mothod, method.getParameterTypes());

                replace(bugMethod,method);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public  native static void replace(Method bugMethod, Method method) ;


}
