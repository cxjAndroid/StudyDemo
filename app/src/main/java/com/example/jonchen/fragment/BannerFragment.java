package com.example.jonchen.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.activity.ActionActivity;
import com.example.jonchen.activity.DaggerDemo2Activity;
import com.example.jonchen.activity.NotificationActivity;
import com.example.jonchen.activity.TouchEventActivity;
import com.example.jonchen.adapter.BannerAdapter;
import com.example.jonchen.base.BaseApplication;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.model.entity.People;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.presenter.BannerPresenter;
import com.example.jonchen.receiver.AlarmReceiver;
import com.example.jonchen.service.AlarmService;
import com.example.jonchen.service.MyService;
import com.example.jonchen.utils.CacheUtils;
import com.example.jonchen.utils.DpUtils;
import com.example.jonchen.utils.IntentUtils;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.MetricsUtils;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @BindView(R.id.btnTest)
    Button btnTest;
    @BindView(R.id.btnDemo)
    Button btnDemo;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btnTouch)
    Button btnTouch;
    /*  @BindView(R.id.loadStatusPage)
      LoadStatusPage loadStatusPage;*/
    @BindView(R.id.contentRl)
    RelativeLayout contentRl;
    private Handler handler;
    private MyRunnable myRunnable;
    private BannerAdapter bannerAdapter;
    private List<DailyBean.TopStoriesBean> storiesBeen;
    private int startX;
    private int startY;
    private int moveX;
    private int moveY;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initView() {
        //initToolBar(toolbar, R.menu.menu);

        adjustAdvLayout();
        syncDrawLayout();

        MetricsUtils.getDensity();

     /*   Coder coder = new Coder();
        NewsPaper newsPaper = new NewsPaper();
        newsPaper.addObserver(coder);
        newsPaper.notifyCoder("  this is content");*/

        Class<People> peopleClass = People.class;
        try {
            People people = peopleClass.newInstance();
            Method[] methods = peopleClass.getMethods();
            Method method = peopleClass.getMethod("setName", String.class);
            method.invoke(people, "jon");
            ToastUtils.show(people.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MyHandler(this).sendEmptyMessage(0);
    }


    private static class MyHandler extends Handler {
        private WeakReference<BannerFragment> weakReference;

        MyHandler(BannerFragment fragment) {
            weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            BannerFragment bannerFragment = weakReference.get();
        }
    }


    @Override
    public void initData() {
        registerEventBus();
        getActivity().setResult(Activity.RESULT_OK);
        if (bannerAdapter == null) {

            //loadStatusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
            mPresenter.getBannerInfo();
        } else {
            advViewpager.setAdapter(bannerAdapter);
            describeTv.setVisibility(View.VISIBLE);
            setViewPagerDescribe(storiesBeen);
        }


        ObjectAnimator translationX = ObjectAnimator.ofFloat(btnTest, "translationX", 0, 200f);
        translationX.setDuration(2000).start();

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


        //imageView.setClickable(true);

        /*onTouch事件返回false会使View执行onTouchEvent方法，imageView默认不可以点击，
        无法进入switch (action)语句中，所以会返回false，导致后续action move等动作无法执行。
        可使用imageView.setClickable(true)使其进入switch (action)语句中返回true解决或直接
        在onTouch中返回true不執行onTouchEvent方法。*/
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("onTouch" + event.getAction());
                return false;
            }
        });


        MetricsUtils.measureView(imageView, new MetricsUtils.OnLayoutListener() {
            @Override
            public void onGlobalLayout() {
                LogUtils.e(String.valueOf(imageView.getTop()) + "top--------");
                LogUtils.e(String.valueOf(imageView.getLeft()) + "left--------");
                LogUtils.e(String.valueOf(imageView.getRight()) + "right--------");
                LogUtils.e(String.valueOf(imageView.getBottom()) + "bottom--------");
                LogUtils.e(String.valueOf(imageView.getX()) + "X--------");
                LogUtils.e(String.valueOf(imageView.getY()) + "Y--------");
                LogUtils.e(String.valueOf(imageView.getTranslationX()) + "translationX--------");
                LogUtils.e(String.valueOf(imageView.getTranslationY()) + "translationY--------");
                LogUtils.e(String.valueOf(DpUtils.px2dip(imageView.getRight())) + "--------");
            }
        });


        long start = System.currentTimeMillis();
        long notifyTime = start + 15 * 1000;
        CacheUtils.putLong("notifyTime", notifyTime);
    }


    @Subscribe
    public void onEvent(EventMessage<String> eventMessage) {
        String message = eventMessage.getMessage();
        ToastUtils.show(message);
    }


    @OnClick({R.id.btnTest, R.id.btnDemo, R.id.btnTouch})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTest:
                //IntentUtils.startActivity(getActivity(), DataBindingActivity.class);
                IntentUtils.startActivity(baseActivity, ActionActivity.class);
                break;
            case R.id.btnDemo:
                IntentUtils.startActivity(baseActivity, NotificationActivity.class);
                //addLocalNotification();

                //IntentUtils.startActivity(baseActivity, DaggerDemo2Activity.class);
                //IntentUtils.startActivity(baseActivity, DaggerStudyActivity.class);
                /*Intent intent = new Intent(baseActivity, MyService.class);
                baseActivity.bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        MyService.MyIBinder myIBinder = (MyService.MyIBinder) service;
                        myIBinder.show();
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);*/

                break;
            case R.id.btnTouch:
                IntentUtils.startActivity(baseActivity, TouchEventActivity.class);

                break;
        }

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
