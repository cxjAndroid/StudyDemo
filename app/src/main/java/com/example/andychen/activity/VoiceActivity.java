package com.example.andychen.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.andychen.base.BaseActivity;
import com.example.andychen.myapplication.R;
import com.example.andychen.utils.LogUtils;

import java.io.File;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxujun on 16-12-22.
 */


public class VoiceActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnVoice)
    ImageButton btnVoice;

    public static final String VOICE_PATH = Environment.getExternalStorageDirectory() + "/cxj/voice/";
    private File voicePath;
    private File audioRecFile;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private long recordStartTime;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_voice;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
    }

    @Override
    public void initDate() {
        btnVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startRecording();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        //mPresenter.onActionUpOrCancel(isFingerMove);
                        //mPresenter.stopRecording(!isFingerMove);

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getY();
                       /* if (moveY - recordOriginalY < -50) {
                            isFingerMove = true;
                            showFingerUpCancelPic();
                        } else {
                            isFingerMove = false;
                            showSlippingUpCancelPic();
                        }*/
                }
                return false;
            }
        });
    }


    public void stopRecording(boolean isSend) {
        int recordLength = (int) ((System.currentTimeMillis() - recordStartTime) / 1000);   /*计算出录音时长*/
      /*  mCountDownTimer.cancel();
        mView.restoreVoicePage();*/
        if (audioRecFile != null && mediaRecorder != null) {
            try {
                mediaRecorder.setOnErrorListener(null);
                mediaRecorder.setOnInfoListener(null);
                /* 停止*/
                mediaRecorder.stop();
            } catch (RuntimeException e) {
                    /* 如果发生异常，很可能是在不合适的状态执行了stop操作*/
                    /* 所以等待一会儿*/
                if (mediaRecorder != null) {
                    mediaRecorder.reset();
                    mediaRecorder.release();
                    mediaRecorder = null;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                }
            }
            /* 然后再进行reset、release */
            if (mediaRecorder != null) {
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
            }
        }
       /* if (isSend) {
            ChatMessage message = insertDatabase(recordLength);
            if (recordLength > 0) {   *//*判断录音时间是否太短*//*
                if (audioRecFile != null && !StringUtils.isEmpty(audioRecFile.getPath())) {   *//*判断是否录音失败*//*
                    sendMessage(message);
                } else {
                    ToastUtils.show(R.string.is_record_permission);
                }
            } else {  *//*时间太短*//*
                ToastUtils.show(R.string.record_too_short);
            }
        } else {
            ToastUtils.show("录音已取消");
        }*/
    }



    public void startRecording() {
        audioRecFile = null;
        mediaRecorder = null;

     /*   mCountDownTimer.start();
        mView.showSlippingUpCancelPic();
        mView.stopTiming();*/
        recordStartTime = System.currentTimeMillis();

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    //initRecordComplete = false;
                    audioRecFile = File.createTempFile("Record_" + recordStartTime, ".amr", new File(VOICE_PATH));
                    //LogUtils.e(audioRecFile.getPath());
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 采样音频源为麦克风
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);// 输出文件格式
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频编码方式
                    mediaRecorder.setOutputFile(audioRecFile.getAbsolutePath());// 输出文件位置
                    // mediaRecorder.setMaxDuration(60);//设置最大录制时间
                    mediaRecorder.setAudioSamplingRate(8000);    // 采样率
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                //initRecordComplete = true;
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }
}
