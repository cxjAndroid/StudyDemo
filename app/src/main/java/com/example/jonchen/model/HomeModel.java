package com.example.jonchen.model;

import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by andychen on 2017/2/8.
 */

public interface HomeModel {
    ArrayList<BottomNavigationItem> initBottomNavigationData();

    ArrayList<BaseFragment> initFragmentPage();
}
