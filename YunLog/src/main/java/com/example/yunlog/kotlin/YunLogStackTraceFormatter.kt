package com.example.yunlog.kotlin

class YunLogStackTraceFormatter : YunLogFormatter<StackTraceElement> {
    override fun format(data: StackTraceElement): String {
        return data.className
    }
}