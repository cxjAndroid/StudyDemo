package com.example.jonchen.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DailyNewspaper {

    /**
     * images : ["http://pic1.zhimg.com/3f7f378c03475d718bb389cd9a54d6d4.jpg"]
     * type : 0
     * id : 9063433
     * ga_prefix : 011008
     * title : 在市区开车，每个细节都来自多年的经验
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
