package com.example.andychen.myapplication.activity.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chenxujun on 2016/3/7.
 */
public class BaseViewHolder {

    private SparseArray<View> mViews;
    public int               position;
    private View              mConvertView;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.position = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);

    }

    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder baseViewHolder = (BaseViewHolder) convertView.getTag();
            baseViewHolder.position = position;
            return baseViewHolder;
        }
    }


    public View getConvertView() {
        return mConvertView;
    }

    public void setConvertView(View mConvertView) {
        this.mConvertView = mConvertView;
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends  View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }


    public BaseViewHolder setText(int viewID,String text){
        TextView view = getView(viewID);
        view.setText(text);
        return this;
    }


}
