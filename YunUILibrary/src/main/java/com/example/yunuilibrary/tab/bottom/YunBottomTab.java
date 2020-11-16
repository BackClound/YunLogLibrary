package com.example.yunuilibrary.tab.bottom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.yunuilibrary.tab.common.java.IYunBottomTab;


class YunBottomTab extends RelativeLayout implements IYunBottomTab<YunBottomTabInfo<?>> {

    private YunBottomTabInfo<?> tabInfo;
    public YunBottomTab(Context context) {
        super(context, null);
    }

    public YunBottomTab(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public YunBottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInfo();
    }

    private void initInfo() {


    }

    @Override
    public void setTabInfo(@NonNull YunBottomTabInfo<?> yunBottomTabInfo) {

    }

    @Override
    public void resetTabHeight(int height) {

    }
}
