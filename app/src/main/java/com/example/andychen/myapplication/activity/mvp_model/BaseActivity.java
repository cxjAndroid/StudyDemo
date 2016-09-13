package com.example.andychen.myapplication.activity.mvp_model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.LayoutDirection;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.andychen.myapplication.activity.mvp_presenter.BasePresenter;
import com.example.andychen.myapplication.activity.mvp_view.BaseView;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.MetricsUtils;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
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
    private int menuLayout;
    private int loadingPageHeight;


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
        int contentViewLayoutID = getContentViewLayoutID();
        if (contentViewLayoutID != 0)
            setContentView(contentViewLayoutID);

        if (isNeedBindButterKnife) ButterKnife.bind(this);
        initView();
        initPresenter();
        initDate();
    }

    protected abstract void initView();

    protected void initPresenter() {

    }

    public abstract int getContentViewLayoutID();

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
        int[] pixels = MetricsUtils.getPixels();

        if (statusPage != null) {
            statusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
        } else {
            statusPage = new LoadStatusPage(this);
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, pixels[1] - loadingPageHeight);
            params.gravity = Gravity.BOTTOM;
            statusPage.setGravity(Gravity.BOTTOM);
            addContentView(statusPage, params);
        }
    }

    @Override
    public void showErrorPage(int type) {
        if (statusPage != null) statusPage.setStatusType(type);
    }

    @Override
    public void showSuccessPage() {
        if (statusPage != null) statusPage.setStatusType(LoadStatusPage.HIDE_LAYOUT);
    }

    @Override
    public int initToolBar(final Toolbar toolbar, int menuLayout) {

        final int statusBarHeight = MetricsUtils.getStatusBarHeight();
        final int navigationBarHeight = MetricsUtils.getNavigationBarHeight();
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        loadingPageHeight = actionBarHeight + statusBarHeight + navigationBarHeight;

        setSupportActionBar(toolbar);
        this.menuLayout = menuLayout;
        return menuLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getSupportActionBar() != null && menuLayout != 0) {
            getMenuInflater().inflate(menuLayout, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

}
