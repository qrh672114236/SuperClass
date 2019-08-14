
#include "DNFFmpeg.h"
#include "macro.h"
#include <cstring>
#include <pthread.h>

void *task_prepare(void *args) {
    DNFFmpeg *ffmpeg = static_cast<DNFFmpeg *>(args);
    ffmpeg->_prepare();
    return 0;

}

void *play(void *args) {
    DNFFmpeg *ffmpeg = static_cast<DNFFmpeg *>(args);
    ffmpeg->_start();
    return 0;
}

DNFFmpeg::DNFFmpeg(JavaCallHelper *callHelper, const char *dataSource) {
    //字符串 /0结束
    this->dataSource = new char[strlen(dataSource) + 1];
    this->callHelper = callHelper;
    strcpy(this->dataSource, dataSource);

}

DNFFmpeg::~DNFFmpeg() {
    delete dataSource;
    dataSource = 0;
    DELETE(callHelper);
}

void DNFFmpeg::prepare() {
    //开启一个线程
    pthread_create(&pid, 0, task_prepare, this);
}

void DNFFmpeg::_prepare() {
    //初始化网络
    avformat_network_init();

    formatContext = 0;
    //打开媒体地址

    int ret = -1;
    //1.打开媒体
    ret = avformat_open_input(&formatContext, dataSource, 0, 0);

    if (ret) {
        //ret 为0 成功
        LOGE("打开媒体失败：%s", av_err2str(ret));
        callHelper->onError(THREAD_CHILD, FFMPEG_CAN_NOT_OPEN_URL);
        return;
    }

    //2.查找媒体中的音视频流
    ret = avformat_find_stream_info(formatContext, 0);

    if (ret < 0) {
        LOGE("查找流失败：%s", av_err2str(ret));
        callHelper->onError(THREAD_CHILD, FFMPEG_CAN_NOT_FIND_STREAMS);
        return;
    }
    //3.遍历包含的流
    for (int i = 0; i < formatContext->nb_streams; ++i) {
        AVStream *stream = formatContext->streams[i];

        //4.判断当前流
        AVCodecParameters *codecPar = stream->codecpar;

        //5.获得解码器
        AVCodec *avCodec = avcodec_find_decoder(codecPar->codec_id);
        if (avCodec == NULL) {
            callHelper->onError(THREAD_CHILD, FFMPEG_FIND_DECODER_FAIL);
            return;
        }

        //6.获取解码器上下文
        AVCodecContext *avCodecContext = avcodec_alloc_context3(avCodec);
        if (avCodecContext == NULL) {
            callHelper->onError(THREAD_CHILD, FFMPEG_ALLOC_CODEC_CONTEXT_FAIL);
            return;
        }

        //7设置解码器上下文的参数
        ret = avcodec_parameters_to_context(avCodecContext, codecPar);
        if (ret < 0) {
            LOGE("设置上下文失败：%s", av_err2str(ret));
            callHelper->onError(THREAD_CHILD, FFMPEG_CODEC_CONTEXT_PARAMETERS_FAIL);
            return;
        }

        //8.打开解码器  0字典
        ret = avcodec_open2(avCodecContext, avCodec, 0);
        if (ret) {
            LOGE("打开解码器失败：%s", av_err2str(ret));
            callHelper->onError(THREAD_CHILD, FFMPEG_OPEN_DECODER_FAIL);
            return;
        }
        //解码
        if (codecPar->codec_type == AVMEDIA_TYPE_AUDIO) {//音频
//            audioChannel = new AudioChannel(i);

        } else if (codecPar->codec_type == AVMEDIA_TYPE_VIDEO) {//视频
//            videoChannel = new VideoChannel(i);

        }
    }
    if (!audioChannel && !videoChannel) {
        LOGE("文件错误");
        callHelper->onError(THREAD_CHILD, FFMPEG_NOMEDIA);
        return;
    }

    //完成准备工作
    callHelper->onPrepare(THREAD_CHILD);
}

void DNFFmpeg::_start() {
    //1.读取媒体数据包
    int ret;
    while (isPlaying) {
        AVPacket *avPacket = av_packet_alloc();
        ret = av_read_frame(formatContext, avPacket);
        if (ret==0){//成功

            if(audioChannel &&avPacket->stream_index==audioChannel->id){//音频包

            }else if(videoChannel &&avPacket->stream_index==videoChannel->id){

            }
        }else if(ret==AVERROR_EOF){//读取完成 但是可能没播完

        } else{

        }
    }
}

void DNFFmpeg::start() {
    isPlaying = true;

    pthread_create(&pid_play, 0, play, this);

}