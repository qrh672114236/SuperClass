#include "JavaCallHelper.h"
#include "macro.h"


JavaCallHelper::JavaCallHelper(JavaVM *vm, JNIEnv *env, jobject instance) {
    this->env = env;
    this->instance = env->NewGlobalRef(instance);
    this->vm = vm;


    jclass clazz = env->GetObjectClass(instance);
    OnErrorId = env->GetMethodID(clazz, "onError", "(I)V");
    onPrepareId = env->GetMethodID(clazz, "onPrepare", "()V");
}


JavaCallHelper::~JavaCallHelper() {
    env->DeleteGlobalRef(instance);
}

void JavaCallHelper::onPrepare(int thread) {
    //自己定义0主线程 ，1子线程
    if (thread == THREAD_MAIN) {
        env->CallVoidMethod(instance, onPrepareId);
    } else {
        //子线程
        JNIEnv *env;
        //获取当前线程的jnienv
        vm->AttachCurrentThread(&env,0);
        env->CallVoidMethod(instance, onPrepareId);
        vm->DetachCurrentThread();
    }
}

void JavaCallHelper::onError(int thread, int errorCode) {

    //自己定义0主线程 ，1子线程
    if (thread == THREAD_MAIN) {
        env->CallVoidMethod(instance, OnErrorId, errorCode);
    } else {
        //子线程
        JNIEnv *env;
        //获取当前线程的jnienv
        vm->AttachCurrentThread(&env,0);
        env->CallVoidMethod(instance, OnErrorId, errorCode);
        vm->DetachCurrentThread();
    }
}
