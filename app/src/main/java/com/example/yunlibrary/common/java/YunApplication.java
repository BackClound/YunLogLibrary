package com.example.yunlibrary.common.java;

import android.app.Application;

import com.example.yunlog.java.YunLogConfig;
import com.example.yunlog.java.YunLogManager;

public class YunApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YunLogConfig config = new YunLogConfig();
        config.enableLog(true);
        YunLogManager.initInstance(config);
    }
}
