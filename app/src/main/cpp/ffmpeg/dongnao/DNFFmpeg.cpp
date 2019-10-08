
#include "DNFFmpeg.h"
#include "macro.h"
#include <cstring>
#include <pthread.h>

extern "C" {
#include <libavutil/time.h>
}

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
    DELETE(dataSource);
    DELETE(callHelper);
}

void DNFFmpeg::prepare() {
    //开启一个线程
    pthread_create(&pid, 0, task_prepare, this);
}

void DNFFmpeg::_prepare() {
    //初始化网络
    avformat_network_init();
    //打开媒体地址
    int ret = -1;
    //设置超时时间(微秒)
    AVDictionary *opt = 0;

    av_dict_set(&opt, "timeout", "5*1000*1000", 0);

    //1.打开媒体-> p3:视频传递格式 （ffmpeg会自动推导 所以不用传）
    ret = avformat_open_input(&formatContext, dataSource, 0, &opt);
    av_dict_free(&opt);
    if (ret) {
        //ret 为0 成功
        LOGE("打开媒体失败：%s", av_err2str(ret));
        if (isPlaying) {
            callHelper->onError(THREAD_CHILD, FFMPEG_CAN_NOT_OPEN_URL);
        }

        return;
    }

    //2.查找媒体中的音视频流
    ret = avformat_find_stream_info(formatContext, 0);

    if (ret < 0) {
        LOGE("查找流失败：%s", av_err2str(ret));
        if (isPlaying) {
            callHelper->onError(THREAD_CHILD, FFMPEG_CAN_NOT_FIND_STREAMS);
        }

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
            if (isPlaying) {
                callHelper->onError(THREAD_CHILD, FFMPEG_FIND_DECODER_FAIL);
            }
            return;
        }

        //6.获取解码器上下文
        AVCodecContext *avCodecContext = avcodec_alloc_context3(avCodec);
        if (avCodecContext == NULL) {
            if (isPlaying) {
                callHelper->onError(THREAD_CHILD, FFMPEG_ALLOC_CODEC_CONTEXT_FAIL);
            }
            return;
        }

        //7设置解码器上下文的参数
        ret = avcodec_parameters_to_context(avCodecContext, codecPar);
        if (ret < 0) {
            LOGE("设置上下文失败：%s", av_err2str(ret));
            if (isPlaying) {
                callHelper->onError(THREAD_CHILD, FFMPEG_CODEC_CONTEXT_PARAMETERS_FAIL);
            }
            return;
        }

        //8.打开解码器  0字典
        ret = avcodec_open2(avCodecContext, avCodec, 0);
        if (ret) {
            LOGE("打开解码器失败：%s", av_err2str(ret));
            if (isPlaying) {
                callHelper->onError(THREAD_CHILD, FFMPEG_OPEN_DECODER_FAIL);
            }
            return;
        }
        AVRational time_base = stream->time_base;

        //解码
        if (codecPar->codec_type == AVMEDIA_TYPE_AUDIO) {//音频
            audioChannel = new AudioChannel(i, avCodecContext, time_base);

        } else if (codecPar->codec_type == AVMEDIA_TYPE_VIDEO) {//视频
            //帧率：单位时间内显示多少个图像
            AVRational frame_rate = stream->avg_frame_rate;
            int fps = av_q2intfloat(frame_rate);

            videoChannel = new VideoChannel(i, avCodecContext, time_base, fps);

            videoChannel->setRenderFrameCallBack(callback);


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
        //读本地可能一下子读完 可能导致oom
        if (audioChannel && audioChannel->packets.size() > 100) {
            av_usleep(1000 * 10);
            continue;
        }

        if (videoChannel && videoChannel->packets.size() > 100) {
            av_usleep(1000 * 10);
            continue;
        }


        AVPacket *avPacket = av_packet_alloc();
        ret = av_read_frame(formatContext, avPacket);
        if (ret == 0) {//成功
            if (audioChannel && avPacket->stream_index == audioChannel->id) {//音频包
                audioChannel->packets.push(avPacket);

            } else if (videoChannel && avPacket->stream_index == videoChannel->id) {
                videoChannel->packets.push(avPacket);
            }
        } else if (ret == AVERROR_EOF) {//读取完成 但是可能没播完
            if (audioChannel->packets.empty() && audioChannel->avFrames.empty() &&
                videoChannel->packets.empty() && videoChannel->avFrames.empty()) {
                break;
            }
        } else {
            break;
        }
    }
    isPlaying = 0;
    audioChannel->stop();
    videoChannel->stop();
}

void DNFFmpeg::start() {
    isPlaying = 1;

    if (audioChannel) {
        audioChannel->play();
    }
    if (videoChannel) {
        videoChannel->setAudioChannel(audioChannel);
        videoChannel->play();
    }


    pthread_create(&pid_play, 0, play, this);

}

void *aync_stop(void *args) {
    DNFFmpeg *dnfFmpeg = static_cast<DNFFmpeg *>(args);
    pthread_join(dnfFmpeg->pid, 0);

    pthread_join(dnfFmpeg->pid_play, 0);
    DELETE(dnfFmpeg->audioChannel);
    DELETE(dnfFmpeg->videoChannel);

    if (dnfFmpeg->formatContext) {
        avformat_close_input(&dnfFmpeg->formatContext);
        avformat_free_context(dnfFmpeg->formatContext);
        dnfFmpeg->formatContext = 0;
    }
    DELETE(dnfFmpeg);

    return 0;
}

void DNFFmpeg::stop() {
    isPlaying = 0;
    callHelper = 0;
    pthread_create(&pid_stop, 0, aync_stop, this);
}

void DNFFmpeg::setRenderFrameCallBack(RenderFrameCallback callback) {
    this->callback = callback;

}

