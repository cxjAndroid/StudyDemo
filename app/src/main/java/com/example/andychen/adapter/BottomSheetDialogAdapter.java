package com.example.andychen.adapter;

import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.base.BaseRecyclerAdapter;
import com.example.andychen.base.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Created by chenxujun on 16-9-8.
 */
public class BottomSheetDialogAdapter extends BaseRecyclerAdapter<String> {

    public BottomSheetDialogAdapter(List<String> mData, int layoutId) {
        super(mData, layoutId);
    }

    @Override
    public void refreshView(BaseRecyclerViewHolder holder, String s, int position) {
        TextView textView = holder.getView(R.id.tv_title);
        textView.setText(s);
    }
}
