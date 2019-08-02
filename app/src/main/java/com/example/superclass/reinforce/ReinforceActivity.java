package com.example.superclass.reinforce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

/**
 * App加固
 *
 * 反编译工具：
 *
 * 1.apktool->主要用于资源文件的获取  :  java -jar apktool_2.4.0.jar d app-debug.apk -o dir
 *
 * 2.dex2jar->将apk中的dex文件编译成jar文件 : d2j-dex2jar.bat app-debug.apk -o app-debug.jar
 *
 * 3.jd-gui->查看反编译后的jar中的class
 *
 * 4.jadx->直接查看资源与代码
 *
 * 5.enjarify->将apk反编译成java源码  ：  enjarify *.apk -o out.jar
 *
 *
 * 混淆 ：
 * <p>
 * 开启->  release {
 * minifyEnabled false/true
 * proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
 * }
 *
 * 如果某个类不想参与混淆
 *
 * 1.可以增加注解 @Keep 在混淆文件中 -keep class androidx.annotation.Keep
 *
 * 2.混淆文件中 -keep class com.example.superclass.reinforce
 *
 *
 * -keep 指定类和类成员（变量和方法）不被混淆。
 * 	-keep class com.dongnao.proxy.guard.test.Bug
 * 	（保护了类名）
 * 	-keep class com.dongnao.proxy.guard.test.Bug{
 *    		public static void *();
 *        }
 * 	（保护了 public static void的没有参数的函数）
 * 	-keep class com.dongnao.proxy.guard.test.Bug{
 *    		*；
 *    }
 * 	(保护所有)
 * -keepclassmembers 指定类成员不被混淆(就是-keep的缩小版，不管类名了)。
 * 	-keepclassmembers
 * 	class com.dongnao.proxy.guard.test.Bug
 * 	（都被混淆了）
 * -keepclasseswithmembers 指定类和类成员不被混淆，前提是指定的类成员存在。
 * 	-keepclasseswithmembers class 	com.dongnao.proxy.guard.test.Bug
 * 	(保护类名，但是没指定成员，所以函数名被混淆)
 * 	-keepclasseswithmembers class   	com.dongnao.proxy.guard.test.Bug{
 * 		native <methods>;
 *    }
 *
 * 错误解决：
 *      outputs/mapping/debug/mapping.txt文件中保存了混淆前后的对应关系
 *      1.把错误信息保存到文件(错误信息 用txt utf-8存储)
 *      2.使用工具 sdk/tools/groguard/bin/retrace.bat
 *        先配置  -keepattributes SourceFile,LineNumberTable
 *        再执行  retrace.bat  -verbose mapping文件  bug文件
 *
 */
public class ReinforceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinforce);
    }
}
