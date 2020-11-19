package com.example.yunuilibrary.tab.top.java;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

public class YunTopTabInfo<IconFont> {

    public enum TabType {
        BITMAP, TEXT
    }
    String name;
    public Class<? extends Fragment> fragment;
    public IconFont defaultIconFont;
    public IconFont selectedIconFont;

    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public TabType tabType;

    public YunTopTabInfo(String name, Class<? extends Fragment> fragment, Bitmap defaultBitmap, Bitmap selectedBitmap, TabType tabType) {
        this.name = name;
        this.fragment = fragment;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = tabType;
    }

    public YunTopTabInfo(String name, Class<? extends Fragment> fragment, IconFont defaultIconFont, IconFont selectedIconFont, TabType tabType) {
        this.name = name;
        this.fragment = fragment;
        this.defaultIconFont = defaultIconFont;
        this.selectedIconFont = selectedIconFont;
        this.tabType = tabType;
    }

    @Override
    public String toString() {
        return "YunTopTabInfo{" +
                "name='" + name + '\'' +
                ", fragment=" + fragment +
                ", defaultIconFont=" + defaultIconFont +
                ", selectedIconFont=" + selectedIconFont +
                ", defaultBitmap=" + defaultBitmap +
                ", selectedBitmap=" + selectedBitmap +
                ", tabType=" + tabType +
                '}';
    }
}
