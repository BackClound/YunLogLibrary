package com.example.yunlog.java;

public class YunLogManager {
    public static final String TAG = "YunLogManager";
    private YunLogConfig yunLogConfig;
    public static YunLogManager mInstance;

    private YunLogManager(YunLogConfig config) {
        yunLogConfig = config;
    }

    public static void initInstance(YunLogConfig config) {
        synchronized(YunLogManager.class) {
            if (mInstance == null) {
                mInstance = new YunLogManager(config);
            }
        }
    }

    public static YunLogManager getInstance() {
        return mInstance;
    }

    public YunLogConfig getYunLogConfig() {
        return yunLogConfig;
    }
}
