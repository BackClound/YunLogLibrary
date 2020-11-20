package com.example.yunuilibrary.tab.top.java;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yunuilibrary.tab.common.java.IYunTopLayout;
import com.example.yunuilibrary.tab.util.java.YunViewUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YunTopTabLayout extends HorizontalScrollView implements IYunTopLayout<YunTopTab, YunTopTabInfo<?>> {
    private List<IYunTopLayout.OnTabSelectedListener<YunTopTabInfo<?>>> topTabListeners = new ArrayList<>();
    private List<YunTopTabInfo<?>> topTabInfos = new ArrayList<>();
    private YunTopTabInfo<?> selectedTopTab = null;
    private int tabWidth = 0;

    public YunTopTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public YunTopTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YunTopTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(false);
    }

    private void onSelected(YunTopTabInfo<?> tabInfo) {
        for (OnTabSelectedListener<YunTopTabInfo<?>> listener : topTabListeners) {
            listener.onTabSelectedChanged(topTabInfos.indexOf(tabInfo), selectedTopTab, tabInfo);
        }
        this.selectedTopTab = tabInfo;

        scrollView(tabInfo);
    }

    private void scrollView(YunTopTabInfo<?> tabInfo) {
        YunTopTab topTab = findTopTab(tabInfo);
        if (topTab == null) return;
        int index = topTabInfos.indexOf(tabInfo);
        int[] loc = new int[2];
        topTab.getLocationInWindow(loc);
        if (tabWidth ==0) tabWidth = topTab.getWidth();
        int scrollWidth;
        //判断点击了屏幕左侧还是屏幕右侧
        if (loc[0] + tabWidth/2 > YunViewUtil.getWindowWidthInPx(getContext())/2) {
            scrollWidth = rangeScrollWidth(index, 2);
        } else {
            scrollWidth = rangeScrollWidth(index, -2);
        }
        scrollTo(scrollWidth, 0);

    }

    /**
     * 获取可滚动范围
     * @param index 从第几个开始
     * @param range 向前向后的范围
     * @return  可滚动的范围
     */
    private int rangeScrollWidth(int index, int range) {
        int scrollWidth = 0;
        for (int i = 0; i< Math.abs(range); i++) {
            int next;
            if (range >0 ) {
                next = range - i + index;
            } else {
                next = range + i + index;
            }

            if (next > 0 && next < topTabInfos.size()) {
                if (range > 0 ) {
                    scrollWidth += scrollWidth(next, true);
                } else {
                    scrollWidth -= scrollWidth(next, false);
                }
            }
        }
        return scrollWidth;
    }

    /**
     * 指定位置的控件可滚动的距离
     * @param next 指定位置的控件
     * @param toRight 向左滑动还是向右
     * @return 可滚动的距离
     */
    private int scrollWidth(int next, boolean toRight) {

        YunTopTab topTab = findTopTab(topTabInfos.get(next));
        if (topTab == null) return 0;
        Rect rect = new Rect();
        topTab.getLocalVisibleRect(rect);
        if (toRight) {//点击屏幕右侧
            if(rect.right > tabWidth){//right坐标大于控件的宽度时，说明rect完全没有显示
                return tabWidth;
            } else {//显示部分，减去已显示的宽度
                return tabWidth - rect.right;
            }
        } else {
            if (rect.left < -tabWidth) {//left小于等于-控件的宽度，说明完全没有显示
                return tabWidth;
            } else if (rect.left >0 ){//部分显示
                return rect.left;
            }
        }

        return 0;
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
        ViewGroup ll = (ViewGroup) getLinearLayout(false);

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
