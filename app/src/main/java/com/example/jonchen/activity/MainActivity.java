package com.example.jonchen.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.mvpview.HomeView;
import com.example.jonchen.presenter.HomePresenter;
import com.example.jonchen.view.MyViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by andychen on 2017/1/22.
 */

public class MainActivity extends BaseActivity implements HomeView {
    @BindView(R.id.navigationBar)
    BottomNavigationBar navigationBar;
    @BindView(R.id.mViewpager)
    MyViewPager mViewpager;
    private ViewPageAdapter pageAdapter;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void initData() {
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getBottomNavigationData();
        homePresenter.getFragmentPage();
    }

    @Override
    public void initBottomNavigationBar(List<BottomNavigationItem> itemList) {
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        navigationBar.setActiveColor(R.color.colorPrimary);
        for (BottomNavigationItem item : itemList) {
            navigationBar.addItem(item);
        }
        navigationBar.initialise();
        navigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mViewpager.setCurrentItem(position,false);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void initFragmentPage(final List<BaseFragment> fragmentList) {
        pageAdapter = new ViewPageAdapter(getSupportFragmentManager(), fragmentList);
        mViewpager.setAdapter(pageAdapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPageAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> fragmentList;

        ViewPageAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container,position,object);
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
