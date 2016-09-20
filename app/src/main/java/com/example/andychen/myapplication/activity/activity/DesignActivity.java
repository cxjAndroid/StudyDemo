package com.example.andychen.myapplication.activity.activity;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.MenuAdapter;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_presenter.DesignPresenter;
import com.example.andychen.myapplication.activity.mvp_view.DesignView;
import com.example.andychen.myapplication.activity.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenxujun on 16-9-19.
 */
public class DesignActivity extends BaseActivity<DesignPresenter> implements DesignView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    LinearLayout tab_layout;
    @BindView(R.id.btn_bottom_sheet_control)
    Button btn_bottom_sheet_control;

    MyListView mListView;
    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.acitivity_design;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(tab_layout);
        initBottomSheet();
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = new DesignPresenter(this, this);
    }

    @Override
    public void initDate() {
        mPresenter.getBottomSheetDialogData();
    }

    @OnClick({R.id.btn_bottom_sheet_control, R.id.btn_bottom_dialog_control})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn_bottom_sheet_control:
                changeBottomSheetStatus();
                break;
            case R.id.btn_bottom_dialog_control:
                showBottomSheetDialog();
                break;
        }
    }

    @Override
    public void createBottomSheetDialog(List<String> data) {
        bottomSheetDialog = new BottomSheetDialog(this);
        View view = View.inflate(this, R.layout.listview, null);
        mListView = ButterKnife.findById(view, R.id.mListView);
        bottomSheetDialog.setContentView(view);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(500);

        MenuAdapter adapter = new MenuAdapter(this, data, android.R.layout.simple_list_item_1);
        mListView.setAdapter(adapter);
    }

    @Override
    public void showBottomSheetDialog() {
        bottomSheetDialog.show();
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
