package com.example.yunlog.java;

import androidx.annotation.NonNull;

interface YunLogPrinter {

    public void print(@NonNull YunLogConfig config, @YunLogType.Type int level, String tag, @NonNull String printString);
}
