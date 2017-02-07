package com.example.jonchen.view;

import android.media.MediaRecorder;

import com.example.jonchen.model.entity.ChatMessage;

import java.util.List;


/**
 * Created by chenxujun on 16-10-25.
 */

public interface VoiceView {
    /**
     * 刷新聊天列表页面
     * @param chatMessageList
     */
    void refreshVoiceList(List<ChatMessage> chatMessageList);
    /**
     * 录音时设置提示音量大小的图片
     */
    void showRecordVolume(MediaRecorder mediaRecorder);

    /**
     * 显示上滑取消发送的提示图片
     */
    void showSlippingUpCancelPic();
    /**
     * 显示上松手消发送的提示图片
     */
    void showFingerUpCancelPic();

    /**
     * 发送完语音页面恢复初始值
     */
    void restoreVoicePage();

    /**
     * 显示没有更多数据提示
     */
    void showNoMoreDataTip();

    /**
     * 显示计时
     * @param time
     */
    void showTiming(long time);

    void stopTiming();
}
