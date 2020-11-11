package com.example.yunlog.java;

class YunLogStackTraceFormatter implements YunLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] stackTraceElements) {
        StringBuffer buffer = new StringBuffer(128);

        if (stackTraceElements.length == 0) {
            return null;
        } else if (stackTraceElements.length == 1) {
            return stackTraceElements[0].toString();
        } else {
            for (int i = 0, len = stackTraceElements.length; i < len; i++) {
                if (i == 0) {
                    buffer.append("StackTrace: \n");
                } else if (i != stackTraceElements.length - 1) {
                    buffer.append("\t |-");
                    buffer.append(stackTraceElements[i].toString());
                    buffer.append("\n");
                } else {
                    buffer.append("\t_|");
                    buffer.append(stackTraceElements[i].toString());
                }
            }
            return buffer.toString();
        }
    }
}
