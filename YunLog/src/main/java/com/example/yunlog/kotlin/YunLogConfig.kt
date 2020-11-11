package com.example.yunlog.kotlin

public abstract class YunLogConfig() {

    public abstract fun getGlobalTag(): String

    public abstract fun getEnable() :Boolean

    companion object {
        const val TAG = "YunLogConfig"
    }

}