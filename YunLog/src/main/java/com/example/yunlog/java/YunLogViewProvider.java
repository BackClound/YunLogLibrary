package com.example.yunlog.java;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class YunLogViewProvider {

    private FrameLayout rootView;
    private View floatingView;
    private RecyclerView recyclerView;
    private FrameLayout logView;
    private boolean isOpen;

    public static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    public static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    public YunLogViewProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    public void showFloatingView() {
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.bottomMargin = YunLogDisplayUtil.dp2px(100f, rootView.getResources());
        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = genFloatingView();
        floatingView.setTag(TAG_FLOATING_VIEW);
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);

        rootView.addView(floatingView, layoutParams);
    }

    private View genFloatingView() {
        if (floatingView != null) return floatingView;
        TextView textView = new TextView(rootView.getContext());
        textView.setText("LogView");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogView();
            }
        });
        return floatingView = textView;
    }

    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) != null) return;

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, YunLogDisplayUtil.dp2px(160, rootView.getResources()));
        layoutParams.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setTag(TAG_LOG_VIEW);

        rootView.addView(logView, layoutParams);
        isOpen = true;
    }

    private View genLogView() {
        if (logView != null) return logView;
        FrameLayout tempLogView = new FrameLayout(rootView.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.END;
        tempLogView.setBackgroundColor(Color.BLACK);
        tempLogView.addView(recyclerView);

        TextView closeView = new TextView(rootView.getContext());
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLogView();
            }
        });

        closeView.setText("close");
        tempLogView.addView(closeView, layoutParams);
        return logView = tempLogView;
    }

    private void closeLogView() {
        isOpen = false;
        rootView.removeView(logView);
    }

    public void closeFloatingView() {
        rootView.removeView(floatingView);
    }
}
