package com.example.jonchen.activity;

import android.content.Intent;
import android.view.View;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.mvpview.NotifyView;
import com.example.jonchen.presenter.NotifyPresenter;

import butterknife.OnClick;

/**
 * @author 17041931
 * @since 2017/10/11
 */

public class NotificationActivity extends BaseActivity<NotifyPresenter> implements NotifyView {
    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new NotifyPresenter(this);
    }

    @Override
    public void showNotification(Intent intent) {
        startService(intent);
    }

    @OnClick(R.id.btn_notify)
    public void onClick(View v) {
        mPresenter.createNotifyIntent();
    }
}
