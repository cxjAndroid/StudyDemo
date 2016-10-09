package com.example.andychen.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.adapter.DoctorListAdapter;
import com.example.andychen.adapter.MenuAdapter;
import com.example.andychen.base.BaseRecyclerAdapter;
import com.example.andychen.base.BaseRecyclerViewHolder;
import com.example.andychen.mvp_model.Doctor;
import com.example.andychen.event.EventMessage;
import com.example.andychen.base.BaseActivity;
import com.example.andychen.mvp_presenter.MainPresenter;
import com.example.andychen.mvp_view.MainView;
import com.example.andychen.utils.IntentUtils;
import com.example.andychen.utils.ToastUtils;
import com.example.andychen.view.MyListView;
import com.example.andychen.view.MyRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
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
    /*@BindView(R.id.mListView)
    ListView mListView;*/
    @BindView(R.id.recyclerView)
    MyRecyclerView recyclerView;
    @BindView(R.id.slidingLayout)
    SlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.menu_list)
    MyListView menu_list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, R.menu.menu);
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);
        showLoadingPage();
        mPresenter.getDoctorsInfo("10");
        mPresenter.getSlidingMenuData();
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
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mListView.setAdapter(adapter);
    }

    @OnClick({R.id.btn, R.id.btn1})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn(this, BannerActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn(this, DesignActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
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