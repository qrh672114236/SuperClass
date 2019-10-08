package com.example.superclass.face;

public class Face {
    /**
     * 面经汇总
     */

/**
 * JAVA
 */
    //1. char如何存UTF-8字符 :
            // char 是2字节 ，utf-8可能是1~3个字节
            // Unicode是字符集  类似ASCII码 （char中存的是utf-16）
            // Unicode扩展字符（emoji）需要用一对char来表示


    //2. String可以有多长 :  字面量 -》栈（65534）    堆 new String （理论上Integer.MAX_VALUE 但要留一些给header信息）
            // 字节码 utf8_info 存储
            // 最多 0~65535个字节 （实际65534）

    //3. java的匿名内部类有哪些限制
            //异步耗时操作 可能持有Activity 导致内存泄漏 （引用外部类实例 ）

    //4. 怎样理解java的方法分派
            //多态最后输出的问题 ：输出 new的对象中的方法

    //5.java泛型实现机制
            //泛型编译后会被擦除 所有类型都会变成T  不能用作方法重载
            //静态方法无法引用类泛型参数
            //签名信息 ：getGennericReturnType 反射获取类型


    //6. 线程相关：
             //停止一个线程 ：线程停止方法已经废弃 （暂停后如果持有锁 阻塞线程会一直阻塞）
             //中断方式 ： Interrupt     (循环操作 不支持) 底层boolean 加个锁
             //循环中断 ：
                            //interrupted() ：获取当前中断状态 获取一次之后会 清空
                            //isInterrupted（） ：获取中断状态 不会清空
//
//            for(int i=0;i<10000;i++){
//                if (intterrupted){
//                    break;
//                }
//            }

               //线程内部设置 volatile boolean isStopped=false
               //外部 thread.isStopped=true


        //线程安全

                //可变资源 （内存）线程间共享 -->进程不存在（内存独享）
                //threadLocal:(创建自己的资源副本 )
                        //void set(Object value)设置当前线程的线程局部变量的值。
                        //public Object get()该方法返回当前线程所对应的线程局部变量。
                        //public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
                        //protected  Object initialValue()返回该线程局部变量的初始值
                //final volatile 禁止重排序 保证可见性

        //ConcurrentHashMap线程安全
        //AtomicReference  AtomicReferenceFiledUpdater 区别

        //kotlin协程



        //CPU架构适配:apk大小优化
                //云端下载so库
                //so体积优化 ：
                     //1.默认隐藏所有符号，只公开必要的 -fvisibility=hidden
                     // 2.禁用 C++Exception&RTTI  -fno-exceptions-fno-rtti
                     // 3.不要使用iostream 优先使用Android Log
                     // 4.使用gc-section 去除无用代码
                //分包打包


        //java Native与 Native 绑定
                //静态绑定：通过命名规则映射
                //动态绑定：通过JNI函数注册

        //native 数据传递 java




}
