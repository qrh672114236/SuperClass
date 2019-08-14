package com.example.superclass.media.ffmpeg.dongnao;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DNPlayer implements SurfaceHolder.Callback {

    private String dataSource;
    private SurfaceHolder holder;
    private onPrepareListener listener;

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;

    }


    public void setSurfaceView(SurfaceView surfaceView) {
        holder = surfaceView.getHolder();
        holder.addCallback(this);


    }

    /**
     * 准备播放视频
     */
    public void prepare() {

    }

    public void start() {
        native_start();
    }

    public void stop() {
    }

    public void release() {
        holder.removeCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void onPrepare() {
        if (null != listener) {
            listener.onPrepare();
        }

    }

    public interface onPrepareListener {
        void onPrepare();
    }

    ;

    public void setOnPrepareListener(onPrepareListener listener) {
        this.listener = listener;
    }


    public void onError(int errorCode) {

    }

    native void native_prepare(String dataSource);

    native void native_start();
}
