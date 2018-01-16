package com.example.jonchen.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.jonchen.R;
import com.example.jonchen.presenter.BasePresenter;
import com.example.jonchen.mvpview.BaseView;
import com.example.jonchen.retrofit.ApiService;
import com.example.jonchen.retrofit.RetrofitMethods;
import com.example.jonchen.swipy_refresh_layout.RefreshLayout;
import com.example.jonchen.swipy_refresh_layout.RefreshLayoutDirection;
import com.example.jonchen.utils.IntentUtils;
import com.example.jonchen.utils.MetricsUtils;
import com.example.jonchen.utils.RxUtils;
import com.example.jonchen.utils.StatusBarUtil;
import com.example.jonchen.view.LoadStatusPage;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by chenxujun on 2016/6/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    public boolean isBindEventBus;
    private Call<?> call;
    private static LoadStatusPage statusPage;
    protected T mPresenter;
    public boolean isNeedBindButterKnife = true;
    public int menuLayout;

   /* public LoadStatusPage getStatusPage() {
        return statusPage;
    }*/

    public T getPresenter() {
        return mPresenter;
    }

    public void setPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
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

    public ApiService getApiService() {
        return RetrofitMethods.getApiService();
    }
    
    //private Object subscriber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentViewLayoutID = getContentViewLayoutID();
        if (contentViewLayoutID != 0) setContentView(contentViewLayoutID);
        if (isNeedBindButterKnife) ButterKnife.bind(this);
        initView();
        initPresenter();
        initData();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
    }

    public abstract int getContentViewLayoutID();

    protected abstract void initView();

    protected void initPresenter() {

    }


    public abstract void initData();

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

    protected RefreshLayout initRefreshLayout(RefreshLayout layout) {
        layout.setEnabled(true);
        layout.setDirection(RefreshLayoutDirection.TOP);
        layout.setDefaultColor();
        return layout;
    }

    protected RefreshLayout initRefreshLayout(RefreshLayout layout, RefreshLayoutDirection direction) {
        layout.setEnabled(true);
        layout.setDirection(direction);
        layout.setDefaultColor();
        return layout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxUtils.get().unSubscribe();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (isBindEventBus) {
            isBindEventBus = false;
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
        if (statusPage != null) {
            statusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
        } else {
            statusPage = new LoadStatusPage(this);
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, MetricsUtils.getStatusPageHeight(this));
            params.gravity = Gravity.BOTTOM;
            statusPage.setGravity(Gravity.BOTTOM);
            addContentView(statusPage, params);
        }
    }

    @Override
    public void showLoadingPage(int loadingLayoutId) {

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
    public LoadStatusPage getStatusPage() {
        return statusPage;
    }

    //NoActionBar style
    public int initToolBar(final Toolbar toolbar, int menuLayout) {
        if (toolbar != null) setSupportActionBar(toolbar);
        this.menuLayout = menuLayout;
        return menuLayout;
    }

    //NoActionBar style
    public int initToolBar(final Toolbar toolbar) {
        return initToolBar(toolbar, 0);
    }

    //DarkActionBar style
    public int initToolBar(int menuLayout) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            this.menuLayout = menuLayout;
        }
        return menuLayout;
    }

    //DarkActionBar style
    public int initToolBar() {
        return initToolBar(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getSupportActionBar() != null) {
            if (menuLayout != 0) {
                getMenuInflater().inflate(menuLayout, menu);
            } else {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            IntentUtils.finishActivity(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            IntentUtils.finishActivity(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
