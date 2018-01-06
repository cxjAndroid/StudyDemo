package com.example.jonchen.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jonchen.R;
import com.example.jonchen.adapter.SearchHistoryAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.mvpview.SearchView;
import com.example.jonchen.presenter.SearchPresenter;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {

    @BindView(R.id.et_search_input)
    EditText etSearchInput;
    @BindView(R.id.search_history_recycle_view)
    MyRecyclerView searchHistoryRecycleView;
    @BindView(R.id.rl_search_history)
    RelativeLayout searchHistoryRL;
    @BindView(R.id.image_clear_history)
    ImageView clearHistoryImage;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_search_coupon;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getSearchHistory();
    }

    @Override
    public void clearSearchContent() {
        etSearchInput.setText("");
    }

    @Override
    public void refreshHistoryListView(List<String> searchHistory) {
        searchHistoryRL.setVisibility(searchHistory.size() > 0 ? View.VISIBLE : View.GONE);
        SearchHistoryAdapter searchHistoryAdapter
                = new SearchHistoryAdapter(searchHistory, R.layout.item_search, this);
        searchHistoryRecycleView.setAdapter(searchHistoryAdapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SearchPresenter(this);
    }

    @OnClick({R.id.img_search_input_delete, R.id.tv_search, R.id.image_clear_history})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_search_input_delete:
                mPresenter.clear();
                break;
            case R.id.tv_search:
                mPresenter.searchCoupon(etSearchInput.getText().toString());
                break;
            case R.id.image_clear_history:
                mPresenter.deleteAllHistory();
                break;
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
