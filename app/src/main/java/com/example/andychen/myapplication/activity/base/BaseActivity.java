package com.example.andychen.myapplication.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andychen.myapplication.activity.mvp_view_interface.BaseView;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.example.andychen.myapplication.activity.view.LoadStatusPage;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;

/**
 * Created by andychen on 2016/6/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private boolean isBindEventBus;
    private Call<?> call;
    private LoadStatusPage statusPage;

    public LoadStatusPage getStatusPage() {
        return statusPage;
    }

    public void setStatusPage(LoadStatusPage statusPage) {
        this.statusPage = statusPage;
    }

    public Call<?> getCall() {
        return call;
    }

    public void setCall(Call<?> call) {
        this.call = call;
    }

    //private Object subscriber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
    }

    public abstract void initView();

    public abstract void initDate();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
        isBindEventBus = true;
        Toast.makeText(this, "register eventBus", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBus) {
            EventBus.getDefault().unregister(this);
            Toast.makeText(this, "unregister eventBus", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        //RxUtils.get().unSubscribe();
    }

    @Override
    public void showLoadPage() {
        statusPage = new LoadStatusPage(this);
        addContentView(statusPage,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void showErrorPage(int type) {
        statusPage.setStatusType(type);
    }

    @Override
    public void showSuccessPage() {
        statusPage.setStatusType(LoadStatusPage.HIDE_LAYOUT);
        //statusPage.setVisibility(View.GONE);
    }
}
