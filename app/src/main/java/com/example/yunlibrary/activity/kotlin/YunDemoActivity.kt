package com.example.yunlibrary.activity.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yunlibrary.R
import com.example.yunlog.java.YunLogManager
import com.example.yunlog.java.YunLogViewPrinter
import com.example.yunlog.kotlin.YunLog
import com.example.yunlog.kotlin.YunLogConfig
import com.example.yunlog.kotlin.YunLogType
import kotlinx.android.synthetic.main.activity_demo_layout.*
typealias JavaYunLog = com.example.yunlog.java.YunLog

class YunDemoActivity : AppCompatActivity() {

    var printer: YunLogViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_demo_layout)
        textview_print_log.setOnClickListener {
            printLog()
        }

        printer = YunLogViewPrinter(this)
        printer?.provider?.showFloatingView()
    }

    fun printLog() {
//        YunLog.e(null, "Kotlin : print the E level log")
//        JavaYunLog.e(listOf("Java : print the E level log"))
        YunLogManager.mInstance.addPrinter(printer)
        YunLog.log(object : YunLogConfig() {
            override fun getGlobalTag(): String {
                return " this is a customer Tag"
            }

            override fun getEnable(): Boolean {
                return true
            }
        },YunLogType.E, "3366", "5500")

        JavaYunLog.e("Java TAG", "Java : print the E Level Log")
    }

}