package com.example.yunlog.java;

import java.util.ArrayList;

public abstract class YunLogConfig {
    public static final String TAG = "YunLogConfig";
    public boolean enable = false;
    public static final int LINE_MAX_LENGTH = 130;
    public static YunLogThreadFormatter YUN_THREAD_FORMATTER = new YunLogThreadFormatter();
    public static YunLogStackTraceFormatter YUN_STACK_TRACE_FORMATTER = new YunLogStackTraceFormatter();

    public boolean enableThread() {
        return true;
    }

    public boolean enableStackTrace() {
        return true;
    }

    public int getStackTraceDepth() {
        return 5;
    }

    public ArrayList<YunLogPrinter> getPrinters() {
        return null;
    }

    public JsonParser injectJsonParser() {
        return null;
    }

    public abstract String getGlobalTag();
    public abstract boolean getEnable();

    public interface JsonParser {
        String toJson(Object o);
    }

}
