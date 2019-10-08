package com.example.superclass.plugin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.example.superclass.R
import kotlinx.android.synthetic.main.activity_plugin_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.*
import java.lang.Exception

class PluginMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin_main)

        load.setOnClickListener {
            loadPlugin()

        }
        step.setOnClickListener {
            //activities 数组 是按照 manifest 顺序存储的

            startActivity<ProxyActivity>("className" to PluginManager.getPackageInfo()!!.activities[0].name)
        }
    }

    private fun loadPlugin() {
        var filesDir = this.getDir("plugin", Context.MODE_PRIVATE)
        var apkName = "plugin.apk"
        var filePath = File(filesDir, apkName).absolutePath
        var file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        var ins: InputStream? = null
        var os: FileOutputStream? = null

        try {
            ins = FileInputStream(File(Environment.getExternalStorageDirectory(), apkName))
            os = FileOutputStream(filePath)
            var len = 0
            var buffer = ByteArray(1024)

            while(((ins.read(buffer)).also { it-> len=it})!=-1){
                os.write(buffer,0,len)
            }
            var f=File(filePath)
            if (f.exists()){
                toast("dex overWrite")
            }
            PluginManager.loadPath(this)
        }catch (e:IOException){

        }finally {
            try {
                os?.close()
                ins?.close()
            }catch (e:Exception){

            }
        }


    }
}
