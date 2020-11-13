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

    public fun log(config: YunLogConfig, @YunLogType.Type type: Int, tag: String?, vararg parameters: Any) {
        val logString = StringBuffer()
        if (!config.getEnable()) {
            return
        }
        if (config.enableThread()) {
            val threadLog = YunLogConfig.YUN_THREAD_FORMATTER.format(Thread.currentThread())
            logString.append(threadLog).append("\n")
        }

        if (config.enableStackTrace() && config.getStackTraceDepth() > 0) {
            val stackTraceLog = YunLogConfig.YUN_STACK_TRACE_FORMATTER.format(Throwable().stackTrace.toMutableList())
            logString.append(stackTraceLog).append("\n")
        }

        logString.append(parseMessage(config, *parameters))
        val logPrinters = config.getPrinters()?.let {
           if (it.isEmpty()) YunLogManager.mInstance.printers else it
        }

        if (logPrinters.isEmpty()) {
            return
        }
        for (printer in logPrinters) {
            printer.print(config, type, tag, logString.toString())
        }
        Log.println(type, tag, logString.toString())
    }

    private fun parseMessage(config: YunLogConfig, vararg parameters: Any): String? {
        val buffer = StringBuffer()

        if (config.injectJsonParser() != null) {
            return config.injectJsonParser()?.toJson(parameters)
        }

        for (o in parameters) {
            buffer.append(o).append(";")
        }
        if (buffer.isNotEmpty()){
            buffer.deleteCharAt(buffer.length -1)
        }

        return buffer.toString()
    }


}