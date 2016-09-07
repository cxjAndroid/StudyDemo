package com.example.andychen.myapplication.activity.activity;

import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.BannerAdapter;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.bean.ShareInfo;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_presenter.BannerPresenter;
import com.example.andychen.myapplication.activity.mvp_view.BannerView;
import com.example.andychen.myapplication.activity.utils.MetricsUtils;
import com.example.andychen.myapplication.activity.view.MyViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * Created by andychen on 2016/6/1.
 */
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerView {

    @BindView(R.id.btn_second)
    Button btn_second;
    @BindView(R.id.adv_viewpager)
    MyViewPager adv_viewpager;
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
        layoutParams.height = (int) (pixels[1] * 0.5);
        adv_viewpager.setLayoutParams(layoutParams);
    }


    @Override
    public void initBanner(List<ShareInfo> shareInfoList) {
        BannerAdapter bannerAdapter = new BannerAdapter(this, shareInfoList);
        adv_viewpager.setAdapter(bannerAdapter);

        myRunnable = new MyRunnable();
        handler = new Handler();
        handler.postDelayed(myRunnable,2000);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage msg) {
        //Toast.makeText(this, (CharSequence) msg.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myRunnable!=null) {
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
