package com.example.yunlog.kotlin

public abstract class YunLogConfig() {

    public abstract fun getGlobalTag(): String

    public abstract fun getEnable(): Boolean

    fun enableThread(): Boolean = true

    fun enableStackTrace(): Boolean = true

    fun getPrinters(): MutableList<YunLogPrinter> {
        return arrayListOf()
    }

    open fun injectJsonParser(): JsonParser? {
        return null
    }

    fun getStackTraceDepth(): Int = 5

    companion object {
        const val TAG = "YunLogConfig"
        val YUN_THREAD_FORMATTER = YunLogThreadFormatter()
        val YUN_STACK_TRACE_FORMATTER = YunLogStackTraceFormatter()
    }

    interface JsonParser {
        fun toJson(any: Any): String
    }

}