package com.example.yunuilibrary.tab.common.java;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface IYunTopLayout<TopView extends ViewGroup, TopInfo> {

    TopView findTopTab(@NonNull TopInfo topInfo);

    void defaultSelected(@NonNull TopInfo topInfo);

    void inflateInfo(@NonNull List<TopInfo> topInfos);

    void addTopTabSelectedChangeListener(OnTabSelectedListener listener);

    public interface OnTabSelectedListener<TopInfo> {
        void onTabSelectedChanged(int index, @Nullable TopInfo preInfo, @Nullable TopInfo nextInfo);
    }
}
