package com.example.jonchen.model.impl;

import com.example.jonchen.database.SearchDao;
import com.example.jonchen.model.SearchModel;

import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public class SearchModelImpl implements SearchModel {

    private SearchDao searchDao;

    public SearchModelImpl() {
        this.searchDao = new SearchDao();
    }

    @Override
    public List<String> getSearchHistory(int count) {
        return searchDao.queryAllKeywords(10);
    }

    @Override
    public void addToSearchHistory(String keywords) {
        searchDao.addKeywords(keywords);
    }

    @Override
    public List<String> deleteAllHistory() {
        return searchDao.deleteAllKeywords();
    }

    @Override
    public void deleteSingleHistory(String keywords) {
        searchDao.deleteKeywords(keywords);
    }
}
