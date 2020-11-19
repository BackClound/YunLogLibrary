package com.example.yunuilibrary.tab.util.java;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

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
}
