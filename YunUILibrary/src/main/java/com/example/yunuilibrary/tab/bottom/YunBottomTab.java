package com.example.yunuilibrary.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.yunuilibrary.R;
import com.example.yunuilibrary.tab.common.java.IYunBottomTab;


class YunBottomTab extends RelativeLayout implements IYunBottomTab<YunBottomTabInfo<?>> {

    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    private YunBottomTabInfo<?> tabInfo;

    public YunBottomTab(Context context) {
        this(context, null);
    }

    public YunBottomTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YunBottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_bottom_item_layout, this);
        tabIconView = findViewById(R.id.textview_icon);
        tabNameView = findViewById(R.id.textview_name);
        tabImageView = findViewById(R.id.imageview_ic_title);

    }

    @Override
    public void setTabInfo(@NonNull YunBottomTabInfo<?> yunBottomTabInfo) {
        if (tabInfo != yunBottomTabInfo) {
            tabInfo = yunBottomTabInfo;
        }
        inflateInfo(false, true);
    }

    public YunBottomTabInfo<?> getTabInfo() {
        return tabInfo;
    }

    private void inflateInfo(boolean selected, boolean initialized) {
        if (tabInfo.iconType == YunBottomTabInfo.IconType.COLOR) {
            if (initialized) {
                tabIconView.setVisibility(VISIBLE);
                tabImageView.setVisibility(GONE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabInfo.iconFont);
                tabIconView.setTypeface(typeface);

                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }

            if (selected) {
                tabNameView.setTextColor(getTextColor(tabInfo.selectedIcon));
                tabIconView.setTextColor(getTextColor(tabInfo.selectedIcon));
                tabIconView.setText(TextUtils.isEmpty(tabInfo.selectedName) ? tabInfo.defaultName : tabInfo.selectedName);
            } else {
                tabNameView.setTextColor(getTextColor(tabInfo.defaultIcon));
                tabIconView.setTextColor(getTextColor(tabInfo.defaultIcon));
                tabIconView.setText(tabInfo.defaultName);
            }
        } else if (tabInfo.iconType == YunBottomTabInfo.IconType.BITMAP) {
            if (initialized) {
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }

            if (selected) {
                tabImageView.setImageBitmap(tabInfo.selectedBitmap);
            } else {
                tabImageView.setImageBitmap(tabInfo.defaultBitmap);
            }
        }

    }

    private int getTextColor(Object defaultIcon) {
        if (defaultIcon instanceof String) {
            return Color.parseColor(defaultIcon.toString());
        } else if (defaultIcon instanceof Integer) {
            return (int) defaultIcon;
        } else {
            return 0;
        }
    }

    @Override
    public void resetTabHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        tabNameView.setVisibility(GONE);
    }

    public void setTabImageView(ImageView tabImageView) {
        this.tabImageView = tabImageView;
    }

    public void setTabIconView(TextView tabIconView) {
        this.tabIconView = tabIconView;
    }

    public void setTabNameView(TextView tabNameView) {
        this.tabNameView = tabNameView;
    }

    @Override
    public void onTabSelectedChange(int index, @NonNull YunBottomTabInfo<?> preTab, @NonNull YunBottomTabInfo<?> nextTab) {
        if ((preTab != tabInfo && nextTab != tabInfo) || preTab == nextTab) {
            return;
        }
        if (preTab == tabInfo) {
            inflateInfo(false, false);
        } else {
            inflateInfo(true, false);
        }
    }
}
