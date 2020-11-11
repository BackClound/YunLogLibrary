package com.example.yunlog.java;

public abstract class YunLogConfig {
    public static final String TAG = "YunLogConfig";
    public boolean enable = false;
    public static final int LINE_MAX_LENGTH = 130;


    public abstract String getGlobalTag();
    public abstract boolean getEnable();

}
