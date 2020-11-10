package com.example.yunlog.kotlin

import android.util.Log
import androidx.annotation.IntDef

class YunLogType {

    @IntDef(V,D,W,I,A, E)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type

    companion object {

        const val V = Log.VERBOSE
        const val D = Log.DEBUG
        const val W = Log.WARN
        const val I = Log.INFO
        const val A = Log.ASSERT
        const val E = Log.ERROR
    }
}