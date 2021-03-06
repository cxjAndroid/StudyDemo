package com.example.jonchen.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by chenxujun on 2016/3/7.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    private List<T> mData;
    protected Context context;
    private int layoutId;

    public BaseListAdapter(Context context, List<T> data, int layoutId) {
        mData = data;
        this.context = context;
        this.layoutId = layoutId;
    }

    public BaseListAdapter(List<T> data, int layoutId) {
        mData = data;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = null;
        if (context != null) {
            baseViewHolder = BaseViewHolder.get(context, convertView, parent, layoutId, position);
        }else{
            baseViewHolder = BaseViewHolder.get(convertView, parent, layoutId, position);
        }
        refreshView(baseViewHolder, getItem(position), position);
        return baseViewHolder.getConvertView();
    }

    public abstract void refreshView(BaseViewHolder holder, T t, int p);

}
