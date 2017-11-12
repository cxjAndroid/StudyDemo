package com.example.jonchen.mvpview;

import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public interface SearchView extends BaseView {
    void clearSearchContent();

    void refreshHistoryListView(List<String> searchHistory);

    void finishActivity();
}
