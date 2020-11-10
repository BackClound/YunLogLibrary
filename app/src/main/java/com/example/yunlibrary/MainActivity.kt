package com.example.yunlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yunlog.kotlin.YunLog
import kotlinx.android.synthetic.main.activity_main.*
import com.example.yunlog.java.YunLog as JavaYunLog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_log.setOnClickListener {
            YunLog.i("test one", "kotlin: the Log level is I, Button has been pressed")
            JavaYunLog.i("test two","java: the Log level is I, Button has been pressed")

            YunLog.e(null, "kotlin: the Log level is E, Button has been pressed")

            JavaYunLog.e(arrayListOf("java: the Log level is E, Button has been pressed"))
        }
    }
}