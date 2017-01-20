package com.example.jonchen.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.adapter.BannerAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.model.DailyBean;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.presenter.BannerPresenter;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.MetricsUtils;
import com.example.jonchen.view.MyViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxujun on 2016/6/1.
 */
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerView {

    @BindView(R.id.advViewpager)
    MyViewPager advViewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.describeTv)
    TextView describeTv;
    private Handler handler;
    private MyRunnable myRunnable;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, R.menu.menu);
        adjustAdvLayout();
        syncDrawLayout();
    }

    @Override
    public void initDate() {
//        registerEventBus();
        setResult(RESULT_OK);

        mPresenter.getBannerInfo();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new BannerPresenter(this, this);
    }

    @Override
    public void adjustAdvLayout() {
        ViewGroup.LayoutParams layoutParams = advViewpager.getLayoutParams();
        int[] pixels = MetricsUtils.getPixels();
        //高度为宽度的1/2
        layoutParams.height = (int) (pixels[0] * 0.62);
        LogUtils.e(String.valueOf((int) (pixels[0] * 0.62)));
        LogUtils.e(String.valueOf((pixels[0])));
        advViewpager.setLayoutParams(layoutParams);
    }

    @Override
    public void syncDrawLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        //mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void initBanner(final List<DailyBean.TopStoriesBean> storiesBeen) {
        BannerAdapter bannerAdapter = new BannerAdapter(this, storiesBeen);

        advViewpager.setAdapter(bannerAdapter);
        advViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int i = position % storiesBeen.size();
                describeTv.setText(storiesBeen.get(i).getTitle());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        startAnimator();

        myRunnable = new MyRunnable();
        handler = new Handler();
        handler.postDelayed(myRunnable, 2000);
    }

    private void startAnimator() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(advViewpager, "rotation", 0, 360);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(advViewpager, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(advViewpager, "scaleY", 0, 1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(advViewpager, "alpha", 0, 1f);
        rotation.setRepeatCount(5);
        rotation.setDuration(300);
        scaleX.setDuration(3000);
        scaleY.setDuration(3000);
        alpha.setDuration(3000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotation).with(scaleX).with(scaleY).with(alpha);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                describeTv.setVisibility(View.VISIBLE);
            }
        });
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
            advViewpager.setCurrentItem(advViewpager.getCurrentItem() + 1);
            handler.postDelayed(this, 2000);
        }
    }

}
