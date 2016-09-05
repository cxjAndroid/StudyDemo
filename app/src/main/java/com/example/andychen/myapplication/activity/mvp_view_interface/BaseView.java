package com.example.andychen.myapplication.activity.mvp_view_interface;

/**
 * Created by andychen on 2016/7/1.
 */
public interface BaseView {

    void showLoadingPage();
    void showErrorPage(int type);
    void showSuccessPage();

}
