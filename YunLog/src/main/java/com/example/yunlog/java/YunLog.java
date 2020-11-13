package com.example.yunlog.java;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

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
        StringBuffer logString =new StringBuffer();
        if (!config.getEnable()) {
            return;
        }
        if (config.enableThread()) {
            String threadLog = YunLogConfig.YUN_THREAD_FORMATTER.format(Thread.currentThread());
            logString.append(threadLog);
            logString.append("\n");
        }

        if (config.enableStackTrace() && config.getStackTraceDepth() > 0) {
            String stackTraceLog = YunLogConfig.YUN_STACK_TRACE_FORMATTER.format(YunLogStackTraceUtil.getRealCropStackTrace(new Throwable().getStackTrace(), config.getGlobalTag(), config.getStackTraceDepth()));
            logString.append(stackTraceLog);
            logString.append("\n");
        }

        String message = parserObject(parameters);
        logString.append(message);

        ArrayList<YunLogPrinter> printers = config.getPrinters() != null ? config.getPrinters() : YunLogManager.getInstance().getPrinters();
        if (printers == null || printers.isEmpty()) {
            return;
        }
        for (YunLogPrinter printer: printers) {
            printer.print(config, type, tag, logString.toString());
        }
    }

    private static String parserObject(Object[] parameters) {
        StringBuffer buffer = new StringBuffer();
        for (Object o : parameters) {
            buffer.append(o.toString());
        }
        return buffer.toString();
    }


}
