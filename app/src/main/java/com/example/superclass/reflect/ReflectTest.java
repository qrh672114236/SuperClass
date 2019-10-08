package com.example.superclass.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public void Test() throws Exception {
        //获取class

        Class a1=AClass.class;
        Class a2=Class.forName("com.example.superclass.reflect.AClass");

        AClass a3= (AClass) a2.newInstance();

        //获取类的构造方法
        
        String className="com.example.superclass.reflect.AClass";
        
        Class<AClass> aclzz= (Class<AClass>) Class.forName(className);
        //取出全部构造方法
        Constructor<?>[] constructors = aclzz.getConstructors();
        //取出某个构造方法
        Constructor<AClass> constructor = aclzz.getConstructor(Integer.class, String.class);
        //调用构造方法
        constructor.newInstance(20,"大傻逼");

        //获取类的所有方法（能获取从父类继承的所有方法，不能获取private方法）
        Method[] methods = aclzz.getMethods();

        //获取指定方法(传入方法名称 以及参数)
        Method setAge = aclzz.getDeclaredMethod("setAge", Integer.class);
        //调用函数
        Object obj=aclzz.newInstance();
        //如果是私有方法 更改是否可以执行
        setAge.setAccessible(true);
        setAge.invoke(obj,20);

        //获取所有字段 不包括父类
        Field[] declaredFields = aclzz.getDeclaredFields();

        //获取指定字段
        Field name = aclzz.getDeclaredField("name");
        //如果是私有属性 需要给权限
        name.setAccessible(true);

        //获取字段值
        AClass a=new AClass(20,"大傻逼");
        String o = (String) name.get(a);
        //设置值
        name.set(a,"二迷糊");
    }


}
