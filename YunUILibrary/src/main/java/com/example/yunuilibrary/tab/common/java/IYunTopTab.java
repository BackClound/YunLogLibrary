package com.example.yunuilibrary.tab.common.java;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

public interface IYunTopTab<TopInfo> extends IYunTopLayout.OnTabSelectedListener<TopInfo> {
    public void setTopInfo(@NonNull TopInfo topInfo);
    public void resetHeight(@Px int height);
}
