package com.example.yunlog.kotlin

class YunLogStackTraceFormatter : YunLogFormatter<MutableList<StackTraceElement>> {
    override fun format(data: MutableList<StackTraceElement>): String {
        return data.toString()
    }
}