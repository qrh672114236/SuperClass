# 指定Cmake需要的最低版本
cmake_minimum_required(VERSION 3.4.1)

#设置输出路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/jniLibs/${ANDROID_ABI})

#添加子目录，将自动找到子目录中的CMakeLists.txt
ADD_SUBDIRECTORY(${PROJECT_SOURCE_DIR}/src/main/cpp/gif)
ADD_SUBDIRECTORY(${PROJECT_SOURCE_DIR}/src/main/cpp/fix)
ADD_SUBDIRECTORY(${PROJECT_SOURCE_DIR}/src/main/cpp/ffmpeg)
ADD_SUBDIRECTORY(${PROJECT_SOURCE_DIR}/src/main/cpp/ffmpeg/dongnao)
ADD_SUBDIRECTORY(${PROJECT_SOURCE_DIR}/src/main/cpp)

# 创建和命名该库  （使用多个 add_library() 命令，您可以为 CMake 定义要从其他源文件构建的更多库）
# 第一参数：配置库的名字
# 第二参数：指定库的类型   SHARED(.so 动态库)  STATIC（.a 静态库）
# 第三个参数是指库的路径

#编写目录下所有cpp文件
#file(GLOB SOURCECPP "src/main/cpp/*.cpp")
#file(GLOB SOURCECPP *.cpp)
#编写目录下所有c文件
#file(GLOB SOUCEC *.c)
#
#add_library(
#        native-lib
#        SHARED
#        ${SOURCECPP}
#        ${SOURCEC})

#编写一部分
#add_library(
#        native-lib
#        SHARED
#        #gif
#        gif/dgif_lib.c
#        gif/gifalloc.c
#       native-lib.cpp)

# 找到一个ndk的库，并将这个库路径存在一个变量中

#find_library(
#        log-lib
#        log)
#find_library(
#        jnigraphics-lib
#        jnigraphics )

# 关联库。将指定的库关联起来 。

#target_link_libraries( # Specifies the target library.
#        native-lib
#        ${log-lib}
#        ${jnigraphics-lib}
#        )

#引入单个头文件 ：include()

#引入头文件夹 ：include_directories()
#include_directories(ffmpeg/include)


# 生成可执行文件

# add_executable(demo demo.cpp)

#包含多个 cpp

# add_library(demo demo.cpp test.cpp util.cpp)

#搜索当前目录下的所有.cpp文件

# aux_source_directory(. SRC_LIST)
# add_library(demo ${SRC_LIST})



#cmake 变量参数

    # 当前工程的源码路径：PROJECT_SOURCE_DIR  CMAKE_SOURCE_DIR

    # 指向工程构建目录的全路径 ：PROJECT_BINARY_DIR

    # cmake完整版本号：CMAKE_VERSION

    # 调用这个变量的CMakeLists.txt所在路径 ：CMAKE_CURRENT_SOURCE_DIR

    # 调用这个变量的CMakeLists.txt的完整路径：CMAKE_CURRENT_LIST_FILE

    # 这个变量所在的行数 CMAKE_CURRENT_LIST_LINE

    # 返回通过project指令定义的项目名称 PROJECT_NAME

