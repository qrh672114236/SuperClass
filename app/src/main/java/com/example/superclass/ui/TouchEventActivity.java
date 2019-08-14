package com.example.superclass.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.superclass.R;

/**
 * 事件分发
 *
 * 滑动冲突解决方案 ：
 *      1.外部拦截 ：父控件 onInterceptTouchEvent 方法中 判断是否需要拦截
 *
 *      2.内部拦截 ：子View dispatchTouchEvent() 中使用 requestDisallowInterceptTouchEvent来设置父控件是否拦截
 *
 *
 *
 *
 */
public class TouchEventActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        imageView=findViewById(R.id.image);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //调取view的onclick（）
                v.performClick();

                //事件消费 不往下传递
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //requestDisallowInterceptTouchEvent 子view设置父是否拦截
        //viewGroup中判断是否拦截
//        if (onInterceptTouchEvent(ev)){
//            //如果拦截 调用自身onTouchEvent
//            boolean=onTouchEvent(ev);
//        }else{
//            //如果未拦截 调子view的ontouch
//            boolean=child.onTouchEvent(ev);
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //requestDisallowInterceptTouchEvent 设置父是否拦截


        //activity->viewGroup dispatchTouchEvent(dispatchTransformedTouchEvent)

        //viewGroup 下发给每个View touchEvent

        return super.onTouchEvent(event);

    }
}
