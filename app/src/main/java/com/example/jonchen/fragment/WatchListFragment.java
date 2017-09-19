package com.example.jonchen.fragment;

import android.support.v7.widget.Toolbar;

import com.example.jonchen.adapter.WatchListAdapter;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.model.entity.WatchInfo;
import com.example.jonchen.mvpview.WatchListView;
import com.example.jonchen.R;
import com.example.jonchen.presenter.WatchListPresenter;
import com.example.jonchen.view.MyRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListFragment extends BaseFragment<WatchListPresenter> implements WatchListView {

   /* @BindView(R.id.toolbar)
    Toolbar toolbar;*/
    @BindView(R.id.mRecyclerView)
    MyRecyclerView myRecyclerView;
    private WatchListAdapter adapter;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_watch_list;
    }

    @Override
    protected void initView() {
        //initToolBar(toolbar);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = new WatchListPresenter(this);
    }

    @Override
    public void initData() {
        if (adapter == null) mPresenter.getWatchList("13691993691");
        else myRecyclerView.setAdapter(adapter);
    }

    @Override
    public void RefreshWatchList(List<WatchInfo> watchInfoList) {
        showSuccessPage();
        adapter = new WatchListAdapter(getActivity(), watchInfoList, R.layout.item_watch);
        myRecyclerView.setAdapter(adapter);
    }
}
