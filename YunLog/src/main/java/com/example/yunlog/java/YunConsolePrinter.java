package com.example.yunlog.java;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.example.yunlog.java.YunLogConfig.LINE_MAX_LENGTH;

class YunConsolePrinter implements YunLogPrinter {
    @Override
    public void print(@NonNull YunLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int depth = len / LINE_MAX_LENGTH;

        if (depth == 0) {
            Log.println(level, tag, printString);
        } else {
            int index = 0;
            for (int i = 0; i < depth; i++) {
                Log.println(level, tag, printString.substring(index, index + LINE_MAX_LENGTH));
                index += LINE_MAX_LENGTH;
            }
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }
        }

    }
}
