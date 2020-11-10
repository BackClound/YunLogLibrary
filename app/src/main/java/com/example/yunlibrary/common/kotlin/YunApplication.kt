package com.example.yunlibrary.common.kotlin

import android.app.Application
import com.example.yunlog.kotlin.YunLogConfig
import com.example.yunlog.kotlin.YunLogManager
import com.example.yunlog.java.YunLog as JavaYunLog
import com.example.yunlog.java.YunLogConfig as JavaYunLogConfig
import com.example.yunlog.java.YunLogManager as JavaYunLogManager

class YunApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        YunLogManager.setConfig(YunLogConfig(true))

        val config = JavaYunLogConfig()
        config.enable = true
        JavaYunLogManager.initInstance(config)
    }
}