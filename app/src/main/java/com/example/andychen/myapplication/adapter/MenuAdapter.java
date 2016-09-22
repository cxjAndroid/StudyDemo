package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.TextView;

import com.example.andychen.myapplication.activity.base.BaseListAdapter;
import com.example.andychen.myapplication.activity.base.BaseViewHolder;

import java.util.List;

/**
 * Created by chenxujun on 16-9-8.
 */
public class MenuAdapter extends BaseListAdapter<String> {

    private MenuItemCallBack menuItemCallBack;
    private SlidingPaneLayout slidingPaneLayout;

    public MenuItemCallBack getMenuItemCallBack() {
        return menuItemCallBack;
    }

    public void setMenuItemCallBack(MenuItemCallBack menuItemCallBack) {
        this.menuItemCallBack = menuItemCallBack;
    }

    public interface MenuItemCallBack {
        void menuItemOnClick(String s);
    }

    public MenuAdapter(List<String> data, int layoutId) {
        super(data, layoutId);
    }

    public MenuAdapter(List<String> data, int layoutId, SlidingPaneLayout slidingPaneLayout) {
        super(data, layoutId);
        this.slidingPaneLayout = slidingPaneLayout;
    }

    @Override
    public void refreshView(BaseViewHolder holder, final String s, int p) {
        TextView textView = holder.getView(android.R.id.text1);
        textView.setText(s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //侧滑菜单是打开朱状态才可以点击
                if (menuItemCallBack != null&&slidingPaneLayout.isOpen()) menuItemCallBack.menuItemOnClick(s);
            }
        });
    }
}
