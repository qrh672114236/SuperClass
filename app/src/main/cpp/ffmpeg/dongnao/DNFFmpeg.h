
#ifndef SUPERCLASS_DNFFMPEG_H
#define SUPERCLASS_DNFFMPEG_H


#include <sys/types.h>
#include "JavaCallHelper.h"
#include "AudioChannel.h"
#include "VideoChannel.h"

extern "C"{
#include "libavcodec/avcodec.h"
#include <libswscale/swscale.h>
#include <libavutil/imgutils.h>
#include <libavformat/avformat.h>
}


class DNFFmpeg {
public:
    DNFFmpeg(JavaCallHelper *callHelper,const char*dataSource);
    ~DNFFmpeg();
    void prepare();
    void _prepare();
    void start();
    void _start();
    void stop();
    void setRenderFrameCallBack(RenderFrameCallback callback);

public:
    char*dataSource;
    pthread_t pid;
    pthread_t pid_play;
    pthread_t pid_stop;
    AVFormatContext *formatContext=0;
    JavaCallHelper *callHelper=0;
    AudioChannel *audioChannel=0;
    VideoChannel *videoChannel=0;
    RenderFrameCallback callback;
    bool  isPlaying;
};


#endif //SUPERCLASS_DNFFMPEG_H
