//
// Created by Administrator on 2019/8/14 0014.
//

#ifndef SUPERCLASS_VIDEOCHANNEL_H
#define SUPERCLASS_VIDEOCHANNEL_H


extern "C" {
#include "libswscale/swscale.h"
}
#include "BaseChannel.h"
#include "AudioChannel.h"

typedef  void (*RenderFrameCallback)(uint8_t*,int,int,int);
class VideoChannel:public BaseChannel{
public:
    VideoChannel(int id,AVCodecContext *avCodecContext, AVRational time_base,int fps);
    ~VideoChannel();
    void setAudioChannel(AudioChannel*audioChannel);
    void play();
    void stop();
    void decode();
    void render();
    void setRenderFrameCallBack(RenderFrameCallback callback);

private:
    pthread_t pid_decode;
    pthread_t pid_render;
    int fps;
    SwsContext *swsContext=0;
    RenderFrameCallback callback;
    AudioChannel *audioChannel;


};

#endif //SUPERCLASS_VIDEOCHANNEL_H
