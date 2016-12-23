package com.example.andychen.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.andychen.adapter.WatchListAdapter;
import com.example.andychen.base.BaseActivity;
import com.example.andychen.model.WatchInfo;
import com.example.andychen.mvpview.WatchListView;
import com.example.andychen.myapplication.R;
import com.example.andychen.presenter.WatchListPresenter;
import com.example.andychen.view.MyRecyclerView;

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
        WatchListAdapter adapter = new WatchListAdapter(watchInfoList, R.layout.item_watch);
        myRecyclerView.setAdapter(adapter);
    }
}
