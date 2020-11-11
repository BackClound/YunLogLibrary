package com.example.yunlog.kotlin

interface YunLogPrinter {
    open fun print(config: YunLogConfig, @YunLogType.Type type: Int, tag : String?, printString: String)
}