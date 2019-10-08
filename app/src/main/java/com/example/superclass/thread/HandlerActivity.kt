package com.example.superclass.thread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.superclass.R
import kotlinx.android.synthetic.main.activity_handler.*
import kotlin.concurrent.thread




class HandlerActivity : AppCompatActivity() {

    private lateinit var threadLooper:Looper

    private lateinit var threadHandler: Handler

    private var mainHandler:Handler=object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            Log.e("mainMsg===",msg?.obj.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        initThread()

        main.setOnClickListener { //主线程发送消息给子线程
            var msg = Message.obtain()

            msg.obj="主线程给子线程发送的消息"

            threadHandler.sendMessage(msg)


        }

        thread.setOnClickListener { //子线程发送消息给主线程
            var msg = Message.obtain()

            msg.obj="子线程给主线程发送的消息"

            mainHandler.sendMessage(msg)
        }


    }

    private fun initThread() {
        thread(start=true){
            //初始化looper
            Looper.prepare()
            threadLooper= Looper.myLooper()
            threadHandler=object :Handler(threadLooper){
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    Log.e("threadMsg===",msg?.obj.toString())
                }
            }
            //启动消息循环(正常是在ActivityThread 创建Lopper之后调用)
            Looper.loop()
        }
    }
}
