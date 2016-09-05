package com.example.andychen.myapplication.activity.activity;

import android.os.Handler;
import android.os.Message;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.BannerAdapter;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.bean.ShareInfo;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_presenter.BannerPresenter;
import com.example.andychen.myapplication.activity.mvp_view_interface.BannerView;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.MetricsUtils;
import com.example.andychen.myapplication.activity.view.MyViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by andychen on 2016/6/1.
 */
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerView {

    @BindView(R.id.btn_second)
    Button btn_second;
    @BindView(R.id.adv_viewpager)
    MyViewPager adv_viewpager;

    private ViewConfiguration viewConfiguration;
    private MyHandler myHandler;
    private Handler handler;
    private MyRunnable myRunnable;

    @Override
    public void initView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initDate() {
        adjustAdvLayout();
        registerEventBus();
        viewConfiguration = ViewConfiguration.get(this);
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
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) adv_viewpager.getLayoutParams();
        int[] pixels = MetricsUtils.getPixels();
        layoutParams.height = (int) (pixels[1] * 0.55);
        adv_viewpager.setLayoutParams(layoutParams);
    }


    @Override
    public void initBanner(List<ShareInfo> shareInfoList) {
        BannerAdapter bannerAdapter = new BannerAdapter(this, shareInfoList);
        adv_viewpager.setAdapter(bannerAdapter);
       /* myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(0, 2000);*/

        myRunnable = new MyRunnable();
        handler = new Handler();
        handler.postDelayed(myRunnable,2000);
    }


    class MyRunnable implements Runnable {
        @Override
        public void run() {
            adv_viewpager.setCurrentItem(adv_viewpager.getCurrentItem() + 1);
            handler.postDelayed(this, 2000);
        }
    }


    static class MyHandler extends Handler {

        private WeakReference<BannerActivity> weakReference;
        private final BannerActivity bannerActivity;

        public MyHandler(BannerActivity activity) {
            // super();
            weakReference = new WeakReference<>(activity);
            bannerActivity = weakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            bannerActivity.adv_viewpager.setCurrentItem(bannerActivity.adv_viewpager.getCurrentItem() + 1);
            this.sendEmptyMessageDelayed(0, 2000);
        }
    }

    @OnClick(R.id.btn_second)
    void click() {
        //获取touchSlop。该值表示系统所能识别出的被认为是滑动的最小距离
        int touchSlop = viewConfiguration.getScaledTouchSlop();
        //获取Fling速度的最小值和最大值
        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        //判断是否有物理按键
        boolean isHavePermanentMenuKey = viewConfiguration.hasPermanentMenuKey();
        LogUtils.e("touchSlop:" + String.valueOf(touchSlop));
        LogUtils.e("minimumVelocity:" + String.valueOf(minimumVelocity));
        LogUtils.e("maximumVelocity:" + String.valueOf(maximumVelocity));
        LogUtils.e("isHavePermanentMenuKey:" + isHavePermanentMenuKey);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage msg) {
        Toast.makeText(this, (CharSequence) msg.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //myHandler.removeMessages(0);
        handler.removeCallbacks(myRunnable);
    }


}
