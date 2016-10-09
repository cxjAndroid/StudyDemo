package com.example.andychen.base;

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

import com.example.andychen.myapplication.R;
import com.example.andychen.mvp_presenter.BasePresenter;
import com.example.andychen.mvp_view.BaseView;
import com.example.andychen.utils.MetricsUtils;
import com.example.andychen.utils.RxUtils;
import com.example.andychen.view.LoadStatusPage;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by chenxujun on 2016/6/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private boolean isBindEventBus;
    private Call<?> call;
    private LoadStatusPage statusPage;
    public T mPresenter;
    public boolean isNeedBindButterKnife = true;
    private int menuLayout;

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

    public abstract int getContentViewLayoutID();

    protected abstract void initView();

    protected void initPresenter() {

    }


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
    public void showErrorPage(int type) {
        if (statusPage != null) statusPage.setStatusType(type);
    }

    @Override
    public void showSuccessPage() {
        if (statusPage != null) statusPage.setStatusType(LoadStatusPage.HIDE_LAYOUT);
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
            finish();
            activityRightOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            activityRightOut();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 页面进入动画
     */
    public void activityLeftIn() {
        overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    /**
     * 页面退出动画
     */
    public void activityRightOut() {
        overridePendingTransition(R.anim.in_anim_exit, R.anim.anim_exit);
    }

    /**
     * 页面上升动画
     */
    public void activityUpIn() {
        overridePendingTransition(R.anim.push_up_in, R.anim.enter);
    }

    /**
     * 页面下降动画
     */
    public void activityUpOut() {
        overridePendingTransition(R.anim.enter, R.anim.push_up_out);
    }

}