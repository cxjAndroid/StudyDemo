package com.example.andychen.myapplication.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.DoctorListAdapter;
import com.example.andychen.myapplication.activity.adapter.MenuAdapter;
import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_presenter.MainPresenter;
import com.example.andychen.myapplication.activity.mvp_view.MainView;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.view.MyListView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, MenuAdapter.MenuItemCallBack {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.slidingLayout)
    SlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.menu_list)
    MyListView menu_list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void adjustView() {
        super.adjustView();
        setSupportActionBar(toolbar);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void createSlidingMenuView(List<String> list) {
        MenuAdapter adapter = new MenuAdapter(this, list, android.R.layout.simple_list_item_1);
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
        DoctorListAdapter adapter = new DoctorListAdapter(this, doctorList, R.layout.item_doctor);
        mListView.setAdapter(adapter);
    }


    @OnClick({R.id.btn, R.id.btn1, R.id.iv})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn(this, BannerActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn(this, ThirdActivity.class);
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
}
