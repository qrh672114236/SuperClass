package com.example.superclass.uitls;

/**
 * Linux centOs 命令
 */
public class linux {

    //下载 wget url

    //tar –xvf file.tar //解压 tar包
    //tar -xzvf file.tar.gz //解压tar.gz
    //tar -xjvf file.tar.bz2   //解压 tar.bz2
    //tar –xZvf file.tar.Z   //解压tar.Z
    //unrar e file.rar //解压rar
    //unzip file.zip //解压zip


    // rm 文件名 删除文件

    //查找 是否端口可用 lsof -i:8080


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
