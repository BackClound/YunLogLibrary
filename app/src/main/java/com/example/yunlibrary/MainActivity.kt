package com.example.yunlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yunlibrary.common.activity.kotlin.YunDemoActivity
import com.example.yunlog.kotlin.YunLog
import kotlinx.android.synthetic.main.activity_main.*
typealias JavaYunLog = com.example.yunlog.java.YunLog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_log.setOnClickListener {
            startActivity(Intent(this, YunDemoActivity::class.java))
        }
    }

}