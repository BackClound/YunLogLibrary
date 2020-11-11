package com.example.yunlibrary.activity.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yunlibrary.R
import com.example.yunlog.kotlin.YunLog
import kotlinx.android.synthetic.main.activity_demo_layout.*
typealias JavaYunLog = com.example.yunlog.java.YunLog

class YunDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_demo_layout)
        textview_print_log.setOnClickListener {
            printLog()
        }
    }

    fun printLog() {
        YunLog.e(null, "Kotlin : print the E level log")
        JavaYunLog.e(listOf("Java : print the E level log"))
        JavaYunLog.e("Java TAG", "Java : print the E Level Log")
    }
}