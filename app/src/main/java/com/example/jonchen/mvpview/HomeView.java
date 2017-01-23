package com.example.jonchen.mvpview;

import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.base.BaseFragment;

import java.util.List;

/**
 * Created by andychen on 2017/1/22.
 */

public interface HomeView extends BaseView {
    /**
     * 初始化底部导航栏
     */
    void initBottomNavigationBar(List<BottomNavigationItem> itemList);

    /**
     * 初始化fragment
     */
    void initFragmentPage(List<BaseFragment> fragmentList);
}
