package com.example.yunlog.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class YunLogStackTraceUtil {

    public static StackTraceElement[] getRealCropStackTrace(StackTraceElement[] stackTraceElements, String ignorePackage, int maxDepth) {
        return realStackTrace(cropStackTrace(stackTraceElements, maxDepth), ignorePackage);
    }


    private static StackTraceElement[] realStackTrace(StackTraceElement[] stackTraceElements, String ignorePackage ) {
        int ignoreDepth = 0;
        int allDepth = stackTraceElements.length;
        for (int i = allDepth -1; i > 0; i --) {
            if (ignorePackage != null && ignorePackage.equals(stackTraceElements[i].getClassName())){
                ignoreDepth = i + 1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] newStackTraces = new StackTraceElement[realDepth];
        System.arraycopy(stackTraceElements, 0, newStackTraces, 0, realDepth);
        return newStackTraces;
    }

    /**
     * 裁剪 StackTrace list
     * @param stackTraceElements
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] stackTraceElements, int maxDepth) {
        StackTraceElement[] stackTraces =new StackTraceElement[stackTraceElements.length];

        int originLen = stackTraceElements.length;
        int desLen;
        if (originLen != 0) {
            desLen = Math.min(originLen, maxDepth);
        } else {
            return null;
        }
        StackTraceElement[] realStackTraces =new  StackTraceElement[desLen];
        System.arraycopy(stackTraceElements, 0, realStackTraces,0, desLen);
        return realStackTraces;
    }
}
