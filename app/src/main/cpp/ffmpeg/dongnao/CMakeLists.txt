
file(GLOB SOURCECPP "*.cpp")

add_library(dongnao SHARED ${SOURCECPP})
#依赖库
find_library(log-lib log)

include_directories(../include)

set(my_lib_path  ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})

set(CMAKE_CXX_FLAGS  "${CMAKE_CXX_FLAGS} -L${my_lib_path}")

target_link_libraries(
        dongnao
        avfilter avformat avcodec avutil swresample swscale
        ${log-lib}
        z
        OpenSLES
        android
)