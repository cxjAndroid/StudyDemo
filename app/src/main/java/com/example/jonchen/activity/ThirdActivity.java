package com.example.jonchen.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jonchen.R;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.fragment.HealthFragment;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.view.MyViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//Created by chenxujun on 2016/6/12.


public class ThirdActivity extends BaseActivity {
    @BindView(R.id.mViewpager)
    MyViewPager mViewPager;
    @BindView(R.id.smartTab)
    SmartTabLayout smartTab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String[] arr;
    private ArrayList<HealthFragment> fragmentList;

    private boolean flag = true;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_third;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
    }

    @Override
    public void initDate() {

        ButterKnife.bind(this);
        registerEventBus();

        fragmentList = new ArrayList<>();
        arr = new String[]{"血氧", "血糖", "体温", "血压", "身高体重"};

        FragmentPagerItems pages = new FragmentPagerItems(this);

        for (String item : arr) {
            HealthFragment healthFragment = new HealthFragment();
            fragmentList.add(healthFragment);
            pages.add(FragmentPagerItem.of(item, HealthFragment.class));
        }

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        smartTab.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (flag) {
                    fragmentList.get(0).setText(arr[0]);
                    flag = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                String text = arr[position];
                fragmentList.get(position).setText(text);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage<String> message) {
        String s = message.getMessage();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }


    class MyViewPagerAdapter extends FragmentPagerAdapter {

        MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
