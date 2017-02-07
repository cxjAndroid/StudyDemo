package com.example.jonchen.mvpview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonchen.model.entity.ChatMessage;


/**
 * Created by chenxujun on 16-10-25.
 */

public interface VoiceAdapterView {
    /**
     * 根据当前DocGroupChatMessage的消息类型来确定AnimationDrawable方向
     *
     * @param view 绑定AnimationDrawable的View
     */
    void setMediaAnimationDrawableDirection(ChatMessage chatMessage, ImageView view);

    /**
     * 调整语音item长度
     * @param chatMessage
     * @param text_voice_length
     * @param length
     */
    void adjustVoiceItemLength(ChatMessage chatMessage, TextView text_voice_length, View length);

    /**
     * 设置语音背景图片
     */
    void setVoicePic(ChatMessage chatMessage, ImageView image_voice, ImageView icon_progress_failed, TextView text_name);

}
