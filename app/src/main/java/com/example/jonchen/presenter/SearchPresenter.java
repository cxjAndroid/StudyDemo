package com.example.jonchen.presenter;

import com.example.jonchen.model.SearchModel;
import com.example.jonchen.model.impl.SearchModelImpl;
import com.example.jonchen.mvpview.SearchView;
import com.example.jonchen.utils.StringUtils;
import com.example.jonchen.utils.ToastUtils;

import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public class SearchPresenter extends BasePresenter<SearchView> {

    private final SearchView searchView;
    private SearchModel searchModel;

    public SearchPresenter(SearchView mView) {
        super(mView);
        searchView = getView();
        searchModel = new SearchModelImpl();
    }

    /**
     * 清空搜索文案
     */
    public void clear() {
        if (searchView != null) searchView.clearSearchContent();
    }

    /**
     * 券搜索
     *
     * @param keyword 搜索关键字
     */
    public void searchCoupon(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            if (searchView != null) searchView.finishActivity();
            return;
        }
        ToastUtils.show("search");
        searchModel.addToSearchHistory(keyword);
    }

    /**
     * 查询搜索历史，限制10条
     */
    public void getSearchHistory() {
        List<String> searchHistory = searchModel.getSearchHistory(10);
        if (searchView != null) searchView.refreshHistoryListView(searchHistory);
    }

    public void deleteAllHistory() {
        List<String> searchHistory = searchModel.deleteAllHistory();
        if (searchView != null) searchView.refreshHistoryListView(searchHistory);
    }

    public void deleteSingleHistory(String keywords){
        searchModel.deleteSingleHistory(keywords);
    }

}
