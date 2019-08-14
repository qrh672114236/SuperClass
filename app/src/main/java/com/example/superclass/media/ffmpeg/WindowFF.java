package com.example.superclass.media.ffmpeg;

/**
 * ffmpeg window 下指令
 */
public class WindowFF {
/**
 * window：
 *
 *  录制屏幕 ：
 *          ffmpeg -f gdigrab -framerate 30 -offset_x 0 -offset_y 0 -video_size 1920*1080 -i desktop out.mpg
 *
 *          -gdigrab:标示通过 gdi抓取屏幕方式  （mac下 avfoundation）
 *
 *          -framerate 30  录制帧率 30
 *
 *          -offset_x : 左上偏移x
 *
 *          -offset_y ：左上偏移y
 *
 *          -video_size  录制宽高
 *
 *          -i ：输入路径和名称格式
 *
 *          desktop： 告诉ffmpeg 录制的是屏幕不是窗口 ，窗口需要 ID
 *
 *          ctrl+c 完成录制
 *
 *
 *
 *  分解复用
 *         1.抽取音频流  ： ffmpeg -i input.mp4 -acodec copy -vn out.aac
 *
 *         2.抽取视频流 ： ffmpeg -i input.mp4 -vcodec copy -an out.h264
 *
 *         3.合成视频  ： ffmpeg -i out.h264 -i out.aac -vcodec copy -acodec copy out.mp4
 *
 *                           acodec:指定音频编码器
 *
 *                           copy  ：指明只拷贝不做编解码
 *
 *                           vn  ： v代表视频 n代表 no 也就是无视频的意思
 *
 *                           an  ： a代表音频
 *
 *
 * 视频转码：ffmpeg -i out.mp4 -vcodec copy -acodec copy out.flv
 *
 *
 * 提取YUV数据 ：
 *        ffmpeg -i input.mp4 -an -c:v rawvideo -pix_fmt yuv420p out.yuv
 *
 *                          -c:v rawvideo :指定将视频转成原始数据
 *
 *                          -pix_fmt yuv420p：转换格式为 yuv 420
 *
 * 提取pcm数据
 *          ffmpeg -i input.mp4 -vn -ar 44100 -ac 2 -f sl6le out.pcm
 *
 *                          -ar:指定音频采样率为 44100
 *
 *                          -ac 指定音频声道channel2为双声道
 *
 *                          -f：数据存储格式
 *
 *                          s: sugned 有符号的
 *
 *                          16：每一个数值都用16位标示
 *
 *                          l：little
 *
 *                          e：end
 *
 *
 *打开yuv视频 ：
 *          ffplay -s 1904*928 out.yuv
 *
 *打开pcm音频 ：
 *          ffplay -ar 44100 -ac 2 -f s16le out.pcm
 *
 *
 *裁剪滤镜
 *
 *          ffmpeg -i input.mp4 -vf crop=in_w-200:in_h-200 -c:v libx264 -c:a copy crop.mp4
 *
 *
 *
 * 切割视频
 *          ffmpeg -ss 00:00:15 -t 00:00:05 -i input.mp4 -vcodec copy -acodec copy output.mp4
 *
 *                     -ss 开始时间
 *                     -t  切多少秒
 *
 */

}
