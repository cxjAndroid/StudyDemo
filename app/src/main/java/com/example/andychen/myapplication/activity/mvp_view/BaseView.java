package com.example.andychen.myapplication.activity.mvp_view;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;

/**
 * Created by andychen on 2016/7/1.
 */
public interface BaseView {

    void showLoadingPage();

    void showErrorPage(int type);

    void showSuccessPage();

    int initToolBar(Toolbar toolbar, int menuLayout);

}
