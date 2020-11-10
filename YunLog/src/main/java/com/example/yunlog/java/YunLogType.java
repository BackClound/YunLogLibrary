package com.example.yunlog.java;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class YunLogType {
    @IntDef({I, D, W, E, A, V})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type{}

    public static final int I = Log.INFO;
    public static final int D = Log.DEBUG;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;
    public static final int V = Log.VERBOSE;

}
