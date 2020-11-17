package com.example.yunuilibrary.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yunlog.java.YunLogDisplayUtil;
import com.example.yunuilibrary.R;
import com.example.yunuilibrary.tab.common.java.IYunBottomLayout;

import java.util.ArrayList;
import java.util.List;

public class YunBottomTabLayout extends FrameLayout implements IYunBottomLayout<YunBottomTab, YunBottomTabInfo<?>> {

    private YunBottomTabInfo<?> selectedInfo;
    private List<YunBottomTabInfo<?>> tabInfos;
    private List<OnTabSelectedListener<YunBottomTabInfo<?>>> tabInfoListeners = new ArrayList();
    private static final String TAG_TAB_BOTTOM = "tag_tab_bottom";

    private float bottomAlpha = 0.8f;
    //bottom 高度
    private static float bottomHeight = 50;
    //bottom线条高度
    private float bottomLineHeight = 0.8f;
    //TabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";

    public YunBottomTabLayout(@NonNull Context context) {
        super(context,null);
    }

    public YunBottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public YunBottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    public void addTabSelectedChangeListener(@NonNull OnTabSelectedListener<YunBottomTabInfo<?>> listener) {
        tabInfoListeners.add(listener);
    }

    @Override
    public YunBottomTab findTab(@NonNull YunBottomTabInfo<?> yunBottomTabInfo) {
        FrameLayout ll = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < ll.getChildCount(); i++){
            View child = ll.getChildAt(i);
            if (child instanceof YunBottomTab) {
                if (((YunBottomTab) child).getTabInfo() == yunBottomTabInfo) {
                    return (YunBottomTab) child;
                }
            }
        }
        return null;
    }

    @Override
    public void defaultTabSelected(@NonNull YunBottomTabInfo<?> yunBottomTabInfo) {
        onSelected(yunBottomTabInfo);
    }

    @Override
    public void instantiateTabList(@NonNull List<YunBottomTabInfo<?>> yunBottomTabInfos) {
        if (yunBottomTabInfos.isEmpty()) {
            return;
        }

        this.tabInfos = yunBottomTabInfos;
        //清除之前的View,添加新的View
        for (int i =0; i < getChildCount(); i++){
            removeViewAt(i);
        }

        selectedInfo = null;
        addBackground();
        //清除之前添加的YunBottomLayout Listener, 使用Iterator进行循环遍历，避免删除过程中出现错误
        while (tabInfoListeners.iterator().hasNext()) {
            if (tabInfoListeners.iterator().next() instanceof YunBottomTab) tabInfoListeners.iterator().remove();
        }

        int width = YunLogDisplayUtil.getDisplayWidthInPx(getContext())/ yunBottomTabInfos.size();
        int height = YunLogDisplayUtil.dp2px(bottomHeight, getResources());
        FrameLayout ll = new FrameLayout(getContext());
        ll.setTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < yunBottomTabInfos.size(); i ++) {
            final YunBottomTabInfo tabInfo = yunBottomTabInfos.get(i);
            LayoutParams params = new LayoutParams(width, height);
            params.gravity = Gravity.BOTTOM;
            params.leftMargin = i*width;

            YunBottomTab bottomTab = new YunBottomTab(getContext());
            bottomTab.setTabInfo(yunBottomTabInfos.get(i));
            tabInfoListeners.add(bottomTab);
            
            ll.addView(bottomTab, params);
            bottomTab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(tabInfo);
                }
            });
        }

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(ll, params);
        

    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, YunLogDisplayUtil.dp2px(bottomLineHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        params.bottomMargin = YunLogDisplayUtil.dp2px(bottomHeight - bottomLineHeight, getResources());
        bottomLine.setAlpha(bottomAlpha);
        addView(bottomLine, params);

    }

    private void onSelected(YunBottomTabInfo tabInfo) {
        for (OnTabSelectedListener<YunBottomTabInfo<?>> listener: tabInfoListeners) {
            listener.onTabSelectedChange(tabInfos.indexOf(tabInfo), selectedInfo, tabInfo);
        }
        selectedInfo = tabInfo;
    }

    private void addBackground() {
        View backgroundView = LayoutInflater.from(getContext()).inflate(R.layout.view_bottom_tab_layout_background, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, YunLogDisplayUtil.dp2px(bottomLineHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(backgroundView, params);

        backgroundView.setAlpha(bottomAlpha);
    }
}
