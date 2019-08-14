#include <jni.h>
#include <string>
#include "art_method.h"
/**
 * Andfix
 *
 */


extern "C"
JNIEXPORT void JNICALL
Java_com_example_superclass_hotfix_DexManager_replace(JNIEnv *env, jclass type, jobject bugMethod,
jobject method) {

//获取 ArtMethod 系统源码头文件
art::mirror::ArtMethod *bug = (art::mirror::ArtMethod *) env->FromReflectedMethod(bugMethod);
art::mirror::ArtMethod *right = (art::mirror::ArtMethod *) env->FromReflectedMethod(method);

bug->declaring_class_ = right->declaring_class_;
bug->dex_cache_resolved_methods_ = right->dex_cache_resolved_methods_;
bug->access_flags_ = right->access_flags_;
bug->dex_cache_resolved_types_ = right->dex_cache_resolved_types_;
bug->dex_code_item_offset_ = right->dex_code_item_offset_;
//方法索引替换
bug->method_index_ = right->method_index_;
bug->method_dex_index_ = right->method_dex_index_;
}

