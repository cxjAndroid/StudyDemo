package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.andychen.myapplication.activity.mvp_model.BaseListAdapter;
import com.example.andychen.myapplication.activity.mvp_model.BaseViewHolder;
import com.example.andychen.myapplication.activity.utils.ToastUtils;

import java.util.List;

/**
 * Created by chenxujun on 16-9-8.
 */
public class MenuAdapter extends BaseListAdapter<String> {

    private MenuItemCallBack menuItemCallBack;

    public MenuItemCallBack getMenuItemCallBack() {
        return menuItemCallBack;
    }

    public void setMenuItemCallBack(MenuItemCallBack menuItemCallBack) {
        this.menuItemCallBack = menuItemCallBack;
    }

    public interface MenuItemCallBack {
        void menuItemOnClick(String s);
    }

    public MenuAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void refreshView(BaseViewHolder holder, final String s, int p) {
        TextView textView = holder.getView(android.R.id.text1);
        textView.setText(s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuItemCallBack != null) menuItemCallBack.menuItemOnClick(s);
            }
        });
    }
}
