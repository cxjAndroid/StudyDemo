package com.example.andychen.myapplication.activity.mvp_model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.andychen.myapplication.activity.mvp_presenter.BasePresenter;
import com.example.andychen.myapplication.activity.mvp_view.BaseView;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.example.andychen.myapplication.activity.view.LoadStatusPage;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by andychen on 2016/6/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private boolean isBindEventBus;
    private Call<?> call;
    private LoadStatusPage statusPage;
    public T mPresenter;
    public boolean isNeedBindButterKnife = true;


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
        if (isNeedBindButterKnife) ButterKnife.bind(this);
        adjustView();
        initPresenter();
        initDate();

    }

    protected void adjustView() {
    }

    protected void initPresenter() {

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
        //Toast.makeText(this, "register eventBus", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxUtils.get().unSubscribe();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (isBindEventBus) {
            EventBus.getDefault().unregister(this);
            //Toast.makeText(this, "unregister eventBus", Toast.LENGTH_SHORT).show();
        }

    }

    public void changeFragment(int containerId, BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment,
                fragment.getClass().getSimpleName()).commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showLoadingPage() {
        statusPage = new LoadStatusPage(this);
        addContentView(statusPage,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void showErrorPage(int type) {
        if (statusPage != null) statusPage.setStatusType(type);
    }

    @Override
    public void showSuccessPage() {
        if (statusPage != null) statusPage.setStatusType(LoadStatusPage.HIDE_LAYOUT);
    }
}
