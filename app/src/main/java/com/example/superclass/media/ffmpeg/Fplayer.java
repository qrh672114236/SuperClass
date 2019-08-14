package com.example.superclass.media.ffmpeg;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Fplayer implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;

    public void setSurfaceView(SurfaceView surfaceView){
        if (null!=this.surfaceHolder){
            this.surfaceHolder.removeCallback(this);
        }
        this.surfaceHolder=surfaceView.getHolder();
        this.surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.surfaceHolder=holder;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    //播放
    public void start(String path) {
        native_statr(path,surfaceHolder.getSurface());
    }
    public native void native_statr(String path, Surface surface);

    public native String test();

    public String testStr(){
        return test();
    }
}
