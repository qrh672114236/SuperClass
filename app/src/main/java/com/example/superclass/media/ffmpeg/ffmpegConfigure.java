package com.example.superclass.media.ffmpeg;

/**
 * ffmpeg 配置文件
 */
public class ffmpegConfigure {

    /**
     * 帮助选项：
     *    --help 打印此消息
     *    --quiet 抑制显示信息输出
     *    --list-decoders 显示所有可用的解码器
     *    --list-encoders 显示所有可用的编码器
     *    --list-hwaccels 显示所有可用的硬件加速器
     *    --list-demuxers 显示所有可用的解复用器
     *    --list-muxers 显示所有可用的复用器
     *    --list-parsers 显示所有可用的解析器
     *    --list-protocols 显示所有可用的协议
     *    --list-bsfs 显示所有可用的比特流过滤器
     *    --list-indevs 显示所有可用的输入设备
     *    --list-outdevs 显示所有可用的输出设备
     *    --list-filters 显示所有可用的过滤器

     * 标准选项：
     *   --logfile = FILE日志测试并输出到FILE [ffbuild / config.log]
     *   --disable-logging不记录配置调试信息
     *    - 如果生成任何配置警告，则-fatal-warnings将失败
     *   --prefix = PREFIX安装在PREFIX [/ usr / local]
     *   -bindir = DIR在DIR [PREFIX / bin]中安装二进制文件
     *   -datadir = DIR在DIR中安装数据文件[PREFIX / share / ffmpeg]
     *   -docdir = DIR中的DIR安装文档[PREFIX / share / doc / ffmpeg]
     *   --libdir = DIR在DIR [PREFIX / lib]中安装库
     *   --shlibdir = DIR在DIR [LIBDIR]中安装共享库
     *   --incdir = DIR安装包含在DIR [PREFIX / include]中
     *   --mandir =在DIR安装手册页[PREFIX / share / man]
     *   --pkgconfigdir = DIR在DIR中安装pkg-config文件[LIBDIR / pkgconfig]
     *   --enable-rpath使用rpath允许在路径中安装库
     *                            不属于动态链接器搜索路径的一部分
     *                            链接程序时使用rpath（USE WITH CARE）
     *   --install-name-dir =已安装目标的DIR Darwin目录名

     * 许可选项：
     *   --enable-gpl允许使用GPL代码，生成的库
     *                            和二进制文件将在GPL下[no]
     *   --enable-version3升级（L）GPL到版本3 [no]
     *   --enable-nonfree允许使用非自由代码，生成的库
     *                            和二进制文件将是不可分发的[不]

     * 配置选项：
     *   --disable-static不构建静态库[no]
     *   --enable-shared构建共享库[no]
     *   --enable-small优化尺寸而不是速度
     *   --disable-runtime-cpudetect禁用在运行时检测CPU功能（较小的二进制）
     *   --enable-grey启用全灰度支持（较慢的颜色）
     *   --disable-swscale-alpha禁用swscale中的alpha通道支持
     *   --disable-all禁用构建组件，库和程序
     *   --disable-autodetect禁用自动检测到的外部库[no]

     * 计划选项：
     *   --disable-programs不构建命令行程序
     *   --disable-ffmpeg禁用ffmpeg构建
     *   --disable-ffplay禁用ffplay构建
     *   --disable-ffprobe禁用ffprobe构建

     * 文档选项：
     *   --disable-doc不构建文档
     *   --disable-htmlpages不构建HTML文档页面
     *   --disable-manpages不构建man文档页面
     *   --disable-podpages不构建POD文档页面
     *   --disable-txtpages不构建文本文档页面

     * 组件选项：
     *   --disable-avdevice禁用libavdevice构建
     *   --disable-avcodec禁用libavcodec构建
     *   --disable-avformat禁用libavformat构建
     *   --disable-swresample禁用libswresample build
     *   --disable-swscale禁用libswscale构建
     *   --disable-postproc禁用libpostproc构建
     *   --disable-avfilter禁用libavfilter构建
     *   --enable-avresample启用libavresample build（不建议使用）[no]
     *   --disable-pthreads禁用pthreads [autodetect]
     *   --disable-w32threads禁用Win32线程[autodetect]
     *   --disable-os2threads禁用OS / 2线程[autodetect]
     *   --disable-network禁用网络支持[否]
     *   --disable-dct禁用DCT代码
     *   --disable-dwt禁用DWT代码
     *   --disable-error-resilience禁用错误恢复代码
     *   --disable-lsp禁用LSP代码
     *   --disable-lzo禁用LZO解码器代码
     *   --disable-mdct禁用MDCT代码
     *   --disable-rdft禁用RDFT代码
     *   --disable-fft禁用FFT代码
     *   --disable-faan禁用浮点AAN（I）DCT代码
     *   --disable-pixelutils禁用libavutil中的pixel utils


     * 个别组件选项：
     *   --disable-everything禁用下面列出的所有组件
     *   --disable-encoder = NAME禁用编码器名称
     *   --enable-encoder = NAME启用编码器NAME
     *   --disable-encoders禁用所有编码器
     *   --disable-decoder = NAME禁用解码器NAME
     *   --enable-decoder = NAME启用解码器NAME
     *   --disable-decoders禁用所有解码器
     *   --disable-hwaccel = NAME禁用hwaccel NAME
     *   --enable-hwaccel = NAME启用hwaccel NAME
     *   --disable-hwaccels禁用所有hwaccel
     *   --disable-muxer = NAME禁用muxer NAME
     *   --enable-muxer = NAME启用muxer NAME
     *   --disable-muxers禁用所有复用器
     *   --disable-demuxer = NAME禁用demuxer NAME
     *   --enable-demuxer = NAME启用解复用程序NAME
     *   --disable-demuxers禁用所有解复用器
     *   --enable-parser = NAME启用解析器NAME
     *   --disable-parser = NAME禁用解析器NAME
     *   --disable-parsers禁​​用所有解析器
     *   --enable-bsf = NAME启用比特流过滤器NAME
     *   --disable-bsf = NAME禁用比特流过滤器NAME
     *   --disable-bsfs禁用所有比特流过滤器
     *   --enable-protocol = NAME启用协议NAME
     *   --disable-protocol = NAME禁用协议名称
     *   --disable-protocols禁用所有协议
     *   --enable-indev = NAME启用输入设备NAME
     *   --disable-indev = NAME禁用输入设备NAME
     *   --disable-indevs禁用输入设备
     *   --enable-outdev = NAME启用输出设备NAME
     *   --disable-outdev = NAME禁用输出设备NAME
     *   --disable-outdevs禁用输出设备
     *   --disable-devices禁用所有设备
     *   --enable-filter = NAME启用过滤器NAME
     *   --disable-filter = NAME禁用过滤器名称
     *   --disable-filters禁用所有过滤器

     * 外部库支持：
     * 使用以下任何一个开关将允许FFmpeg链接到
     * 相应的外部库。所有组件都依赖于该库
     * 如果所有其他依赖项都得到满足而未满足，则将启用
     * 明确禁用。例如。 –enable-libwavpack将启用链接
     * libwavpack并允许构建libwavpack编码器，除非它是
     * 使用–disable-encoder = libwavpack专门禁用。
     *
     * 请注意，仅自动检测系统库。所有其他外部
     * 必须明确启用库。
     *
     * 另请注意，以下帮助文本描述了库的用途
     * 他们自己并非所有功能都必须由FFmpeg使用。
     *
     *   --disable-alsa禁用ALSA支持[autodetect]
     *   --disable-appkit禁用Apple AppKit框架[autodetect]
     *   --disable-avfoundation禁用Apple AVFoundation框架[autodetect]
     *   --enable-avisynth可以读取AviSynth脚本文件[no]
     *   --disable-bzlib disable bzlib [autodetect]
     *   --disable-coreimage禁用Apple CoreImage框架[autodetect]
     *   --enable-chromaprint启用带色度的音频指纹识别[no]
     *   --enable-frei0r启用frei0r视频过滤[no]
     *   --enable-gcrypt启用gcrypt，rtmp（t）e支持所需
     *                            如果没有使用openssl，librtmp或gmp [no]
     *   --enable-gmp启用gmp，rtmp（t）e支持所需
     *                            如果没有使用openssl或librtmp [no]
     *   --enable-gnutls支持https支持所需的gnutls
     *                            如果没有使用openssl或libtls [no]
     *   --disable-iconv禁用iconv [autodetect]
     *   --enable-jni启用JNI支持[no]
     *   --enable-ladspa启用LADSPA音频过滤[否]
     *   --enable-libaom通过libaom启用AV1视频编码/解码[no]
     *   --enable-libass启用libass字幕渲染，
     *                            字幕和屁股过滤所需[no]
     *   --enable-libbluray使用libbluray启用BluRay读取[no]
     *   --enable-libbs2b启用bs2b DSP库[no]
     *   --enable-libcaca使用libcaca启用文本显示[no]
     *   --enable-libcelt通过libcelt启用CELT解码[no]
     *   --enable-libcdio启用音频CD抓取libcdio [no]
     *   --enable-libcodec2使用libcodec2启用codec2 en / decode [no]
     *   --enable-libdc1394使用libdc1394启用IIDC-1394
     *                            和libraw1394 [no]
     *   --enable-libfdk-aac通过libfdk-aac启用AAC de / encoding [no]
     *   --enable-libflite通过libflite启用flite（语音合成）支持[no]
     *   --enable-libfontconfig启用libfontconfig，对drawtext过滤器很有用[no]
     *   --enable-libfreetype启用libfreetype，drawtext过滤器需要[no]
     *   --enable-libfribidi启用libfribidi，改进了drawtext过滤器[no]
     *   --enable-libgme通过libgme启用游戏音乐Emu [no]
     *   --enable-libgsm通过libgsm启用GSM de / encoding [no]
     *   --enable-libiec61883通过libiec61883启用iec61883 [no]
     *   --enable-libilbc通过libilbc启用iLBC de / encoding [no]
     *   --enable-libjack启用JACK音频声音服务器[否]
     *   --enable-libkvazaar通过libkvazaar启用HEVC编码[no]
     *   --enable-libmodplug通过libmodplug启用ModPlug [no]
     *   --enable-libmp3lame通过libmp3lame启用MP3编码[no]
     *   --enable-libopencore-amrnb通过libopencore-amrnb启用AMR-NB de / encoding [no]
     *   --enable-libopencore-amrwb通过libopencore-amrwb启用AMR-WB解码[no]
     *   --enable-libopencv通过libopencv启用视频过滤[no]
     *   --enable-libopenh264通过OpenH264启用H.264编码[no]
     *   --enable-libopenjpeg通过OpenJPEG启用JPEG 2000 de / encoding [no]
     *   --enable-libopenmpt通过libopenmpt启用解码跟踪文件[no]
     *   --enable-libopus通过libopus启用Opus de / encoding [no]
     *   --enable-libpulse通过libpulse启用Pulseaudio输入[no]
     *   --enable-librsvg通过librsvg启用​​SVG光栅化[no]
     *   --enable-librubberband启用橡皮带过滤器所需的橡皮带[no]
     *   --enable-librtmp通过librtmp [no]启用RTMP [E]支持
     *   --enable-libshine通过libshine启用定点MP3编码[no]
     *   --enable-libsmbclient通过libsmbclient启用Samba协议[no]
     *   --enable-libsnappy启用Snappy压缩，用于hap编码[no]
     *   --enable-libsoxr enable包含libsoxr重采样[no]
     *   --enable-libspeex通过libspeex启用Speex de / encoding [no]
     *   --enable-libsrt通过libsrt启用Haivision SRT协议[no]
     *   --enable-libssh通过libssh启用SFTP协议[no]
     *   --enable-libtesseract启用tesseract，ocr过滤器需要[no]
     *   --enable-libtheora通过libtheora启用Theora编码[no]
     *   --enable-libtls启用了LibreSSL（通过libtls），这是https支持所必需的
     *                            如果没有使用openssl或gnutls [no]
     *   --enable-libtwolame通过libtwolame启用MP2编码[no]
     *   --enable-libv4l2启用libv4l2 / v4l-utils [no]
     *   --enable-libvidstab使用vid.stab [no]启用视频稳定
     *   --enable-libvmaf通过libvmaf启用vmaf过滤器[no]
     *   --enable-libvo-amrwbenc通过libvo-amrwbenc [no]启用AMR-WB编码
     *   --enable-libvorbis通过libvorbis启用Vorbis en /解码，
     *                            本机实现存在[否]
     *   --enable-libvpx通过libvpx启用VP8和VP9 de / encoding [no]
     *   --enable-libwavpack通过libwavpack启用wavpack编码[no]
     *   --enable-libwebp通过libwebp启用WebP编码[no]
     *   --enable-libx264通过x264启用H.264编码[no]
     *   --enable-libx265通过x265启用HEVC编码[no]
     *   --enable-libxavs通过xavs启用AVS编码[no]
     *   --enable-libxcb使用XCB启用X11抓取[autodetect]
     *   --enable-libxcb-shm启用X11抓取shm通信[autodetect]
     *   --enable-libxcb-xfixes启用X11抓取鼠标渲染[autodetect]
     *   --enable-libxcb-shape启用X11抓取形状渲染[autodetect]
     *   --enable-libxvid通过xvidcore启用Xvid编码，
     *                            原生MPEG-4 / Xvid编码器存在[no]
     *   --enable-libxml2使用C库libxml2 [no]启用XML解析
     *   --enable-libzimg启用z.lib，zscale过滤器需要[no]
     *   --enable-libzmq启用通过libzmq传递的消息[no]
     *   --enable-libzvbi通过libzvbi启用图文电视支持[no]
     *   --enable-lv2启用LV2音频过滤[否]
     *   --disable-lzma disable lzma [autodetect]
     *   --enable-decklink启用Blackmagic DeckLink I / O支持[no]
     *   --enable-libndi_newtek启用Newteck NDI I / O支持[no]
     *   --enable-mediacodec支持Android MediaCodec [no]
     *   --enable-libmysofa启用libmysofa，sofalizer过滤器需要[no]
     *   --enable-openal启用OpenAL 1.1捕获支持[no]
     *   --enable-opencl启用OpenCL处理[no]
     *   --enable-opengl启用OpenGL渲染[no]
     *   --enable-openssl启用openssl，https支持所需
     *                            如果不使用gnutls或libtls [no]
     *   --disable-sndio禁用sndio支持[autodetect]
     *   --disable-schannel禁用SChannel SSP，需要TLS支持
     *                            Windows如果没有使用openssl和gnutls [autodetect]
     *   --disable-sdl2禁用sdl2 [autodetect]
     *   --disable-securetransport禁用TLS支持所需的安全传输
     *                            在OSX上如果没有使用openssl和gnutls [autodetect]
     *   --disable-xlib禁用xlib [autodetect]
     *   --disable-zlib禁用zlib [autodetect]

     * 以下库提供各种硬件加速功能：
     *   --disable-amf禁用AMF视频编码代码[autodetect]
     *   --disable-audiotoolbox禁用Apple AudioToolbox代码[autodetect]
     *   --enable-cuda-sdk启用需要CUDA SDK的CUDA功能[否]
     *   --disable-cuvid禁用Nvidia CUVID支持[autodetect]
     *   --disable-d3d11va禁用Microsoft Direct3D 11视频加速代码[autodetect]
     *   --disable-dxva2禁用Microsoft DirectX 9视频加速代码[autodetect]
     *   --disable-ffnvcodec禁用动态链接的Nvidia代码[autodetect]
     *   --enable-libdrm启用DRM代码（Linux）[no]
     *   --enable-libmfx通过libmfx [no]启用Intel MediaSDK（AKA快速同步视频）代码
     *   --enable-libnpp启用基于Nvidia Performance Primitives的代码[no]
     *   --enable-mmal通过MMAL启用Broadcom多媒体抽象层（Raspberry Pi）[no]
     *   --disable-nvdec禁用Nvidia视频解码加速（通过hwaccel）[autodetect]
     *   --disable-nvenc禁用Nvidia视频编码代码[autodetect]
     *   --enable-omx启用OpenMAX IL代码[no]
     *   --enable-omx-rpi为Raspberry Pi启用OpenMAX IL代码[no]
     *   --enable-rkmpp启用Rockchip Media Process Platform代码[no]
     *   --disable-v4l2-m2m禁用V4L2 mem2mem代码[autodetect]
     *   --disable-vaapi禁用视频加速API（主要是Unix / Intel）代码[autodetect]
     *   --disable-vdpau禁用Nvidia Video Decode和Presentation API for Unix code [autodetect]
     *   --disable-videotoolbox禁用VideoToolbox代码[autodetect]

     * 工具链选项：
     *   --arch = ARCH select architecture []
     *   --cpu = CPU选择所需的最小CPU（影响
     *                            指令选择，可能会在较旧的CPU上崩溃）
     *   --cross-prefix = PREFIX使用PREFIX编译工具[]
     *   --progs-suffix = SUFFIX程序名后缀[]
     *   --enable-cross-compile假设使用了交叉编译器
     *   --sysroot =交叉构建树的PATH根
     *   --sysinclude =交叉构建系统头的PATH位置
     *   --target-os = OS编译器目标OS []
     *   --target-exec = CMD命令在目标上运行可执行文件
     *   --target-path =在目标上查看构建目录的DIR路径
     *   --target-samples =目标上样本目录的DIR路径
     *   --tempprefix = PATH强制修复dir /前缀而不是mktemp进行检查
     *   --toolchain = NAME根据NAME设置工具默认值
     *                            （gcc-asan，clang-asan，gcc-msan，clang-msan，
     *                            gcc-tsan，clang-tsan，gcc-usan，clang-usan，
     *                            valgrind-massif，valgrind-memcheck，
     *                            msvc，icl，gcov，llvm-cov，hardened）
     *   --nm = NM使用nm工具NM [nm -g]
     *   --ar = AR使用存档工具AR [ar]
     *   --as = AS使用汇编程序AS []
     *   --ln_s = LN_S使用符号链接工具LN_S [ln -s -f]
     *   --strip = STRIP使用剥离工具STRIP [strip]
     *   --windres = WINDRES使用windows资源编译器WINDRES [windres]
     *   --x86asmexe = EXE使用nasm兼容的汇编程序EXE [nasm]
     *   --cc = CC使用C编译器CC [gcc]
     *   --cxx = CXX使用C编译器CXX [g ++]
     *   --objcc = OCC使用ObjC编译器OCC [gcc]
     *   --dep-cc = DEPCC使用依赖关系生成器DEPCC [gcc]
     *   --nvcc = NVCC使用Nvidia CUDA编译器NVCC [nvcc]
     *   --ld = LD使用链接器LD []
     *   --pkg-config = PKGCONFIG使用pkg-config工具PKGCONFIG [pkg-config]
     *   --pkg-config-flags = FLAGS将附加标志传递给pkgconf []
     *   --ranlib = RANLIB使用ranlib RANLIB [ranlib]
     *   --doxygen = DOXYGEN使用DOXYGEN生成API doc [doxygen]
     *   --host-cc = HOSTCC使用主机C编译器HOSTCC
     *   --host-cflags = HCFLAGS在编译主机时使用HCFLAGS
     *   --host-cppflags = HCPPFLAGS在编译主机时使用HCPPFLAGS
     *   --host-ld = HOSTLD使用主机链接器HOSTLD
     *   --host-ldflags = HLDFLAGS在链接主机时使用HLDFLAGS
     *   --host-libs = HLIBS在链接主机时使用libs HLIBS
     *   --host-os = OS编译器主机OS []
     *   --extra-cflags = ECFLAGS将ECFLAGS添加到CFLAGS []
     *   --extra-cxxflags = ECFLAGS将ECFLAGS添加到CXXFLAGS []
     *   --extra-objcflags = FLAGS将标志添加到OBJCFLAGS []
     *   --extra-ldflags = ELDFLAGS将ELDFLAGS添加到LDFLAGS []
     *   --extra-ldexeflags = ELDFLAGS将ELDFLAGS添加到LDEXEFLAGS []
     *   --extra-ldsoflags = ELDFLAGS将ELDFLAGS添加到LDSOFLAGS []
     *   --extra-libs = ELIBS add ELIBS []
     *   --extra-version = STRING版本字符串后缀[]
     *   --optflags = OPTFLAGS覆盖与优化相关的编译器标志
     *   --nvccflags = NVCCFLAGS覆盖nvcc标志[-gencode arch = compute_30，code = sm_30 -O2]
     *   --build-suffix = SUFFIX库名后缀[]
     *   --enable-pic构建与位置无关的代码
     *   Thumb指令集的--enable-thumb编译
     *   --enable-lto使用链接时优化
     *   --env =“ENV = override”覆盖环境变量　

     * 高级选项（仅限专家）：
     *   --malloc-prefix = PREFIX前缀malloc和PREFIX的相关名称
     *   --custom-allocator = NAME使用受支持的自定义分配器
     *   --disable-symver禁用符号版本控制
     *   --enable-hardcoded-tables使用硬编码表而不是运行时生成
     *    - 禁用安全比特流阅读器
     *                            在bitreaders中禁用缓冲区边界检查
     *                            （更快，但可能会崩溃）
     *   --sws-max-filter-size = N swscale使用的最大过滤器大小[256]

     * 优化选项（仅限专家）：
     *   --disable-asm禁用所有程序集优化
     *   --disable-altivec禁用AltiVec优化
     *   --disable-vsx禁用VSX优化
     *   --disable-power8禁用POWER8优化
     *   --disable-amd3dnow禁用3DNow！优化
     *   --disable-amd3dnowext禁用3DNow！扩展优化
     *   --disable-mmx禁用MMX优化
     *   --disable-mmxext禁用MMXEXT优化
     *   --disable-sse禁用SSE优化
     *   --disable-sse2禁用SSE2优化
     *   --disable-sse3禁用SSE3优化
     *   --disable-ssse3禁用SSSE3优化
     *   --disable-sse4禁用SSE4优化
     *   --disable-sse42禁用SSE4.2优化
     *   --disable-avx禁用AVX优化
     *   --disable-xop禁用XOP优化
     *   --disable-fma3禁用FMA3优化
     *   --disable-fma4禁用FMA4优化
     *   --disable-avx2禁用AVX2优化
     *   --disable-avx512禁用AVX-512优化
     *   --disable-aesni禁用AESNI优化
     *   --disable-armv5te禁用armv5te优化
     *   --disable-armv6禁用armv6优化
     *   --disable-armv6t2禁用armv6t2优化
     *   --disable-vfp禁用VFP优化
     *   --disable-neon禁用NEON优化
     *   --disable-inline-asm禁用内联汇编
     *   --disable-x86asm禁用独立x86程序集
     *   --disable-mipsdsp禁用MIPS DSP ASE R1优化
     *   --disable-mipsdspr2禁用MIPS DSP ASE R2优化
     *   --disable-msa禁用MSA优化
     *   --disable-mipsfpu禁用浮点MIPS优化
     *   --disable-mmi禁用龙芯SIMD优化
     *   --disable-fast-unaligned认为未对齐的访问速度很慢

     * 开发人员选项（在处理FFmpeg时很有用）：
     *   --disable-debug禁用调试符号
     *   --enable-debug = LEVEL设置调试级别[]
     *   --disable-optimizations禁用编译器优化
     *   --enable-extra-warnings可以启用更多编译器警告
     *   --disable-stripping禁用剥离可执行文件和共享库
     *   --assert-level = 0级（默认），1或2，断言测试量，
     *                            2导致运行时减速。
     *   --enable-memory-poisoning fill heap未初始化的已分配空间和任意数据
     *   --valgrind = VALGRIND通过valgrind运行“make fate”测试来检测内存
     *                            泄漏和错误，使用指定的valgrind二进制文件。
     *                            不能与--target-exec结合使用
     *   --enable-ftrapv陷阱算术溢出
     *   --samples = FATE的测试样本的PATH位置，如果没有设置使用
     *                            make调用时的$ FATE_SAMPLES。
     *   --enable-neon-clobber-test检查NEON寄存器用于破坏（应该是
     *                            仅用于调试目的）
     *   --enable-xmm-clobber-test检查XMM注册用于clobbering（仅限Win64;
     *                            应该只用于调试目的）
     *   --enable-random随机启用/禁用组件
     *   --disable随机
     *   --enable-random = LIST随机启用/禁用特定组件或
     *   --disable-random = LIST组件组。 LIST是以逗号分隔的列表
     *                            NAME是组件的NAME [：PROB]条目
     *                            （组）和PROB相关的概率
     *                            NAME（默认为0.5）。
     *   --random-seed = --enable / disable-random的VALUE种子值
     *   --disable-valgrind-backtrace不会在Valgrind下打印回溯
     *                            （仅适用于--disable-optimizations构建）
     *   --enable-osfuzz启用构建模糊工具
     *   --libfuzzer = libfuzzer的路径
     *   --ignore-tests = TESTS以逗号分隔的列表（没有“fate-”前缀
     *                            在名称中）的结果被忽略的测试
     *   --enable-linux-perf启用Linux性能监视器API
     */


    /**
     * sh linux编写 静态库
     *
     #!/bin/bash
     # 设置临时文件夹，需要提前手动创建
     export TMPDIR="/usr/myfile/ffmpeg-4.0.4/buildlib"
     # 设置NDK路径
     NDK=/usr/ndk/android-ndk-r20
     # 设置编译针对的平台，可以根据实际需求进行设置
     # 当前设置为最低支持android-21版本，arm架构
     SYSROOT=$NDK/platforms/android-21/arch-arm
     # 设置编译工具链，4.9为版本号
     TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64
     # 执行configure脚本：生成makefile
     #--prefix：安装目录
     #--enable-small：优化大小
     #--disable-programs ：不编译ffmpeg程序(命令行工具)，我们是需要获得静态（动态）库
     #--disable-avdevice：关闭avdevice 模块 ，此模块在android中 无用
     #--disable-encoders : 关闭所有编码器(播放不需要编码器)
     #--disable-muxers ：关闭所有复用器（封装器）不需要生成mp4的文件 所以关闭
     #--disable-filters ： 关闭视频滤镜
     #--enable-cross-compile ：开启交叉编译（ffmpeg比较**跨平台**并不是所有的库都有这么happy的选项）
     #--cross-prefix：
     #--disable-shared :enable-static 不写也可以 默认就是静态的
     #--sysroot:
     #--extra-cflags：会传给gcc的参数
     #arch --target-os：
     ./configure
     --enable-cross-compile \
     --disable-shared \
     --enable-static \
     --enable-small \
     --disable-programs \
     --disable-avdevice \
     --disable-encoders \
     --disable-muxers \
     --disable-filters \
     --prefix=$PREFIX \
     --cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
     --target-os=android \
     --arch=arm \
     --sysroot=$SYSROOT \
     --extra-cflags="-Os -fpic $ADDI_CFLAGS" \
     $ADDITIONAL_CONFIGURE_FLAG

     make clean
     make install
     # 设置编译后文件的输出目录
     CPU=arm
     PREFIX=$(pwd)/android/$CPU
     ADDI_CFLAGS="-marm"
     build_one
     */








}
