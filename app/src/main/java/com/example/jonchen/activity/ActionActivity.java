package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.view.View;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.view.MyBtn;
import com.example.jonchen.view.MyLayout;

import butterknife.BindView;

/**
 * Created by jon on 3/18/17.
 */

public class ActionActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    @BindView(R.id.mLayout)
    MyLayout mLayout;

    @BindView(R.id.mBtn)
    MyBtn mBtn;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_action;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        mLayout.setOnTouchListener(this);
        mBtn.setOnTouchListener(this);
        mLayout.setOnClickListener(this);
        mBtn.setOnClickListener(this);

      
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
