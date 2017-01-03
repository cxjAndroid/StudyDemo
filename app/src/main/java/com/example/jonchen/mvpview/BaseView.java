package com.example.jonchen.mvpview;


/**
 * Created by chenxujun on 2016/7/1.
 */
public interface BaseView {

    /**
     * 显示正在加载页面
     */
    void showLoadingPage();
    /**
     * 显示错误页面
     */
    void showErrorPage(int type);
    /**
     * 显示成功页面
     */
    void showSuccessPage();

}
