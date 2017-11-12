package com.example.jonchen.model;

import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public interface SearchModel {
    List<String> getSearchHistory(int count);

    void addToSearchHistory(String keywords);

    List<String> deleteAllHistory();

    void deleteSingleHistory(String keywords);


}
