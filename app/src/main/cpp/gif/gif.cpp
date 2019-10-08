#include <jni.h>
#include <string>
#include "gif_lib.h"
#include <android/bitmap.h>
#include <android/log.h>
#include <malloc.h>
#define  LOG_TAG    "qq"
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define  argb(a,r,g,b) ( ((a) & 0xff) << 24 ) | ( ((b) & 0xff) << 16 ) | ( ((g) & 0xff) << 8 ) | ((r) & 0xff)
/**
 * gif加载优化 cpp
 */

typedef struct GifBean {

    int current_frame;
    int total_frame;
    int *dealys;
} GifBean;


//绘制一张图片
void drawFrame(GifFileType *gifFileType, GifBean *gifBean, AndroidBitmapInfo info, void **pixels) {
    //当前帧
    SavedImage savedImage = gifFileType->SavedImages[gifBean->current_frame];

    GifImageDesc frameInfo=savedImage.ImageDesc;
    //rgb数据
    ColorMapObject *colorMapObject=frameInfo.ColorMap;
    //图片首地址

    GifByteType  gifByteType; //压缩数据
    int *px = (int *) pixels;
    int pointPixel;
    px = (int *) ((char*)px + info.stride * frameInfo.Top);
    int *line;
    for (int y = frameInfo.Top; y < frameInfo.Top+frameInfo.Height; ++y) {
        for (int x = frameInfo.Left; x <frameInfo.Left+frameInfo.Width; ++x) {
            pointPixel= (y-frameInfo.Top)*frameInfo.Width+(x-frameInfo.Left);

            gifByteType= savedImage.RasterBits[pointPixel];
            GifColorType gifColorType=colorMapObject->Colors[gifByteType];
            line[x]=argb(255,gifColorType.Red, gifColorType.Green, gifColorType.Blue);

        }
        px = (int *) ((char*)px + info.stride);
    }
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_superclass_gif_GifHandler_loadPath(JNIEnv *env, jobject instance, jstring path_) {
    const char *path = env->GetStringUTFChars(path_, 0);
    int err;
    GifFileType *gifFileType = DGifOpenFileName(path, &err);
    DGifSlurp(gifFileType);
    GifBean *gifBean = (GifBean *) malloc(sizeof(GifBean));
    memset(gifBean, 0, sizeof(GifBean));
    gifFileType->UserData = gifBean;
    gifBean->dealys = (int *) malloc(sizeof(int) * gifFileType->ImageCount);
    memset(gifBean->dealys, 0, sizeof(int) * gifFileType->ImageCount);

    gifFileType->UserData = gifBean;
    gifBean->current_frame = 0;
    gifBean->total_frame = gifFileType->ImageCount;
    ExtensionBlock *ext;

    for (int i = 0; i < gifFileType->ImageCount; ++i) {
        SavedImage frame = gifFileType->SavedImages[i];
        for (int j = 0; j < frame.ExtensionBlockCount; ++j) {
            if (frame.ExtensionBlocks[j].Function == GRAPHICS_EXT_FUNC_CODE) {
                ext = &frame.ExtensionBlocks[j];
                break;
            };
        }
        if (ext) {
            int frame_delay = 10 * (ext->Bytes[1] | (ext->Bytes[2] << 8));

            gifBean->dealys[i] = frame_delay;
        }
    }

    env->ReleaseStringUTFChars(path_, path);
    return (jlong) gifFileType;

}



extern "C"
JNIEXPORT jint JNICALL
Java_com_example_superclass_gif_GifHandler_getWidth(JNIEnv *env, jobject instance,
                                                    jlong ndkGif) {
    GifFileType *gifFileType = (GifFileType *) ndkGif;
    return gifFileType->SWidth;
}extern "C"
JNIEXPORT jint JNICALL
Java_com_example_superclass_gif_GifHandler_getHeight(JNIEnv *env, jobject instance,
                                                     jlong ndkGif) {
    GifFileType *gifFileType = (GifFileType *) ndkGif;
    return gifFileType->SHeight;

}extern "C"
JNIEXPORT jint JNICALL
Java_com_example_superclass_gif_GifHandler_updateFrame(JNIEnv *env,
                                                       jobject instance,
                                                       jlong ndkGif,
                                                       jobject bitmap) {

    GifFileType *gifFileType = (GifFileType *) ndkGif;

    GifBean *gifBean = (GifBean *) gifFileType->UserData;

    AndroidBitmapInfo info;

    AndroidBitmap_getInfo(env, bitmap, &info);

    void **pixels;

    AndroidBitmap_lockPixels(env, bitmap, pixels);


    drawFrame(gifFileType, gifBean, info, pixels);

    gifBean->current_frame += 1;
    if (gifBean->current_frame >= gifBean->total_frame - 1) {
        gifBean->current_frame = 0;
    }

    AndroidBitmap_unlockPixels(env, bitmap);

    return gifBean->dealys[gifBean->current_frame];
}