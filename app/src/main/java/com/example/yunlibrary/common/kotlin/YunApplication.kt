package com.example.yunlibrary.common.kotlin

import android.app.Application
import com.example.yunlog.kotlin.*

typealias JavaYunLogConfig = com.example.yunlog.java.YunLogConfig
typealias JavaYunLogManager = com.example.yunlog.java.YunLogManager
typealias JavaYunLogPrinter = com.example.yunlog.java.YunLogPrinter
typealias JavaYunConsolePrinter = com.example.yunlog.java.YunConsolePrinter

class YunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        YunLogManager.newInstance(object : YunLogConfig() {
            override fun getGlobalTag(): String {
                return "YunApplicationKotlin"
            }

            override fun getEnable(): Boolean {
                return true
            }
        }, mutableListOf<YunLogPrinter>(YunConsolePrinter()))


        JavaYunLogManager.initInstance(object : JavaYunLogConfig() {
            override fun getEnable(): Boolean {
                return true
            }

            override fun getGlobalTag(): String {
                return "YunApplicationJava"
            }

        }, arrayListOf<JavaYunLogPrinter>(JavaYunConsolePrinter()))
    }
}