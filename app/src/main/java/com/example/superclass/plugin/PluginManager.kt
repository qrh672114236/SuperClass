@file:Suppress("DEPRECATION")

package com.example.superclass.plugin

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import dalvik.system.DexClassLoader
import java.io.File

object PluginManager {

    private var packageeInfo: PackageInfo? = null
    private var dexClassLoader: DexClassLoader? = null
    private var resources: Resources? = null

    fun loadPath(context: Context) {
        var fileDir = context.getDir("plugin", Context.MODE_PRIVATE)
        var apkName = "plugin.apk"
        var path = File(fileDir, apkName).absolutePath

        val packageManager = context.packageManager
        packageeInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
        var dex = context.getDir("dex", Context.MODE_PRIVATE)

        dexClassLoader = DexClassLoader(path, dex.absolutePath, null, context.classLoader)
        try {
            val manager = AssetManager::class.java.newInstance()
            val method = manager::class.java.getMethod("addAssetPath", String::class.java)
            method.invoke(manager, path)

            //获取资源文件
            resources = Resources(manager, context.resources.displayMetrics, context.resources.configuration)
        } catch (e: Exception) {

        }
    }

    fun getResources(): Resources? {
        return resources
    }

    fun getDexClassLoader(): DexClassLoader? {
        return dexClassLoader
    }

    fun getPackageInfo(): PackageInfo? {
        return packageeInfo
    }
}