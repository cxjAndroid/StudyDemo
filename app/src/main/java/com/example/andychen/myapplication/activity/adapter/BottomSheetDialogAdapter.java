package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.mvp_model.BaseListAdapter;
import com.example.andychen.myapplication.activity.mvp_model.BaseViewHolder;

import java.util.List;

/**
 * Created by chenxujun on 16-9-8.
 */
public class BottomSheetDialogAdapter extends BaseListAdapter<String> {

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

    public BottomSheetDialogAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    public BottomSheetDialogAdapter(Context context, List<String> data, int layoutId, SlidingPaneLayout slidingPaneLayout) {
        super(context, data, layoutId);
        this.slidingPaneLayout = slidingPaneLayout;
    }

    @Override
    public void refreshView(BaseViewHolder holder, final String s, int p) {
        TextView textView = holder.getView(R.id.tv_title);
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
