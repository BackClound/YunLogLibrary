package com.example.yunlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yunlibrary.activity.java.YunBottomLayoutActivity
import com.example.yunlibrary.activity.java.YunTopLayoutActivity
import com.example.yunlibrary.activity.kotlin.YunDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_log.setOnClickListener {
            startActivity(Intent(this, YunDemoActivity::class.java))
        }

        textview_bottom_view.setOnClickListener {
            startActivity(Intent(this, YunBottomLayoutActivity::class.java))
        }

        textview_top_view.setOnClickListener {
            startActivity(Intent(this, YunTopLayoutActivity::class.java))
        }
    }

}