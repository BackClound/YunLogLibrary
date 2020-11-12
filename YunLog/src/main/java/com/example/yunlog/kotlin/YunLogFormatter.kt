package com.example.yunlog.kotlin

interface YunLogFormatter<T> {
    fun format(data: T): String?
}