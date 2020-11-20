package com.example.yunuilibrary.tab.util.java;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class YunViewUtil {

    public static <T> T findTypeView(@Nullable ViewGroup viewGroup, Class<T> tClass) {
        if (viewGroup == null) {
            return null;
        }

        Deque<View> deque =new ArrayDeque();
        deque.add(viewGroup);
        while (!deque.isEmpty()) {
            View node = deque.removeFirst();
            if (tClass.isInstance(node)) {
                return tClass.cast(node);
            }else if (node instanceof ViewGroup) {
                ViewGroup container = (ViewGroup) node;
                for (int i = 0, count = container.getChildCount(); i < count; i ++) {
                    deque.add(container.getChildAt(i));
                }
            }
        }
        return null;
    }

    public static int getWindowWidthInPx(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            return point.x;
        }
        return 0;
    }
}
