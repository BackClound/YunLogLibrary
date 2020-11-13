package com.example.yunlog.java;

import java.text.SimpleDateFormat;
import java.util.Locale;

class YunLogMode {

    public YunLogMode(long timeMill, String log, int level, String tag) {
        this.timeMill = timeMill;
        this.log = log;
        this.level = level;
        this.tag = tag;
    }

    private SimpleDateFormat dateFormat =new SimpleDateFormat("yy-MM--dd HH:mm:ss", Locale.CHINA);
    public long timeMill;
    public String log;
    public int level;
    public String tag;


    public String format(long time) {
        return dateFormat.format(time);
    }

}
