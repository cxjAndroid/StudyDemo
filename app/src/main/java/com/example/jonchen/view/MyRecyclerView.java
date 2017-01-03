package com.example.jonchen.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by chenxujun on 16-9-22.
 */

public class MyRecyclerView extends RecyclerView {
    private  Context context;
    private LinearLayoutManager layoutManager;

    @Override
    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public MyRecyclerView(Context context) {
        this(context,null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        setLayoutManager(layoutManager);
        addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
    }
}
