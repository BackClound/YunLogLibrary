package com.example.yunlog.java;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunlog.R;

import java.util.ArrayList;
import java.util.List;

public class YunLogViewPrinter implements YunLogPrinter{

    private YunLogRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    public YunLogViewProvider provider;

    public YunLogViewPrinter(Activity activity) {
        FrameLayout rootLayout = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new YunLogRecyclerViewAdapter(activity, LayoutInflater.from(recyclerView.getContext()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        provider =new YunLogViewProvider(rootLayout, recyclerView);
    }

    @Override
    public void print(@NonNull YunLogConfig config, int level, String tag, @NonNull String printString) {
        adapter.addItem(new YunLogMode(System.currentTimeMillis(),printString, level, tag));

        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    class YunLogRecyclerViewAdapter extends RecyclerView.Adapter<YunLogModeViewHolder> {

        public List<YunLogMode> items =new  ArrayList<YunLogMode>();
        public Context context;
        public LayoutInflater inflater;


        public YunLogRecyclerViewAdapter(Context context, LayoutInflater inflater) {
            this.context = context;
            this.inflater = inflater;
        }

        public void addItem(YunLogMode mode) {
            items.add(mode);
            notifyItemInserted(items.size()-1);
        }

        @NonNull
        @Override
        public YunLogModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = inflater.inflate(R.layout.item_log_print_layout, parent, false);
            return new YunLogModeViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull YunLogModeViewHolder holder, int position) {
            YunLogMode item  = items.get(position);

            int color = setHighLightColor(item.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);
            holder.tagView.setText(item.tag);
            holder.messageView.setText(item.log);
        }

        private int setHighLightColor(int level) {
            int highLigh;
            switch (level) {
                case YunLogType.V:
                    highLigh = 0xffbbbbbb;
                    break;
                case YunLogType.D:
                    highLigh = 0xffffffff;
                    break;
                case YunLogType.I:
                    highLigh = 0xff6a8759;
                    break;
                case YunLogType.W:
                    highLigh = 0xffbbb529;
                    break;
                case YunLogType.E:
                    highLigh = 0xffff6b68;
                    break;
                default:
                    highLigh = 0xffffff00;
                    break;
            }
            return highLigh;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class YunLogModeViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;

        public YunLogModeViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.textview_log_tag_info);
            messageView = itemView.findViewById(R.id.textview_log_message_info);
        }
    }
}
