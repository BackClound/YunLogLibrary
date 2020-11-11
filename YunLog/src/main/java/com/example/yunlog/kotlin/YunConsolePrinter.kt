package com.example.yunlog.kotlin

import android.util.Log
import com.example.yunlog.java.YunLogConfig.LINE_MAX_LENGTH

class YunConsolePrinter : YunLogPrinter {
    override fun print(config: YunLogConfig, type: Int, tag: String, printString: String) {
        val len = printString.length
        val depth = len / LINE_MAX_LENGTH

        when (depth) {
            0 -> {
                Log.println(type, tag, printString)
            }
            else -> {
                var index = 0
                for (subLength in 0..depth) {
                    Log.println(type, tag, printString.substring(index, index + LINE_MAX_LENGTH))
                    index += LINE_MAX_LENGTH
                }

                if (index != len) {
                    Log.println(type, tag, printString.substring(index, len))
                }
            }
        }

    }
}