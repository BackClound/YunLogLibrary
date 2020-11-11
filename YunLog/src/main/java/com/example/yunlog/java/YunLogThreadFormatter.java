package com.example.yunlog.java;

class YunLogThreadFormatter implements YunLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return data.getName();
    }
}
