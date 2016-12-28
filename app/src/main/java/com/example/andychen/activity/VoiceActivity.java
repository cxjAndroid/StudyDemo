package com.example.andychen.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.andychen.adapter.VoiceAdapter;
import com.example.andychen.base.BaseActivity;
import com.example.andychen.base.Constants;
import com.example.andychen.database.ChatMessageDAO;
import com.example.andychen.event.EventMessage;
import com.example.andychen.model.ChatMessage;
import com.example.andychen.model.WatchInfo;
import com.example.andychen.myapplication.R;
import com.example.andychen.utils.LogUtils;
import com.example.andychen.utils.StringUtils;
import com.example.andychen.utils.TimeUtils;
import com.example.andychen.utils.ToastUtils;
import com.example.andychen.view.MyListView;
import com.example.andychen.view.MyRecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.example.andychen.myapplication.R.id.chatList;

/**
 * Created by chenxujun on 16-12-22.
 */


public class VoiceActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnVoice)
    ImageButton btnVoice;
    @BindView(R.id.chatList)
    MyListView chatList;

    public static String VOICE_PATH = Environment.getExternalStorageDirectory() + "/cxj/voice/";
    private File voicePath;
    private File audioRecFile;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private long recordStartTime;
    private ChatMessageDAO chatMessageDAO;
    private ChatMessage chatMessage;
    private List<ChatMessage> messageList;
    private VoiceAdapter voiceAdapter;
    private boolean initRecordComplete;
    private boolean isStop;
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
        createDir(new ChatMessage());
        registerEventBus();
        chatMessageDAO = ChatMessageDAO.getInstance(this);
        getChatMsgFromDatabase();

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
                        stopRecording(true);
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


    private void createDir(ChatMessage chatMessage) {
       /* VOICE_PATH += "/" + chatMessage.getUserId() + "/" + chatMessage.getWatchId();
        voicePath = new File(VOICE_PATH);
        if (!voicePath.exists()) {
            voicePath.mkdirs();
        }*/
        voicePath = new File(VOICE_PATH);
        if (!voicePath.exists()) {
            voicePath.mkdirs();
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage<ChatMessage> eventMessage) {
        chatMessage = eventMessage.getMessage();
    }


    public void startRecording() {
        ToastUtils.show("start");
        LogUtils.e("start");
        audioRecFile = null;

        if(mediaRecorder!=null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }

     /*   mCountDownTimer.start();
        mView.showSlippingUpCancelPic();
        mView.stopTiming();*/
        recordStartTime = System.currentTimeMillis();

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    initRecordComplete = false;
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
                    LogUtils.e("prepare");
                    mediaRecorder.start();
                    LogUtils.e("mediaRecorder.start()");
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                initRecordComplete = true;
                LogUtils.e("initRecordComplete");
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }

    public void stopRecording(boolean isSend) {
        ToastUtils.show("stop");
        LogUtils.e("stop");
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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                }
            }
            /* 然后再进行reset、release */
            if (mediaRecorder != null&&initRecordComplete) {
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
                LogUtils.e("release");
            }
        }
        if (isSend) {
            ChatMessage message = insertDatabase(recordLength);
            messageList.add(message);
            refreshVoiceList(messageList);
            if (recordLength > 0) {   //判断录音时间是否太短
                if (audioRecFile != null && !StringUtils.isEmpty(audioRecFile.getPath())) {   //判断是否录音失败
                    //sendMessage(message);
                } else {
                    ToastUtils.show(R.string.is_record_permission);
                }
            } else {  //时间太短
                ToastUtils.show(R.string.record_too_short);
            }
        } else {
            ToastUtils.show("录音已取消");
        }
    }

    public List<ChatMessage> getChatMsgFromDatabase() {
        messageList = chatMessageDAO.queryChatMessage(chatMessage.getUserId(),
                chatMessage.getWatchId(), Constants.VOICE_PAGE_SIZE);
        refreshVoiceList(messageList);
        return messageList;
    }


    public void refreshVoiceList(List<ChatMessage> chatMessageList) {
        if (voiceAdapter != null) {
            voiceAdapter.notifyDataSetChanged();
           /* if (!isLoadMore) {
                isLoadMore = false;
                chatList.setSelection(voiceAdapter.getCount() - 1);
            } else {
                isLoadMore = false;
                chatList.setSelection(chatMessageList.size() - lastMsgListSize);
            }*/
        } else {
            voiceAdapter = new VoiceAdapter(chatMessageList, chatMessage);
            chatList.setAdapter(voiceAdapter);
            if (voiceAdapter.getCount() > 1) {
                /*if (!isLoadMore) {
                    isLoadMore = false;
                    chatList.setSelection(voiceAdapter.getCount() - 1);
                }*/
            }
        }
        //lastMsgListSize = chatMessageList.size();
    }

    /*private void sendMessage(final ChatMessage message) {

        UploadMessage uploadMessage = new UploadMessage();
        uploadMessage.setAccount(message.getUserId());
        uploadMessage.setImei(message.getWatchId());
        uploadMessage.setTimeStamp(TimeUtils.getTimeLong(message.getTimeDateStr()));
        uploadMessage.setRecord(FileUtils.encodeBase64File(message.getVoiceDataLocalPath()));
        uploadMessage.setDuration(message.getVoiceDuration());

        RetrofitMethods.originRequest(RetrofitMethods.getApiService().rxUploadVoiceMsg(uploadMessage),
                new CustomObserver<ResponseBody>() {
                    @Override
                    public void doOnNext(ResponseBody responseBody) {
                        try {
                            String res = responseBody.string();
                            JSONObject jsonObject = new JSONObject(res);
                            int errorCode = jsonObject.optInt("errorCode");
                            if (errorCode == 0) {
                                messageList.add(message);
                                mView.refreshVoiceList(messageList);
                            } else {
                                showSendFailedMsg(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showSendFailedMsg(message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showSendFailedMsg(message);
                    }
                }
        );
    }*/
    @NonNull
    private ChatMessage insertDatabase(int recordLength) {
        ChatMessage message = new ChatMessage();
        String date = TimeUtils.getDate(0);
        message.setTimeDateStr(date);
        message.setTimeStamp(TimeUtils.getTimeLong(date));
        message.setUserId(chatMessage.getUserId());
        message.setWatchId(chatMessage.getWatchId());
        message.setVoiceDataUrl("");
        message.setVoiceDataLocalPath(audioRecFile.getPath());
        message.setVoiceDuration(recordLength + 1);
        message.setContentType(Constants.ContentType.VOICE);
        message.setLayoutType(Constants.LayoutType.RIGHT);
        message.setShowTimeFlag(chatMessage.getShowTimeFlag());
        message.setHeadPicUrl("");
        return chatMessageDAO.addMessage(message);
    }



}
