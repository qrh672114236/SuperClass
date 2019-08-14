//
// Created by Administrator on 2019/8/14 0014.
//

#ifndef SUPERCLASS_JAVACALLHELPER_H
#define SUPERCLASS_JAVACALLHELPER_H

#include <jni.h>

class JavaCallHelper {
public:
    JavaCallHelper(JavaVM *vm,JNIEnv *env,jobject instance);
    ~JavaCallHelper();

    void onError(int thread,int errorCode);

    void onPrepare(int thread);

private:
    JavaVM *vm;
    JNIEnv *env;
    jobject instance;
    jmethodID OnErrorId;
    jmethodID onPrepareId;

};


#endif //SUPERCLASS_JAVACALLHELPER_H
