package com.example.yunuilibrary.tab.bottom;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

public class YunBottomTabInfo<IconFont> {
    public enum IconType {
        BITMAP, ICONFONT, DRAWABLE
    }

    /**
     * 当 Icon的类型为Bitmap时，可使用如下字段信息
     */
    public Class<? extends Fragment> fragment;
    public String name;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public String iconFont;

    /**
     * 当Icon的类型为泛型，无法确定
     */
    public String defaultName;
    public String selectedName;
    public IconFont defaultIconFont;
    public IconFont selectedIconFont;

    public IconType iconType;

    public YunBottomTabInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.iconType = IconType.BITMAP;
    }

    public YunBottomTabInfo(String name, String defaultName, String selectedName, IconFont defaultIconFont, IconFont selectedIconFont, String iconFont) {
        this.name = name;
        this.defaultName = defaultName;
        this.selectedName = selectedName;
        this.defaultIconFont = defaultIconFont;
        this.selectedIconFont = selectedIconFont;
        this.iconType = IconType.ICONFONT;
        this.iconFont = iconFont;
    }
}
