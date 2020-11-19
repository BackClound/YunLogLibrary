package com.example.yunuilibrary.tab.top.java;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yunuilibrary.R;
import com.example.yunuilibrary.tab.common.java.IYunTopTab;

public class YunTopTab extends RelativeLayout implements IYunTopTab<YunTopTabInfo<?>> {
    public YunTopTabInfo<?> topTabInfo;
    public ImageView topImageView;
    public TextView topTabName;
    public View indicator;

    public YunTopTab(Context context) {
        this(context, null);
    }

    public YunTopTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YunTopTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public YunTopTabInfo<?> getTopTabInfo() {
        return topTabInfo;
    }

    private void initView() {
        ViewGroup rootView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.top_tab_item_layout, this);
        topImageView = findViewById(R.id.imageview_top_bitmap);
        topTabName = findViewById(R.id.textview_top_name);
        indicator = findViewById(R.id.indicator_top_tab_view);
    }

    @Override
    public void setTopInfo(@NonNull YunTopTabInfo<?> yunTopTabInfo) {
        if (topTabInfo != yunTopTabInfo) {
            topTabInfo = yunTopTabInfo;
        }
        initInfo(false, true);
    }

    private void initInfo(boolean selected, boolean inited) {
        if (topTabInfo.tabType == YunTopTabInfo.TabType.TEXT) {
            if (inited) {
                topImageView.setVisibility(GONE);
                topTabName.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(topTabInfo.name)) {
                    topTabName.setText(topTabInfo.name);
                }
            }

            if (selected) {
                indicator.setVisibility(VISIBLE);
                topTabName.setTextColor(getTextColor(topTabInfo.selectedIconFont));
            } else {
                indicator.setVisibility(GONE);
                topTabName.setTextColor(getTextColor(topTabInfo.defaultIconFont));
            }
        } else if (topTabInfo.tabType == YunTopTabInfo.TabType.BITMAP) {
            if (inited) {
                topImageView.setVisibility(VISIBLE);
                topTabName.setVisibility(GONE);
            }

            if (selected) {
                indicator.setVisibility(VISIBLE);
                topImageView.setImageBitmap(topTabInfo.selectedBitmap);
            } else {
                indicator.setVisibility(GONE);
                topImageView.setImageBitmap(topTabInfo.defaultBitmap);
            }
        }
    }

    private int getTextColor(Object selectedIconFont) {
        if (selectedIconFont instanceof String) {
            return Color.parseColor((String) selectedIconFont);
        } else {
            return (int) selectedIconFont;
        }
    }

    @Override
    public void resetHeight(int height) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = height;
        setLayoutParams(params);
        topTabName.setVisibility(GONE);
    }

    @Override
    public void onTabSelectedChanged(int index, @Nullable YunTopTabInfo<?> preInfo, @Nullable YunTopTabInfo<?> nextInfo) {
        if ((preInfo != topTabInfo && nextInfo != topTabInfo) || preInfo == nextInfo) {
            return;
        }

        if (preInfo == topTabInfo) {
            initInfo(false, false);
        } else {
            initInfo(true, false);
        }
    }
}
