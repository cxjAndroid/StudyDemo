package com.example.jonchen.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chenxujun on 2016/7/19.
 */
public abstract class BaseFragment extends Fragment {
    public boolean isNeedBindButterKnife = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container);
        if (isNeedBindButterKnife) {
            ButterKnife.bind(this, view);
        }
        return view;
    }

    /**
     * 子类必须实现此方法, 返回一个View对象, 作为当前Fragment的布局来展示.
     *
     * @param inflater 布局填充器
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 如果子类需要初始化自己的数据, 把此方法给覆盖.
     */
    public void initData() {

    }


}
