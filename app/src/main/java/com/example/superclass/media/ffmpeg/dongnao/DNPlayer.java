package com.example.superclass.media.ffmpeg.dongnao;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DNPlayer implements SurfaceHolder.Callback {

    static {
        System.loadLibrary("dongnao");
    }

    private String dataSource;
    private SurfaceHolder holder;
    private onPrepareListener listener;

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;

    }


    public void setSurfaceView(SurfaceView surfaceView) {
        if(null==holder){
            holder.removeCallback(this);
        }
        holder = surfaceView.getHolder();
        holder.addCallback(this);


    }

    /**
     * 准备播放视频
     */
    public void prepare() {
        native_prepare(dataSource);
    }

    public void start() {
        native_start();
    }

    public void stop() {
    }

    public void release() {
        holder.removeCallback(this);
        native_release();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        native_setSurface(holder.getSurface());

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        native_setSurface(holder.getSurface());
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
        System.out.println("Java接到回调:"+errorCode);
    }

    native void native_prepare(String dataSource);

    native void native_start();

    native void native_setSurface(Surface surface);

    native void native_release();

    native void native_stop();
}
