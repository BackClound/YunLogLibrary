package com.example.yunlibrary.activity.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yunlibrary.R;
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


        YunBottomTabInfo homeInfo = new YunBottomTabInfo<String>("首页",getString(R.string.if_home), "#ff656667", "#ffd44949", "fonts/iconfont.ttf" );
        YunBottomTabInfo favoriteInfo = new YunBottomTabInfo<String>("收藏",getString(R.string.if_favorite), "#ff656667", "#ffd44949", "fonts/iconfont.ttf" );
        YunBottomTabInfo categoryInfo = new YunBottomTabInfo<String>("分类",getString(R.string.if_category), "#ff656667", "#ffd44949", "fonts/iconfont.ttf" );
        YunBottomTabInfo recommendInfo = new YunBottomTabInfo<String>("推荐",getString(R.string.if_recommend), "#ff656667", "#ffd44949", "fonts/iconfont.ttf" );
        YunBottomTabInfo mainInfo = new YunBottomTabInfo<String>("我的",getString(R.string.if_profile), "#ff656667", "#ffd44949", "fonts/iconfont.ttf" );
        tabList.add(homeInfo);
        tabList.add(favoriteInfo);
        tabList.add(categoryInfo);
        tabList.add(recommendInfo);
        tabList.add(mainInfo);

        bottomTabLayout.instantiateTabList(tabList);
        bottomTabLayout.defaultTabSelected(homeInfo);
    }
}
