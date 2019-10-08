
add_library(cxx SHARED cxx/cxx.cpp)
#依赖库
find_library(log-lib log)

target_link_libraries(cxx ${log-lib})