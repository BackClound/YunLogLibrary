package com.example.yunlog.java;

public abstract class YunLogConfig {
    public static final String TAG = "YunLogConfig";
    public boolean enable = false;

    public abstract String getGlobalTag();
    public abstract boolean getEnable();

}
