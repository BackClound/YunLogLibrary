package com.example.yunuilibrary.tab.common.java;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public interface IYunBottomLayout<TabView extends ViewGroup, TabInfo> {

    void addTabSelectedChangeListener(@NonNull OnTabSelectedListener<TabInfo> listener);

    TabView findTab(@NonNull TabInfo info);

    void defaultTabSelected(@NonNull TabInfo tabInfo);

    void instantiateTabList(@NonNull List<TabInfo> tabInfoList);

    public interface OnTabSelectedListener<TabInfo> {
        void onTabSelectedChange(int index, @NonNull TabInfo preTab, @NonNull TabInfo nextTab);
    }

}
