//
// Created by Administrator on 2019/8/14 0014.
//

#ifndef SUPERCLASS_BASECHANNEL_H
#define SUPERCLASS_BASECHANNEL_H

#include "safe_queue.h"

extern "C" {
#include <libavcodec/avcodec.h>
};

class BaseChannel {
public:
    BaseChannel(int id, AVCodecContext *avCodecContext, AVRational time_base) : id(id),
                                                                                avCodecContext(
                                                                                        avCodecContext),
                                                                                time_base(
                                                                                        time_base) {
        packets.setReleaseCallback(releaseAvPacket);
        avFrames.setReleaseCallback(releaseAvFrame);
    }

    virtual ~BaseChannel() {
        avFrames.clear();
        packets.clear();
        if (avCodecContext) {
            avcodec_close(avCodecContext);
            avcodec_free_context(&avCodecContext);
            avCodecContext = 0;
        }
    }


    /**
    * 释放 AVPacket
    * @param packet
    */
    static void releaseAvPacket(AVPacket **packet) {
        if (packet) {
            av_packet_free(packet);
            //为什么用指针的指针？
            // 指针的指针能够修改传递进来的指针的指向
            *packet = 0;
        }
    }

    static void releaseAvFrame(AVFrame **frame) {
        if (frame) {
            av_frame_free(frame);
            *frame = 0;
        }
    }

    virtual void play() = 0;

    virtual void stop() = 0;

    int id;
    SafeQueue<AVPacket *> packets;
    SafeQueue<AVFrame *> avFrames;
    AVRational time_base;
    bool isPlaying;
    AVCodecContext *avCodecContext;

public:
    double clock;
};

#endif //SUPERCLASS_BASECHANNEL_H
