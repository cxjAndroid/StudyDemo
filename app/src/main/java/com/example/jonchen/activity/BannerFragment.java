package com.example.jonchen.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.adapter.BannerAdapter;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.presenter.BannerPresenter;
import com.example.jonchen.utils.IntentUtils;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.MetricsUtils;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenxujun on 2016/6/1.
 */
public class BannerFragment extends BaseFragment<BannerPresenter> implements BannerView {

    @BindView(R.id.advViewpager)
    MyViewPager advViewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.describeTv)
    TextView describeTv;
    @BindView(R.id.btn)
    Button btnTest;
    @BindView(R.id.imageView)
    ImageView imageView;
    private Handler handler;
    private MyRunnable myRunnable;
    private BannerAdapter bannerAdapter;
    private List<DailyBean.TopStoriesBean> storiesBeen;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        //initToolBar(toolbar, R.menu.menu);
        adjustAdvLayout();
        syncDrawLayout();
    }

    @Override
    public void initData() {
        registerEventBus();
        getActivity().setResult(Activity.RESULT_OK);
        if (bannerAdapter == null) {
            mPresenter.getBannerInfo();
        } else {
            advViewpager.setAdapter(bannerAdapter);
            describeTv.setVisibility(View.VISIBLE);
            setViewPagerDescribe(storiesBeen);
        }

        /*btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("onClick");
            }
        });

        btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("onTouch" + event.getAction());
                return false;
            }
        });*/


        /**
         * onTouch事件返回false会使View执行onTouchEvent方法，imageView默认不可以点击，
         * 无法进入switch (action)语句中，所以会返回false，导致后续action move等动作无法执行。
         * 可使用imageView.setClickable(true)使其进入switch (action)语句中返回true解决或直接
         * 在onTouch中返回true跳过onTouchEvent方法。
         */
        //imageView.setClickable(true);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("onTouch" + event.getAction());
                return false;
            }
        });
    }


    @Subscribe
    public void onEvent(EventMessage<String> eventMessage){
        String message = eventMessage.getMessage();
        ToastUtils.show(message);
    }


    @OnClick(R.id.btn)
    public void onClick(){
        IntentUtils.startActivity(getActivity(),TestActivity.class);
    }



    @Override
    protected void initPresenter() {
        mPresenter = new BannerPresenter(this);
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
                new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        //mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void initBanner(final List<DailyBean.TopStoriesBean> storiesBeen) {
        this.storiesBeen = storiesBeen;
        bannerAdapter = new BannerAdapter(getActivity(), storiesBeen);

        advViewpager.setAdapter(bannerAdapter);
        setViewPagerDescribe(storiesBeen);

        startAnimator();

        myRunnable = new MyRunnable();
        handler = new Handler();
        handler.postDelayed(myRunnable, 2000);
    }

    private void setViewPagerDescribe(final List<DailyBean.TopStoriesBean> storiesBeen) {
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
    public void onDestroy() {
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
