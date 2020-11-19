package com.example.yunlibrary.activity.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yunlibrary.R;
import com.example.yunuilibrary.tab.top.java.YunTopTab;
import com.example.yunuilibrary.tab.top.java.YunTopTabInfo;
import com.example.yunuilibrary.tab.top.java.YunTopTabLayout;

import java.util.ArrayList;
import java.util.List;

public class YunTopLayoutActivity extends AppCompatActivity {

    String[] tabsStr = new String[]{
            "热门",
            "服装",
            "数码",
            "鞋子",
            "零食",
            "家电",
            "汽车",
            "百货",
            "家居",
            "装修",
            "运动"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_layout);

        initView();
    }

    private void initView() {
        List<YunTopTabInfo<?>> topTabInfoList = new ArrayList<>();
        YunTopTabLayout topTabLayout = findViewById(R.id.yun_top_tab);

        int defaultTopColor = getColor(R.color.tabBottomDefaultColor);
        int selectedTopColor = getResources().getColor(R.color.tabBottomTintColor);

        for (String item: tabsStr) {
            YunTopTabInfo<Integer> topTabInfo = new YunTopTabInfo<Integer>(item,null,defaultTopColor, selectedTopColor, YunTopTabInfo.TabType.TEXT);
            topTabInfoList.add(topTabInfo);
        }

        topTabLayout.inflateInfo(topTabInfoList);

        topTabLayout.defaultSelected(topTabInfoList.get(0));
    }
}
