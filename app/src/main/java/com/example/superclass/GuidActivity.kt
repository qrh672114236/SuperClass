package com.example.superclass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.superclass.aop.AopJavaActivity
import com.example.superclass.eventbus.test.EventBusActivity
import com.example.superclass.eventbus.test.ReciverActivity
import com.example.superclass.framework.mvvm.databinding.DataBindingActivity
import com.example.superclass.plugin.PluginMainActivity
import com.example.superclass.thread.HandlerActivity
import kotlinx.android.synthetic.main.activity_guid.*
import org.jetbrains.anko.startActivity

class GuidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guid)


        btn1.setOnClickListener { startActivity<DataBindingActivity>() }


        btn2.setOnClickListener { startActivity<HandlerActivity>() }

        btn3.setOnClickListener { startActivity<PluginMainActivity>() }

        btn4.setOnClickListener { startActivity<AopJavaActivity>() }

        btn5.setOnClickListener { startActivity<ReciverActivity>() }
    }
}

