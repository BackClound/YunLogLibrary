package com.example.yunlibrary.common.kotlin

import android.app.Application
import com.example.yunlog.kotlin.YunLogConfig
import com.example.yunlog.kotlin.YunLogManager
typealias JavaYunLogConfig = com.example.yunlog.java.YunLogConfig
typealias JavaYunLogManager = com.example.yunlog.java.YunLogManager

class YunApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        YunLogManager.setConfig(object :YunLogConfig() {
            override fun getGlobalTag(): String {
                return "YunApplicationKotlin"
            }

            override fun getEnable(): Boolean {
                return true
            }
        })


        JavaYunLogManager.initInstance(object : JavaYunLogConfig(){
            override fun getEnable(): Boolean {
                return true
            }

            override fun getGlobalTag(): String {
                return "YunApplicationJava"
            }

        })
    }
}