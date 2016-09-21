package com.example.andychen.myapplication.activity.activity;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.BannerAdapter;
import com.example.andychen.myapplication.activity.bean.ShareInfo;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_presenter.BannerPresenter;
import com.example.andychen.myapplication.activity.mvp_view.BannerView;
import com.example.andychen.myapplication.activity.utils.MetricsUtils;
import com.example.andychen.myapplication.activity.view.MyViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxujun on 2016/6/1.
 */
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerView {

    @BindView(R.id.adv_viewpager)
    MyViewPager adv_viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private Handler handler;
    private MyRunnable myRunnable;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, R.menu.menu);
        adjustAdvLayout();
        syncDrawLayout();
    }

    @Override
    public void initDate() {
        registerEventBus();
        setResult(RESULT_OK);

        showLoadingPage();
        mPresenter.getShareInfo();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new BannerPresenter(this, this);
    }

    @Override
    public void adjustAdvLayout() {
        ViewGroup.LayoutParams layoutParams = adv_viewpager.getLayoutParams();
        int[] pixels = MetricsUtils.getPixels();
        layoutParams.height = (int) (pixels[0] * 0.5);
        adv_viewpager.setLayoutParams(layoutParams);
    }

    @Override
    public void syncDrawLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                        R.string.drawer_open, R.string.drawer_close) {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        setTitle(getString(R.string.drawer_open));
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        setTitle(getString(R.string.app_name));
                    }
                };
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void initBanner(List<ShareInfo> shareInfoList) {
        BannerAdapter bannerAdapter = new BannerAdapter(this, shareInfoList);
        adv_viewpager.setAdapter(bannerAdapter);

        myRunnable = new MyRunnable();
        handler = new Handler();
        handler.postDelayed(myRunnable, 2000);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage msg) {
        //Toast.makeText(this, (CharSequence) msg.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myRunnable != null) {
            handler.removeCallbacks(myRunnable);
        }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            adv_viewpager.setCurrentItem(adv_viewpager.getCurrentItem() + 1);
            handler.postDelayed(this, 2000);
        }
    }

}
