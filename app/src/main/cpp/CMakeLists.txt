# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 指定Cmake需要的最低版本

cmake_minimum_required(VERSION 3.4.1)

# 创建和命名该库  （使用多个 add_library() 命令，您可以为 CMake 定义要从其他源文件构建的更多库）
# 第一参数：配置库的名字
# 第二参数：指定库的类型   SHARED(.so 动态库)  STATIC（.a 静态库）
# 第三个参数是指库的路径

add_library(
        native-lib
        SHARED
        #gif
        gif/dgif_lib.c
        gif/gifalloc.c
        native-lib.cpp)

# 找到一个ndk的库，并将这个库路径存在一个变量中

find_library(
        log-lib
        log)
find_library(
        jnigraphics-lib
        jnigraphics )

# 关联库。将指定的库关联起来 。

target_link_libraries( # Specifies the target library.
        native-lib
        ${log-lib}
        ${jnigraphics-lib}
        )


# 生成可执行文件

# add_executable(demo demo.cpp)

#包含多个 cpp

# add_library(demo demo.cpp test.cpp util.cpp)

#搜索当前目录下的所有.cpp文件

# aux_source_directory(. SRC_LIST)
# add_library(demo ${SRC_LIST})