package com.example.yunlibrary.activity.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yunlibrary.R;
import com.example.yunlog.java.YunLogDisplayUtil;
import com.example.yunuilibrary.tab.bottom.YunBottomTab;
import com.example.yunuilibrary.tab.bottom.YunBottomTabInfo;
import com.example.yunuilibrary.tab.bottom.YunBottomTabLayout;

import java.util.ArrayList;

public class YunBottomLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_layout);
        initBottomView();
    }

    private void initBottomView() {
        YunBottomTabLayout bottomTabLayout = findViewById(R.id.root_view);
        bottomTabLayout.setAlpha(0.8f);

        ArrayList<YunBottomTabInfo<?>> tabList = new ArrayList<>();

//        YunBottomTabInfo homeInfoImage =

        YunBottomTabInfo homeInfo = new YunBottomTabInfo<String>("首页", getString(R.string.if_home), null, "#ff656667", "#ffd44949", "fonts/iconfont.ttf");
        YunBottomTabInfo favoriteInfo = new YunBottomTabInfo<String>("收藏", getString(R.string.if_favorite), null, "#ff656667", "#ffd44949", "fonts/iconfont.ttf");
        YunBottomTabInfo categoryInfo = new YunBottomTabInfo<String>("分类", getString(R.string.if_category), null, "#ff656667", "#ffd44949", "fonts/iconfont.ttf");
        YunBottomTabInfo recommendInfo = new YunBottomTabInfo<String>("推荐", getString(R.string.if_recommend), null, "#ff656667", "#ffd44949", "fonts/iconfont.ttf");
        YunBottomTabInfo mainInfo = new YunBottomTabInfo<String>("我的", getString(R.string.if_profile), null, "#ff656667", "#ffd44949", "fonts/iconfont.ttf");
        tabList.add(homeInfo);
        tabList.add(favoriteInfo);
        tabList.add(categoryInfo);
        tabList.add(recommendInfo);
        tabList.add(mainInfo);

        bottomTabLayout.instantiateTabList(tabList);
        bottomTabLayout.defaultTabSelected(homeInfo);

        YunBottomTab centerTab = bottomTabLayout.findTab(tabList.get(2));
        centerTab.resetTabHeight(YunLogDisplayUtil.dp2px(50f, getResources()));
    }
}
