package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseListAdapter;
import com.example.andychen.myapplication.activity.base.BaseViewHolder;

import java.util.List;

/**
 * Created by chenxujun on 16-9-8.
 */
public class MenuAdapter extends BaseListAdapter<String> {

    public MenuAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void refreshView(BaseViewHolder holder, String s, int p) {
        TextView textView = holder.getView(android.R.id.text1);
        textView.setText(s);
    }
}
