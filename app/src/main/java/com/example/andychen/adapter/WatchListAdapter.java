package com.example.andychen.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.andychen.base.BaseRecyclerAdapter;
import com.example.andychen.base.BaseRecyclerViewHolder;
import com.example.andychen.model.WatchInfo;
import com.example.andychen.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListAdapter extends BaseRecyclerAdapter<WatchInfo> {

    public WatchListAdapter(List<WatchInfo> mData, int layoutId) {
        super(mData, layoutId);
    }

    @Override
    public void refreshView(BaseRecyclerViewHolder holder, WatchInfo watchInfo, int position) {
        TextView nameTV = holder.getView(R.id.nameTV);
        TextView phoneTV = holder.getView(R.id.phoneTV);
        nameTV.setText(watchInfo.getRealName());
        phoneTV.setText(watchInfo.getPhone());
    }
}
