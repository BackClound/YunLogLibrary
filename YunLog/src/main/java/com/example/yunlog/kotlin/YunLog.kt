package com.example.yunlog.kotlin

import android.util.Log

/**
 * interface oriented programming
 *this is used for user to print logs
 *
 * it seems we can not use kotlin to print our logs, because it only print the memory address
 *
 * 当我们调用 vararg-函数时，我们可以一个接一个地传参，例如 asList(1, 2, 3)，或者，如果我们已经有一个数组并希望将其内容传给该函数，
 * 我们使用伸展（spread）操作符（在数组前面加 *）：
 * val a = arrayOf(1, 2, 3)
 * val list = asList(-1, 0, *a, 4)
 */
object YunLog {
    fun e(tag: String? = null, vararg parameters: Any) {
        tag?.let {
            log(YunLogManager.mInstance.config, YunLogType.E, it, *parameters)
        } ?: run {
            log(YunLogManager.mInstance.config, YunLogType.E, YunLogManager.TAG, *parameters)
        }
    }

    fun i(tag: String? = null, vararg parameters: String) {
        tag?.let {
            log(YunLogManager.mInstance.config, YunLogType.I, it, *parameters)
        } ?: log(YunLogManager.mInstance.config, YunLogType.I, YunLogManager.TAG, *parameters)

    }

    fun d(tag: String? = null, vararg parameters: Any) {

    }

    private fun log(config: YunLogConfig, @YunLogType.Type type: Int, tag: String?, vararg parameters: Any) {
        if (!config.getEnable()) {
            return
        }
        val message = parseMessage(*parameters)
        Log.println(type, tag, message)
    }

    private fun parseMessage(vararg parameters: Any): String {
        val buffer = StringBuffer()
        for (o in parameters) {
            buffer.append(o)
        }

        return buffer.toString()
    }


}