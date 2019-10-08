package com.example.pluginapp

import android.content.Intent
import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)


        findViewById(R.id.btn).setOnClickListener {
            //由于是加载类 所有没有注册上下文 只能使用传过来的that
             var intent= Intent(that,SecondActivity::class.java)

            startActivity(intent)
        }
    }
}
