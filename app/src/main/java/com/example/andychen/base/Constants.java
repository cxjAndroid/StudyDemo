package com.example.andychen.base;

/**
 * Created by chenxujun on 16-10-23.
 */

public class Constants {

    /**
     * 分页加载时每一页的Item数量。
     */
    public static final int VOICE_PAGE_SIZE = 15;
    /**聊天页面的消息列表时间显示间隔*/
    public static final int CHAT_MSG_TIME_SHOW_SPACE = 5 * 1000 * 60;

    public interface ContentType {

        /**
         * 文本
         */
        int TEXT = 1;

        /**
         * 图片
         */
        int IMAGE = 3;

        /**
         * 语音
         */
        int VOICE = 2;

        /**
         * 提示性消息
         */
        int TIP = 4;
    }

    /**
     * 布局类型
     */
    public interface LayoutType {

        /**
         * 左
         */
        int LEFT = 0;

        /**
         * 右
         */
        int RIGHT = 1;

        /**
         * 中
         */
        int CENTER = 2;
    }
}
