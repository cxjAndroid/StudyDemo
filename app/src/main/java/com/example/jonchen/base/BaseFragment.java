package com.example.jonchen.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.jonchen.R;
import com.example.jonchen.mvpview.BaseView;
import com.example.jonchen.presenter.BasePresenter;
import com.example.jonchen.swipy_refresh_layout.RefreshLayout;
import com.example.jonchen.swipy_refresh_layout.RefreshLayoutDirection;
import com.example.jonchen.utils.DpUtils;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.MetricsUtils;
import com.example.jonchen.utils.RxUtils;
import com.example.jonchen.view.LoadStatusPage;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by chenxujun on 2016/7/19.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    public boolean isNeedBindButterKnife = true;
    private boolean isBindEventBus;
    public T mPresenter;
    private LoadStatusPage statusPage;
    protected View mFragmentView;
    private boolean isViewCreated;
    protected BaseActivity baseActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e(getClass().getSimpleName() + "------" + "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.e(getClass().getSimpleName() + "------" + "onCreateView");
        int contentViewLayoutID = getContentViewLayoutID();
        mFragmentView = inflater.inflate(contentViewLayoutID, null);
        if (isNeedBindButterKnife) {
            ButterKnife.bind(this, mFragmentView);
        }
        initPresenter();
        isViewCreated = true;
        baseActivity = (BaseActivity) getActivity();
        return mFragmentView;
    }

    public abstract int getContentViewLayoutID();

    protected abstract void initView();

    protected void initPresenter() {

    }

    public int initToolBar(final Toolbar toolbar, int menuLayout) {
        setHasOptionsMenu(true);
        baseActivity.menuLayout = menuLayout;
        if (toolbar != null) baseActivity.setSupportActionBar(toolbar);
        return menuLayout;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.e(getClass().getSimpleName() + "------" + "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        //if (getUserVisibleHint()) {
        initView();
        initData();
        //}
    }

    /**
     * 如果子类需要初始化自己的数据, 把此方法给覆盖.
     */
    protected abstract void initData();

    public void registerEventBus() {
        EventBus.getDefault().register(this);
        isBindEventBus = true;
        //Toast.makeText(this, "register eventBus", Toast.LENGTH_SHORT).show();
    }

    protected RefreshLayout initRefreshLayout(RefreshLayout layout) {
        layout.setEnabled(true);
        layout.setDirection(RefreshLayoutDirection.TOP);
        layout.setDefaultColor();
        return layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(getClass().getSimpleName() + "------" + "onDestroy");
        RxUtils.get().unSubscribe();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    public void showLoadingPage() {
        if (statusPage != null) {
            statusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
        } else {
            statusPage = new LoadStatusPage(getActivity());
            statusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(0, MetricsUtils.getActionBarHeight(getActivity()), 0, DpUtils.dip2px(56));
            getActivity().addContentView(statusPage, params);
        }
    }

    @Override
    public void showLoadingPage(int rootLayoutId) {

        statusPage = new LoadStatusPage(getActivity());
        statusPage.setStatusType(LoadStatusPage.NETWORK_LOADING);
        ViewGroup loadingView = (ViewGroup) mFragmentView.findViewById(rootLayoutId);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        //params.gravity = Gravity.BOTTOM;
        statusPage.setPadding(0, MetricsUtils.getActionBarHeight(getActivity()), 0, 0);
        loadingView.addView(statusPage, params);
    }


    @Override
    public void showErrorPage(int type) {
        if (statusPage != null) statusPage.setStatusType(type);
    }

    @Override
    public void showSuccessPage() {
        if (statusPage != null) statusPage.setStatusType(LoadStatusPage.HIDE_LAYOUT);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.e(getClass().getSimpleName() + "------" + "setUserVisibleHint" + "--------" + isVisibleToUser);

        /*if (isVisibleToUser && isViewCreated) {
            initData();
        }*/
    }

    @Override
    public LoadStatusPage getStatusPage() {
        return statusPage;
    }

    @Override
    public void onAttach(Context context) {
        LogUtils.e(getClass().getSimpleName() + "------" + "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        isViewCreated = false;
        LogUtils.e(getClass().getSimpleName() + "------" + "onDestroyView");
        if (isBindEventBus) {
            isBindEventBus = false;
            EventBus.getDefault().unregister(this);
            //Toast.makeText(this, "unregister eventBus", Toast.LENGTH_SHORT).show();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtils.e(getClass().getSimpleName() + "------" + "onDetach");
        super.onDetach();
    }
}
