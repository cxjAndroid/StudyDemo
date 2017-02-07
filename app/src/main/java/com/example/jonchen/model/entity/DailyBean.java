package com.example.jonchen.model.entity;

import java.util.List;

/**
 * Created by andychen on 2017/1/11.
 */

public class DailyBean {

    /**
     * date : 20170111
     * stories : [{"images":["http://pic2.zhimg.com/57637df37b6f87afaf46710bb0046d8d.jpg"],"type":0,"id":9139108,"ga_prefix":"011110","title":"为什么西欧国家可以做到新老建筑并存？"},{"images":["http://pic2.zhimg.com/48e47d1b8c537284afd4ed75fe62ea51.jpg"],"type":0,"id":9138899,"ga_prefix":"011109","title":"从长期影响来看，2016 年我国最重要的公共政策是什么？"},{"images":["http://pic3.zhimg.com/6047fa66d863b0f5fa2fd1ade8534e4a.jpg"],"type":0,"id":9129166,"ga_prefix":"011108","title":"现代工业造物中的最高难度，在我眼中就是它了"},{"images":["http://pic3.zhimg.com/9ca4aa0618c0ced5b5f0945b9d683506.jpg"],"type":0,"id":9139045,"ga_prefix":"011107","title":"用生日做密码，真的是给破解者的一道送分题"},{"images":["http://pic4.zhimg.com/76ff06a486e2a6cee8468c7e9b96c523.jpg"],"type":0,"id":9138877,"ga_prefix":"011107","title":"《侠盗一号》中有哪些不易察觉的彩蛋或细节？（剧透）"},{"images":["http://pic3.zhimg.com/35ddb902ee00f18951aaffbd3f4994ca.jpg"],"type":0,"id":9138568,"ga_prefix":"011107","title":"和屠呦呦同获国家最高科技奖的赵忠贤院士有哪些贡献？"},{"images":["http://pic3.zhimg.com/980fe7183fa7b209f597e3f6a9964522.jpg"],"type":0,"id":9138195,"ga_prefix":"011106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/91e11355a8511923c6ee2e35b02535cb.jpg","type":0,"id":9138877,"ga_prefix":"011107","title":"《侠盗一号》中有哪些不易察觉的彩蛋或细节？（剧透）"},{"image":"http://pic2.zhimg.com/0ef4b8696e7a3b4f1b73c29d18b481cd.jpg","type":0,"id":9138899,"ga_prefix":"011109","title":"从长期影响来看，2016 年我国最重要的公共政策是什么？"},{"image":"http://pic4.zhimg.com/dde505464058552a4bd807c27c1347a3.jpg","type":0,"id":9138568,"ga_prefix":"011107","title":"和屠呦呦同获国家最高科技奖的赵忠贤院士有哪些贡献？"},{"image":"http://pic2.zhimg.com/2f176e460d8b46f7ca68ed678614e609.jpg","type":0,"id":9138907,"ga_prefix":"011017","title":"知乎好问题 · 年轻人如何预防和及时发现癌症？"},{"image":"http://pic1.zhimg.com/9bc9cc38a9d4333443045fa341c81d50.jpg","type":0,"id":9138067,"ga_prefix":"011013","title":"「别人可以修改你的支付宝密码」漏洞，是哪里出了问题？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic2.zhimg.com/57637df37b6f87afaf46710bb0046d8d.jpg"]
         * type : 0
         * id : 9139108
         * ga_prefix : 011110
         * title : 为什么西欧国家可以做到新老建筑并存？
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

    public static class TopStoriesBean {
        /**
         * image : http://pic4.zhimg.com/91e11355a8511923c6ee2e35b02535cb.jpg
         * type : 0
         * id : 9138877
         * ga_prefix : 011107
         * title : 《侠盗一号》中有哪些不易察觉的彩蛋或细节？（剧透）
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
    }
}
