package com.example.yunlog.java;

import java.util.ArrayList;

public class YunLogManager {
    public static final String TAG = "YunLogManager";
    private YunLogConfig yunLogConfig;
    public static YunLogManager mInstance;
    private ArrayList<YunLogPrinter> printers;

    private YunLogManager(YunLogConfig config, ArrayList<YunLogPrinter> logPrinters) {
        yunLogConfig = config;
        printers = logPrinters;
    }

    public static void initInstance(YunLogConfig config, ArrayList<YunLogPrinter> logPrinters) {
        synchronized(YunLogManager.class) {
            if (mInstance == null) {
                mInstance = new YunLogManager(config, logPrinters);
            }
        }
    }

    public static YunLogManager getInstance() {
        return mInstance;
    }

    public YunLogConfig getYunLogConfig() {
        return yunLogConfig;
    }

    public ArrayList<YunLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(YunLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(YunLogPrinter printer) {
        printers.remove(printer);
    }

    public void removeAll() {
        printers.clear();
    }
}
