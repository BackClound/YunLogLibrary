package com.example.yunlog.java;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * 面向接口编程
 *
 * 对外提供打印日志的方法
 *
 * 添加@kotlin.jvm.IvmStatic注解，是Kotlin可以在companion object找中调用
 */
public class YunLog {
    public static void i(Object... parameters) {
        log(YunLogType.I, parameters);
    }

    public static void i(@NonNull String tag, Object... parameters) {
        log(YunLogType.I, tag, parameters);
    }

    public static void e(Object... parameters) {
        log(YunLogType.E, parameters);
    }

    public static void e(@NonNull String tag, Object... parameters) {
        log(YunLogType.E, tag, parameters);
    }

    public static void w(Object... parameters) {
        log(YunLogType.W, parameters);
    }

    public static void w(@NonNull String tag, Object... parameters) {
        log(YunLogType.W, tag, parameters);
    }

    public static void d(Object... parameters) {
        log(YunLogType.D, parameters);
    }

    public static void d(@NonNull String tag, Object... parameters) {
        log(YunLogType.D, tag, parameters);
    }

    public static void v(Object... parameters) {
        log(YunLogType.V, parameters);
    }

    public static void v(@NonNull String tag, Object... parameters) {
        log(YunLogType.V, tag, parameters);
    }


    private static void log(@YunLogType.Type int type, Object... parameters) {
        log(type, YunLogManager.TAG, parameters);
    }

    private static void log(@YunLogType.Type int type, @NonNull String tag, Object... parameters) {
        log(YunLogManager.mInstance.getYunLogConfig(), type, tag, parameters);
    }

    private static void log(@NonNull YunLogConfig config, @YunLogType.Type int type, @NonNull String tag, Object... parameters) {
        if (!config.getEnable()) {
            return;
        }
        String message = parserObject(parameters);
        Log.println(type, tag, message);

    }

    private static String parserObject(Object[] parameters) {
        StringBuffer buffer = new StringBuffer();
        for (Object o : parameters) {
            buffer.append(o.toString());
        }
        return buffer.toString();
    }


}
