
#include "VideoChannel.h"
#include "AudioChannel.h"

extern "C" {
#include <libavutil/imgutils.h>
#include <libavutil/time.h>
}

VideoChannel::VideoChannel(int id, AVCodecContext *avCodecContext, AVRational time_base, int fps)
        : BaseChannel(id,
                      avCodecContext, time_base) {
    this->fps = fps;
}
//丢 av_packet包 (直到下一个关键帧)I BP
void dropAvPacket(queue<AVPacket*> &q){
    while(!q.empty()){
        AVPacket *packet=q.front();
        //如果不是I帧 就释放
        if (packet->flags!=AV_PKT_FLAG_KEY){
            BaseChannel::releaseAvPacket(&packet);
            q.pop();
        }else{
            break;
        }
    }
}

//丢 avframe 图片
void dropAvFrame(queue<AVFrame*> &q){
    while(!q.empty()){
        AVFrame *frame=q.front();
        BaseChannel::releaseAvFrame(&frame);
        q.pop();

    }
}

//解码
void *decode_task(void *args) {
    VideoChannel *channel = static_cast<VideoChannel *>(args);
    channel->decode();
    return 0;
}

//渲染
void *render_task(void *args) {
    VideoChannel *channel = static_cast<VideoChannel *>(args);
    channel->render();
    return 0;
}


void VideoChannel::play() {
    isPlaying = 1;
    avFrames.setWork(1);
    packets.setWork(1);
    //1.解码
    pthread_create(&pid_decode, 0, decode_task, this);
    //2.播放
    pthread_create(&pid_render, 0, render_task, this);
}

void VideoChannel::decode() {
    AVPacket *packet = 0;
    while (isPlaying) {
        int ret = packets.pop(packet);
        if (!isPlaying) {
            break;
        }
        if (!ret) {
            continue;
        }
        //把包丢给解码器
        ret = avcodec_send_packet(avCodecContext, packet);
        releaseAvPacket(&packet);
        if (ret != 0) {
            break;
        }
        //代表一个图像
        AVFrame *frame = av_frame_alloc();
        ret = avcodec_receive_frame(avCodecContext, frame);
        //需要更多数据来解码
        if (ret == AVERROR(EAGAIN)) {
            continue;
        } else if (ret != 0) {
            break;
        }

        //再开线程播放
        avFrames.push(frame);

    }
    releaseAvPacket(&packet);
}

void VideoChannel::setAudioChannel(AudioChannel *audioChannel) {
    this->audioChannel = audioChannel;
}

void VideoChannel::render() {
    swsContext = sws_getContext(avCodecContext->width, avCodecContext->height,
                                avCodecContext->pix_fmt,
                                avCodecContext->width, avCodecContext->height, AV_PIX_FMT_RGBA,
                                SWS_BILINEAR, 0, 0, 0);
    AVFrame *frame = 0;
    uint8_t *dst_data[4];
    int dst_linesize[4];
    //画面刷新间隔
    double frame_delays = 1.0 / fps;
    av_image_alloc(dst_data, dst_linesize, avCodecContext->width, avCodecContext->height,
                   AV_PIX_FMT_RGBA, 1);

    while (isPlaying) {
        int ret = avFrames.pop(frame);
        if (!isPlaying) {
            break;
        }
        sws_scale(swsContext, frame->data, frame->linesize, 0, avCodecContext->height, dst_data,
                  dst_linesize);
        //获得视频播放的相对时间
        double clock = frame->best_effort_timestamp * av_q2d(time_base);
        //额外间隔时间
        double extra_delay = frame->repeat_pict / (2 * fps);
        //真实需要的间隔时间
        double delays=extra_delay+frame_delays;
        if (!audioChannel) {
            av_usleep(delays * 1000000);
        } else {
            if (clock == 0) {
                //休眠
                av_usleep(delays * 1000000);
            } else {
                //比较视频音频
                double audioClock = audioChannel->clock;
                //相差时间
                double diff = clock - audioClock;

                if (diff>0){//视频快
                    av_usleep((delays+diff)*1000000);

                }else if(delays<0){//音频快
                    //视频挤压太多 （丢包）
                    if (fabs(diff)>0.05){
                        releaseAvFrame(&frame);
                        //丢包
                        avFrames.sync();
                        continue;
                    }
                }
            }
        }
        callback(dst_data[0], dst_linesize[0], avCodecContext->width, avCodecContext->height);
        releaseAvFrame(&frame);
    }
    av_free(&dst_data[0]);
    releaseAvFrame(&frame);

    isPlaying=0;
    sws_freeContext(swsContext);
    swsContext=0;
}

VideoChannel::~VideoChannel() {
    avFrames.clear();
}

void VideoChannel::setRenderFrameCallBack(RenderFrameCallback callback) {
    this->callback = callback;
}
void VideoChannel::stop() {
    isPlaying=0;
    avFrames.setWork(0);
    packets.setWork(0);
    pthread_join(pid_decode,0);
    pthread_join(pid_render,0);



}