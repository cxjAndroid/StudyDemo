package com.example.jonchen.activity;

import android.support.v7.widget.Toolbar;

import com.example.jonchen.adapter.WatchListAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.model.WatchInfo;
import com.example.jonchen.mvpview.WatchListView;
import com.example.jonchen.R;
import com.example.jonchen.presenter.WatchListPresenter;
import com.example.jonchen.view.MyRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListActivity extends BaseActivity<WatchListPresenter> implements WatchListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mRecyclerView)
    MyRecyclerView myRecyclerView;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_watch_list;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
        showLoadingPage();
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = new WatchListPresenter(this, this);
    }

    @Override
    public void initDate() {
        mPresenter.getWatchList("13691993691");
    }

    @Override
    public void RefreshWatchList(List<WatchInfo> watchInfoList) {
        showSuccessPage();
        WatchListAdapter adapter = new WatchListAdapter(this,watchInfoList, R.layout.item_watch);
        myRecyclerView.setAdapter(adapter);
    }
}
