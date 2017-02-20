package com.example.jonchen.model.impl;

import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.R;
import com.example.jonchen.fragment.BannerFragment;
import com.example.jonchen.fragment.DrawViewFragment;
import com.example.jonchen.fragment.ZhFragment;
import com.example.jonchen.fragment.WatchListFragment;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.model.HomeModel;

import java.util.ArrayList;

/**
 * Created by andychen on 2017/2/8.
 */

public class HomeModelImpl implements HomeModel {

    @Override
    public ArrayList<BottomNavigationItem> initBottomNavigationData() {
        ArrayList<BottomNavigationItem> navigationItems = new ArrayList<>();
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "one"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "two"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_find_replace_white_24dp, "three"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "four"));
        return navigationItems;
    }

    @Override
    public ArrayList<BaseFragment> initFragmentPage() {
        ArrayList<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BannerFragment());
        fragmentList.add(new ZhFragment());
        fragmentList.add(new WatchListFragment());
        fragmentList.add(new DrawViewFragment());
        return fragmentList;
    }
}
