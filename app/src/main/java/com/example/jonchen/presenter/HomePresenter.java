package com.example.jonchen.presenter;

import com.example.jonchen.model.HomeModel;
import com.example.jonchen.model.impl.HomeModelImpl;
import com.example.jonchen.mvpview.HomeView;
import com.example.jonchen.utils.ToastUtils;

/**
 * Created by andychen on 2017/1/22.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel homeModel;


    public HomePresenter(HomeView mView) {
        super(mView);
        homeModel = new HomeModelImpl();
    }

    public void getBottomNavigationData() {
        getView().initBottomNavigationBar(homeModel.initBottomNavigationData());
    }

    public void getFragmentPage() {
        getView().initFragmentPage(homeModel.initFragmentPage());
    }
}
