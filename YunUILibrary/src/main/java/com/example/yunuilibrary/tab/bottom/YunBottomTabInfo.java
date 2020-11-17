package com.example.yunuilibrary.tab.bottom;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

public class YunBottomTabInfo<Icon> {
    public enum IconType {
        BITMAP, COLOR, DRAWABLE
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
    public Icon defaultIcon;
    public Icon selectedIcon;

    public IconType iconType;

    public YunBottomTabInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.iconType = IconType.BITMAP;
    }

    public YunBottomTabInfo(String defaultName, String selectedName, Icon defaultIcon, Icon selectedIcon, String iconFont) {
        this.defaultName = defaultName;
        this.selectedName = selectedName;
        this.defaultIcon = defaultIcon;
        this.selectedIcon = selectedIcon;
        this.iconType = IconType.COLOR;
        this.iconFont = iconFont;
    }
}
