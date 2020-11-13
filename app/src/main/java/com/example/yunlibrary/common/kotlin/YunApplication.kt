package com.example.yunlibrary.common.kotlin

import android.app.Application
import com.example.yunlog.kotlin.*
import com.google.gson.Gson

typealias JavaYunLogConfig = com.example.yunlog.java.YunLogConfig
typealias JavaYunLogManager = com.example.yunlog.java.YunLogManager
typealias JavaYunLogPrinter = com.example.yunlog.java.YunLogPrinter
typealias JavaYunConsolePrinter = com.example.yunlog.java.YunConsolePrinter
typealias JavaYunLogJsonParser = com.example.yunlog.java.YunLogConfig.JsonParser

class YunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        YunLogManager.newInstance(object : YunLogConfig() {
            override fun injectJsonParser(): JsonParser? {
                return object :JsonParser {
                    override fun toJson(any: Any): String {
                        return Gson().toJson(any)
                    }
                }
            }

            override fun getGlobalTag(): String {
                return "YunApplicationKotlin"
            }

            override fun getEnable(): Boolean {
                return true
            }
        }, mutableListOf<YunLogPrinter>(YunConsolePrinter()))


        JavaYunLogManager.initInstance(object : JavaYunLogConfig() {
            override fun injectJsonParser(): JavaYunLogJsonParser {
                return JavaYunLogJsonParser { src-> Gson().toJson(src) }
            }

            override fun getEnable(): Boolean {
                return true
            }

            override fun getGlobalTag(): String {
                return "YunApplicationJava"
            }

        }, arrayListOf<JavaYunLogPrinter>(JavaYunConsolePrinter()))
    }
}