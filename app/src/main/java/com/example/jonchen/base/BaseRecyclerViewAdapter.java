package com.example.jonchen.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chenxujun on 16-9-22.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> mData;
   /* private int layoutId;

    public BaseRecyclerViewAdapter(List<T> mData, int layoutId) {
        this.mData = mData;
        this.layoutId = layoutId;
    }*/

    public BaseRecyclerViewAdapter(List<T> mData) {
        this.mData = mData;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = createItemView(parent, viewType);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        refreshView(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public abstract void refreshView(BaseRecyclerViewHolder holder, T t, int position);

    public abstract View createItemView(ViewGroup parent, int viewType);

}
