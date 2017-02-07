package com.example.jonchen.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class ZhiHuResult<T> {
    private String date;
    private T stories;
    private T top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public T getStories() {
        return stories;
    }

    public void setStories(T stories) {
        this.stories = stories;
    }

    public T getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(T top_stories) {
        this.top_stories = top_stories;
    }
}
