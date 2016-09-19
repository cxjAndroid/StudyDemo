package com.example.andychen.myapplication.activity.activity;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_view.DesignView;

import butterknife.BindView;

/**
 * Created by chenxujun on 16-9-19.
 */
public class DesignActivity extends BaseActivity implements DesignView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    LinearLayout tab_layout;
    @BindView(R.id.btn_bottom_sheet_control)
    Button btn_bottom_sheet_control;
    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.acitivity_design;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(tab_layout);
    }

    @Override
    public void initDate() {
        btn_bottom_sheet_control.setOnClickListener(this);
        initBottomSheet();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_bottom_sheet_control) {
            changeBottomSheetStatus();
        }
    }

    @Override
    public void initBottomSheet() {
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void changeBottomSheetStatus() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setHideable(true);
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
