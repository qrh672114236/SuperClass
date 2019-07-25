package com.example.superclass.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

/**
 * 内存优化
 *工具：1.run->profile   2.MAT 3.快照转换工具 tools->sdk->plaform->tools hprof-conv.exe
 *
 * @内存抖动（大量开辟内存->GC）
 *         a.大量String字符串拼接
 *         b.WebView ->独立开辟进程运行 (process)  通讯（binder  aidl）
 *
 *（1）软引用（SoftReference）  内存不足回收
 * （2）弱引用（WeakReference）   ** GC来了就会回收
 *  （3）虚引用（PhantomReference 跟踪GC
 */
public class MemoryOptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_opt);


    }
}
/**
 * @内存泄露
 * 编码习惯
 *         a.数据类型：尽量不要用占更大空间的类型
 *
 *         b.循环尽量用foreach 少用 iterator （自动装箱 少用数组 链表 栈 数 ）
 *
 *         c.数据结构：数据千级以内可以使用 [sparse ArrayMap] 性能不如hashMap 但节约内存
 *
 *         d.尽量少用枚举  因为每个枚举都开辟内存
 *
 *         e.static final（打包在dex文件中直接使用 不用初始化） 的性能 大于static（需要初始化）
 *
 *         f.字符串拼接问题
 *
 *         g.不要重复申请内存 ： （1）重复new对象   （2）不要再onMeasure    onLayout onDraw 中刷新UI
 *
 *         h.避免GC回收要用到的对象->（对象池+LUR）
 *
 *         i.Activity组件泄露：（1）非业务需要尽量使用application 上下文
 *                             (2) 和Activity有关的对象不要static （button）
 *                            （3）非静态内部类和匿名内部类不要持有activity -> weakReference
 *                             (4) 单利 activity持有 -> weakReference
 *                            （5） handler中Looper是根据Application的生命周期走
 *          j.尽量使用intentService 防止服务不能杀死 导致内存泄露
 *
 */
