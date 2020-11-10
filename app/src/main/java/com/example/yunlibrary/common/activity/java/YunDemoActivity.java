package com.example.yunlibrary.common.activity.java;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yunlibrary.R;
import com.example.yunlog.kotlin.YunLog;

public class YunDemoActivity  extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout);
        textView = findViewById(R.id.textview_print_log);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog();
            }
        });
    }

    private void printLog() {
        YunLog.INSTANCE.i(null,"Kotlin : call from Java, Print I level log");
    }
}
