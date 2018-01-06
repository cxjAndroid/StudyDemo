package com.example.jonchen.presenter;

import android.media.MediaPlayer;
import android.widget.ImageView;

import com.example.jonchen.R;
import com.example.jonchen.base.Constants;
import com.example.jonchen.model.entity.ChatMessage;
import com.example.jonchen.mvpview.VoiceAdapterView;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by chenxujun on 16-10-25.
 */

public class VoiceAdapterPresenter extends BasePresenter<VoiceAdapterView> {

    private MediaPlayer mediaPlayer;

    public VoiceAdapterPresenter(VoiceAdapterView mView) {
        super(mView);
    }


    public  void playRecord(final ChatMessage message, final ImageView image_voice) {
        getView().setMediaAnimationDrawableDirection(message,image_voice);
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
}
