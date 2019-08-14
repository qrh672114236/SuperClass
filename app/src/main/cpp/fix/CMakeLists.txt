
add_library(fix SHARED fix.cpp)
#依赖库
find_library(log-lib log)

target_link_libraries(fix ${log-lib})