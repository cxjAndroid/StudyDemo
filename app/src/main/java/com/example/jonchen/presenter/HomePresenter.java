package com.example.jonchen.presenter;

import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.R;
import com.example.jonchen.activity.HomeFragment;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.fragment.HealthFragment;
import com.example.jonchen.mvpview.HomeView;

import java.util.ArrayList;

/**
 * Created by andychen on 2017/1/22.
 */

public class HomePresenter extends BasePresenter<HomeView> {


    public HomePresenter(HomeView mView) {
        super(mView);
    }

    public void getBottomNavigationData(){
        ArrayList<BottomNavigationItem> navigationItems = new ArrayList<>();
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "one"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "two"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_find_replace_white_24dp, "three"));
        navigationItems.add(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "four"));
        mView.initBottomNavigationBar(navigationItems);
    }

    public void getFragmentPage(){
        ArrayList<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HealthFragment());
        fragmentList.add(new HealthFragment());
        fragmentList.add(new HealthFragment());
        mView.initFragmentPage(fragmentList);
    }
}
