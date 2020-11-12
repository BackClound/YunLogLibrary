package com.example.yunlog.kotlin

class YunLogThreadFormatter: YunLogFormatter<Thread> {
    override fun format(data: Thread): String? {
        return "Yun Thread putout:" + data.name
    }
}