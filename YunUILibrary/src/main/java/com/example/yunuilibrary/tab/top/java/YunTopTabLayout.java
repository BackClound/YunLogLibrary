package com.example.yunuilibrary.tab.top.java;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yunuilibrary.tab.common.java.IYunTopLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YunTopTabLayout extends HorizontalScrollView implements IYunTopLayout<YunTopTab, YunTopTabInfo<?>> {
    private List<IYunTopLayout.OnTabSelectedListener<YunTopTabInfo<?>>> topTabListeners = new ArrayList<>();
    private List<YunTopTabInfo<?>> topTabInfos = new ArrayList<>();
    private YunTopTabInfo<?> selectedTopTab = null;

    public YunTopTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public YunTopTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YunTopTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void onSelected(YunTopTabInfo<?> tabInfo) {
        for (OnTabSelectedListener<YunTopTabInfo<?>> listener : topTabListeners) {
            listener.onTabSelectedChanged(topTabInfos.indexOf(tabInfo), selectedTopTab, tabInfo);
        }
        this.selectedTopTab = tabInfo;
    }

    private LinearLayout getLinearLayout(boolean clear) {
        LinearLayout rootView = (LinearLayout) getChildAt(0);

        if (rootView == null) {
            rootView = new LinearLayout(getContext());
            rootView.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(rootView, params);
        } else if (clear) {
            rootView.removeAllViews();
        }

        return rootView;
    }


    @Override
    public YunTopTab findTopTab(@NonNull YunTopTabInfo<?> yunTopTabInfo) {
        ViewGroup ll = (ViewGroup) getRootView();

        for (int i = 0; i < ll.getChildCount(); i++) {
            View childView = ll.getChildAt(i);
            if (childView instanceof YunTopTab) {
                YunTopTabInfo topTabInfo = ((YunTopTab) childView).getTopTabInfo();
                if (topTabInfo == yunTopTabInfo) {
                    return (YunTopTab) childView;
                }
            }
        }

        return null;
    }

    @Override
    public void defaultSelected(@NonNull YunTopTabInfo<?> yunTopTabInfo) {
        onSelected(yunTopTabInfo);
    }

    @Override
    public void inflateInfo(@NonNull List<YunTopTabInfo<?>> yunTopTabInfos) {
        if (yunTopTabInfos.isEmpty()) return;
        topTabInfos = yunTopTabInfos;

        LinearLayout rootView = getLinearLayout(true);
        selectedTopTab = null;
        //清空添加的Listeners
        Iterator<OnTabSelectedListener<YunTopTabInfo<?>>> iterator = topTabListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof YunTopTab) {
                iterator.remove();
            }
        }

        for (int i = 0; i < topTabInfos.size(); i++) {
            final YunTopTabInfo<?> tabInfo = topTabInfos.get(i);
            YunTopTab topTab = new YunTopTab(getContext());
            topTabListeners.add(topTab);
            topTab.setTopInfo(tabInfo);
            Log.e(" top view", topTab.toString());
//            YunLog.INSTANCE.e(" top view ", topTab.toString());

            rootView.addView(topTab);
            topTab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(tabInfo);
                }
            });
        }
    }

    @Override
    public void addTopTabSelectedChangeListener(OnTabSelectedListener listener) {
        topTabListeners.add(listener);
    }
}
