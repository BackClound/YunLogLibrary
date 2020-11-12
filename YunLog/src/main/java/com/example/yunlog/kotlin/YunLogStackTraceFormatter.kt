package com.example.yunlog.kotlin

class YunLogStackTraceFormatter : YunLogFormatter<MutableList<StackTraceElement>> {
    override fun format(data: MutableList<StackTraceElement>): String? {

        if (data.isNullOrEmpty()) {
            return null
        }

        if (data.size == 1) {
            return data[0].toString()
        }
        val logString = StringBuffer()

        for (element in data) {

            when(element){
                data.first() -> {
                    logString.append("\t StackTrace: \n").append("\t |- ").append(element.toString()).append("\n")
                }
                data.last() -> {
                    logString.append("\t |_").append(element.toString()).append("\n")
                }
                else -> {
                    logString.append("\t |- ").append(element.toString())
                }
            }
        }

        return data.toString()
    }
}