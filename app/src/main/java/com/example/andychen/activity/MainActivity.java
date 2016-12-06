package com.example.andychen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.example.andychen.adapter.DoctorListAdapter;
import com.example.andychen.adapter.MenuAdapter;
import com.example.andychen.base.BaseActivity;
import com.example.andychen.event.EventMessage;
import com.example.andychen.model.ChatMessage;
import com.example.andychen.model.Doctor;
import com.example.andychen.mvpview.MainView;
import com.example.andychen.myapplication.R;
import com.example.andychen.presenter.MainPresenter;
import com.example.andychen.utils.AnimatorUtil;
import com.example.andychen.utils.IntentUtils;
import com.example.andychen.utils.LogUtils;
import com.example.andychen.utils.ToastUtils;
import com.example.andychen.view.MyListView;
import com.example.andychen.view.MyRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, MenuAdapter.MenuItemCallBack {
    @BindView(R.id.btn)
    Button btn;
    /*@BindView(R.id.iv)
    ImageView iv;*/
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.recyclerView)
    MyRecyclerView recyclerView;
    @BindView(R.id.slidingLayout)
    SlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.menu_list)
    MyListView menu_list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.floatBtn)
    FloatingActionButton floatBtn;
    @BindView(R.id.rl_main)
    CoordinatorLayout rl_main;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, R.menu.menu);
        AnimatorUtil.scaleHide(floatBtn, 0, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDate();
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);
        showLoadingPage();
        mPresenter.getDoctorsInfo("20");
        mPresenter.getSlidingMenuData();

        btn.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                LogUtils.e(String.valueOf(btn.getLeft()));
                LogUtils.e(String.valueOf(btn.getTop()));
                LogUtils.e(String.valueOf(btn.getRight()));
                LogUtils.e(String.valueOf(btn.getBottom()));
            }
        });
    }


    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_capture:
                IntentUtils.startActivityLeftIn(this, CaptureActivity.class, 0);
                break;
            case R.id.action_about_us:
                ToastUtils.show("action_about_us");
                break;
            case R.id.action_feedback:
                ToastUtils.show("action_feedback");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createSlidingMenuView(List<String> data) {
        MenuAdapter adapter = new MenuAdapter(data, android.R.layout.simple_list_item_1, slidingPaneLayout);
        adapter.setMenuItemCallBack(this);
        menu_list.setAdapter(adapter);
    }

    @Override
    public void menuItemOnClick(String s) {
        showLoadingPage();
        mPresenter.getDoctorsInfo(s);
        slidingPaneLayout.closePane();
    }

    @Override
    public void refreshDocList(List<Doctor> doctorList) {
        DoctorListAdapter adapter = new DoctorListAdapter(doctorList, R.layout.item_doctor);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.btn, R.id.btn1, R.id.btn2, R.id.floatBtn})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn(this, BannerActivity.class);
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn(this, DesignActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.btn2:
                IntentUtils.startActivityLeftIn(this, DrawViewActivity.class);
                break;
            case R.id.floatBtn:
                recyclerView.getLayoutManager().scrollToPosition(0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            tv.setText(extras.getString("result"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
