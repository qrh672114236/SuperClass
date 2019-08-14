package com.example.superclass.uitls;

/**
 * Linux centOs 命令
 */
public class linux {


    //解压缩：zip : unzip   xxx.zip

    //下载 wget url


    /**
     * linux 编译 ：
     *      编译so命令：  gcc -fPIC -shared xxx.c -o xxx.o
     *      编译可执行文件： gcc -rdynamic -o xxx xxx.c -ldl
     */

    /**
     * 静态库打包成动态库
     *
     *      gcc -shared -o libMain.so -wl,--whole-archive libMain.a -Wl,--no-whole-archive
     */
}
