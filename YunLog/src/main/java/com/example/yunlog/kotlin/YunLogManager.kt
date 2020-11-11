package com.example.yunlog.kotlin


class YunLogManager(val config: YunLogConfig, val logPrinters: MutableList<YunLogPrinter>) {

    val printers = logPrinters

    fun addPrinter(printer: YunLogPrinter) {
        printers.add(printer)
    }

    fun removePrinter(printer: YunLogPrinter) {
        printers.remove(printer)
    }

    fun removeAll() {
        printers.clear()
    }

    companion object {
        const val TAG = "YunLogManager"
        lateinit var mInstance: YunLogManager


        fun newInstance(config: YunLogConfig, logPrinters: MutableList<YunLogPrinter>) {
            mInstance = YunLogManager(config, logPrinters)
        }
    }

}