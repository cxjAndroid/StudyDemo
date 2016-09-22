package com.example.andychen.myapplication.activity.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chenxujun on 16-9-22.
 */

public class BaseRecyclerApapter<T> extends RecyclerView.Adapter {

    private List<T> mData;
    private int layoutId;

    public BaseRecyclerApapter(List<T> mData, int layoutId) {
        this.mData = mData;
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
