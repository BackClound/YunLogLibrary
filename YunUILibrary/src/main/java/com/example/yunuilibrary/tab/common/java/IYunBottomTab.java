package com.example.yunuilibrary.tab.common.java;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

public interface IYunBottomTab<TabInfo> extends IYunBottomLayout.OnTabSelectedListener<TabInfo>{

    void setTabInfo(@NonNull TabInfo tabInfo);

    void resetTabHeight(@Px int height);
}
