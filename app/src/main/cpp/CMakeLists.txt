
add_library(native SHARED native.cpp)
#依赖库
find_library(log-lib log)

target_link_libraries(native ${log-lib})