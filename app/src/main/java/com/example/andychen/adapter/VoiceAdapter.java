package com.example.andychen.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andychen.base.BaseApplication;
import com.example.andychen.base.BaseListAdapter;
import com.example.andychen.base.BaseViewHolder;
import com.example.andychen.base.Constants;
import com.example.andychen.database.ChatMessageDAO;
import com.example.andychen.model.ChatMessage;
import com.example.andychen.myapplication.R;
import com.example.andychen.utils.DpUtils;
import com.example.andychen.utils.LogUtils;
import com.example.andychen.utils.TimeUtils;
import com.example.andychen.utils.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by chenxujun on 16-10-24.
 */

public class VoiceAdapter extends BaseListAdapter<ChatMessage> {
    /**
     * 语音型，左边
     */
    private static final int TYPE_VOICE_LEFT = 1;

    /**
     * 语音型，右边
     */
    private static final int TYPE_VOICE_RIGHT = 2;
    //private final String watchRealName;

    private List<ChatMessage> messageList;
    private ChatMessage lastChatMessage;
    private ImageView lastImageVoice;
    //private final VoiceAdapterPresenter mPresenter;
    private final ChatMessageDAO chatMessageDAO;
    //private final String realName;
    private ChatMessage chatMessage;
    private MediaPlayer mediaPlayer;

    public VoiceAdapter(List<ChatMessage> data, ChatMessage chatMessage) {
        super(data, -1);
        this.chatMessage = chatMessage;
        messageList = data;
        //mPresenter = new VoiceAdapterPresenter(this);
        chatMessageDAO = ChatMessageDAO.getInstance(BaseApplication.getApplication());
        /*realName = CacheUtils.getString("realName", "");
        watchRealName = CacheUtils.getString(chatMessage.getWatchId()+"watchRealName", "");*/
    }

    @Override
    /**判断布局类型*/
    public int getItemViewType(int position) {
        ChatMessage chatMessage = messageList.get(position);
        switch (chatMessage.getContentType()) {
            case Constants.ContentType.VOICE:    /*语音*/
                if (chatMessage.getLayoutType() == Constants.LayoutType.LEFT) {      /*接收型*/
                    return TYPE_VOICE_LEFT;
                } else {                                                            /*发送型*/
                    return TYPE_VOICE_RIGHT;
                }
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder;
        int type = getItemViewType(position);
        if (type == TYPE_VOICE_LEFT) {
            baseViewHolder = BaseViewHolder.get(convertView, parent, R.layout.item_chat_voice_left, position);
        } else {
            baseViewHolder = BaseViewHolder.get(convertView, parent, R.layout.item_chat_voice_right, position);
        }
        refreshView(baseViewHolder, getItem(position), position);
        return baseViewHolder.getConvertView();
    }

    @Override
    public void refreshView(BaseViewHolder holder, final ChatMessage chatMessage, final int position) {
        ImageView icon_progress_failed = holder.getView(R.id.icon_progress_failed);
        TextView text_name = holder.getView(R.id.text_name);
        TextView text_voice_length = holder.getView(R.id.text_voice_length);
        View length = holder.getView(R.id.length);
        ImageView image_voice = holder.getView(R.id.icon_voice);
        View rl_voice = holder.getView(R.id.rl_voice);
        TextView text_time = holder.getView(R.id.text_time);
        //TimeUtils.displayTime(chatMessage.getTimeDateStr(), text_time, 0);
        setTime(chatMessage,text_time,position);

        adjustVoiceItemLength(chatMessage, text_voice_length, length);
        setVoicePic(chatMessage, image_voice,icon_progress_failed,text_name);

        rl_voice.setOnClickListener(new VoiceClickListener(chatMessage, image_voice));
    }

    private class VoiceClickListener implements View.OnClickListener {

        private ChatMessage chatMessage;
        private ImageView image_voice;

        public VoiceClickListener(ChatMessage chatMessage, ImageView image_voice) {
            this.chatMessage = chatMessage;
            this.image_voice = image_voice;
        }

        @Override
        public void onClick(View v) {
            if (lastChatMessage != null) {
                //语音在播放时再次点击同一条语音，停止播放
                if (chatMessage.isPlaying() &&
                        lastChatMessage == chatMessage) {
                    stopMediaPlayer(chatMessage, image_voice);
                }//语音在播放时点击另一条语音，正在播放的停止，播放点击的语音条目
                else if (lastChatMessage != chatMessage
                        && lastChatMessage.isPlaying()) {
                    stopMediaPlayer(lastChatMessage, lastImageVoice);
                    playRecord(chatMessage, image_voice);
                } else {
                    playRecord(chatMessage, image_voice);
                }
            }//首次点击，播放语音
            else {
                playRecord(chatMessage, image_voice);
            }
            lastChatMessage = chatMessage;
            lastImageVoice = image_voice;
        }
    }



    public  void playRecord(final ChatMessage message, final ImageView image_voice) {
        setMediaAnimationDrawableDirection(message,image_voice);
        message.setPlaying(true);
        String voiceDataLocalPath = message.getVoiceDataLocalPath();
        File voiceFile = new File(voiceDataLocalPath);
        if (voiceFile != null && voiceFile.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                FileInputStream fis = new FileInputStream(voiceFile);
                mediaPlayer.setDataSource(fis.getFD());
                fis.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer arg0) {
                        stopMediaPlayer(message, image_voice);
                    }
                });
            } catch (Exception e) {
                LogUtils.e("Voice Playing Error", e);
                ToastUtils.show("语音播放错误");
                stopMediaPlayer(message, image_voice);
            }
        }
    }

    public void stopMediaPlayer(ChatMessage message, ImageView image_voice) {
        message.setPlaying(false);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (message.getLayoutType() == Constants.LayoutType.RIGHT) {  /*发送型*/
            image_voice.setImageResource(R.drawable.msg_pic_bluesound3);
        } else {                                                                      /*接收型*/
            image_voice.setImageResource(R.drawable.msg_pic_whitesound3);
        }
    }

    /**
     * 显示时间
     */
    private void setTime(ChatMessage chatMessage, TextView text_time, int pos) {
        //是否显示判断  第一位默认显示，isTimeStampFlag为true也显示
        if (pos == 0 || chatMessage.getShowTimeFlag() == 1) {
            chatMessageDAO.changeShowTimeState(chatMessage);
            /*try{
                String time = timeMap.get(chatMessage.getTimeDateStr().trim());   尝试从缓存拿取处理过的时间
                if(StringUtils.isEmpty(time)){  缓存没有
                    TimeUtils.displayTime(chatMessage.getTimeDateStr().trim(), holder.text_time, 0);
                    timeMap.put(chatMessage.getTimeDateStr().trim(),holder.text_time.getText().toString());
                }else{  缓存中已有处理过的时间 直接显示即可
                    holder.text_time.setText(time);
                }
            }catch (Exception e){
                com.nostra13.universalimageloader.utils.L.e("Chat Show Time Error", "", e);
                holder.text_time.setText(chatMessage.getTimeDateStr().trim());
            }*/
            text_time.setVisibility(View.VISIBLE);
            TimeUtils.displayTime(chatMessage.getTimeDateStr().trim(), text_time, 0);
        } else {
            text_time.setVisibility(View.GONE);
        }
    }


    public void adjustVoiceItemLength(ChatMessage chatMessage, TextView text_voice_length, View length) {
        text_voice_length.setText(chatMessage.getVoiceDuration() + "''");
        length.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = length.getLayoutParams();
        layoutParams.width = DpUtils.dip2px(10) + chatMessage.getVoiceDuration() * 3;
        length.setLayoutParams(layoutParams);
    }

    public void setVoicePic(ChatMessage chatMessage, ImageView image_voice, ImageView icon_progress_failed, TextView text_name) {
        if(chatMessage.getUploadState()==1){
            icon_progress_failed.setVisibility(View.VISIBLE);
        }else{
            icon_progress_failed.setVisibility(View.GONE);
        }

        if (chatMessage.getLayoutType() == Constants.LayoutType.RIGHT) {            // 发送型消息
            image_voice.setImageResource(R.drawable.msg_pic_bluesound3);
            //text_name.setText(realName);
        } else {                                                                    // 接收型消息
            image_voice.setImageResource(R.drawable.msg_pic_whitesound3);
            //text_name.setText(watchRealName);
        }
    }

    public void setMediaAnimationDrawableDirection(ChatMessage chatMessage, ImageView view) {
        if (chatMessage.getLayoutType() == Constants.LayoutType.RIGHT) {
            view.setImageDrawable(BaseApplication.getApplication().getResources().getDrawable(R.drawable.anim_chat_voice_playing_left));
        } else {
            view.setImageDrawable(BaseApplication.getApplication().getResources().getDrawable(R.drawable.anim_chat_voice_playing_right))
            ;
        }
        ((AnimationDrawable) view.getDrawable()).start();
    }
}
