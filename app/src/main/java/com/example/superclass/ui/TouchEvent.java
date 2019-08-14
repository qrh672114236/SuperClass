package com.example.superclass.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * 事件分发
 */
public class TouchEvent extends ViewGroup {
    public TouchEvent(Context context) {
        super(context);
    }

    public TouchEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);

    }


    //dispatchTouchEvent



}
