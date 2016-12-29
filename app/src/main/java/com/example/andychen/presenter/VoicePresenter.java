package com.example.andychen.presenter;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.example.andychen.base.Constants;
import com.example.andychen.database.ChatMessageDAO;
import com.example.andychen.model.ChatMessage;
import com.example.andychen.model.ServerChatMessage;
import com.example.andychen.model.UploadMessage;
import com.example.andychen.myapplication.R;
import com.example.andychen.retrofit.CustomObserver;
import com.example.andychen.retrofit.RetrofitMethods;
import com.example.andychen.utils.CacheUtils;
import com.example.andychen.utils.FileUtils;
import com.example.andychen.utils.LogUtils;
import com.example.andychen.utils.StringUtils;
import com.example.andychen.utils.TimeUtils;
import com.example.andychen.utils.ToastUtils;
import com.example.andychen.view.VoiceView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxujun on 16-10-25.
 */

public class VoicePresenter extends BasePresenter<VoiceView> {

    private ChatMessageDAO chatMessageDAO;
    private ChatMessage chatMessage;
    public String VOICE_PATH = Environment.getExternalStorageDirectory() + "/_KM";
    private File audioRecFile;
    private MediaRecorder mediaRecorder;
    private long recordStartTime;
    private List<ChatMessage> messageList;
    public static final int DOWNLOAD_SUCCESS = 1;
    /**
     * 录音时用来计时
     */
    private CountDownTimer mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            mView.showTiming(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            stopRecording(true);
            mView.stopTiming();
        }
    };
    private ArrayList<ChatMessage> downLoadList;
    private int size;
    private final MyHandler myHandler;
    private boolean initRecordComplete;
    private File voicePath;


    public VoicePresenter(VoiceView mView, Context mContext, ChatMessage chatMessage) {
        super(mView, mContext);
        this.chatMessage = chatMessage;
        createDir(chatMessage);
        chatMessageDAO = ChatMessageDAO.getInstance();
        myHandler = new MyHandler(new WeakReference<>(this));
    }

    private void createDir(ChatMessage chatMessage) {
        VOICE_PATH += "/" + chatMessage.getUserId() + "/" + chatMessage.getWatchId();
        voicePath = new File(VOICE_PATH);
        if (!voicePath.exists()) {
            voicePath.mkdirs();
        }
    }

    public List<ChatMessage> getChatMsgFromDatabase() {
        messageList = chatMessageDAO.queryChatMessage(chatMessage.getUserId(),
                chatMessage.getWatchId(), Constants.VOICE_PAGE_SIZE);
        mView.refreshVoiceList(messageList);
        return messageList;
    }

    public List<ChatMessage> loadMoreMsgFromDatabase(List<ChatMessage> chatMessageList) {
        List<ChatMessage> moreMsgList = chatMessageDAO.queryChatMessage(chatMessage.getUserId(),
                chatMessage.getWatchId(), Constants.VOICE_PAGE_SIZE
                , chatMessageList.size());
        if (moreMsgList.size() == 0)
            mView.showNoMoreDataTip();
        messageList.addAll(0, moreMsgList);
        mView.refreshVoiceList(messageList);
        return messageList;
    }

    public void startRecording() {
        audioRecFile = null;
        /*第一次录取语音时可能被安全卫士或部分手机定制系统弹出是否给予权限弹框，导致mediaRecorder释放失败，
         所以在发送语音前先检查。* */
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        mCountDownTimer.start();
        mView.showSlippingUpCancelPic();
        mView.stopTiming();
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
                    mediaRecorder.start();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                initRecordComplete = true;
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }

    public void recordComplete(boolean isFingerMove) {
        if (!initRecordComplete) {
            stopRecording(false);
        } else {
            stopRecording(!isFingerMove);
        }
    }

    public void stopRecording(boolean isSend) {
        int recordLength = (int) ((System.currentTimeMillis() - recordStartTime) / 1000);   /*计算出录音时长*/
        mCountDownTimer.cancel();
        mView.restoreVoicePage();
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
            if (mediaRecorder != null && initRecordComplete) {
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
            }
        }
        if (isSend) {
            ChatMessage message = insertDatabase(recordLength);

            if (recordLength > 0) {   /*判断录音时间是否太短*/
                if (audioRecFile != null && !StringUtils.isEmpty(audioRecFile.getPath())) {   /*判断是否录音失败*/
                    sendMessage(message);
                } else {
                    ToastUtils.show(R.string.is_record_permission);
                }
            } else {  /*时间太短*/
                ToastUtils.show(R.string.record_too_short);
            }
        } else {
            ToastUtils.show("录音已取消");
        }
    }


    public void getVoiceList() {
        String key = chatMessage.getWatchId() + CacheUtils.DOWNLOAD_TIMESTAMP;
        RetrofitMethods.commonRequest(getApiService().getVoiceList(chatMessage.getUserId(),
                chatMessage.getWatchId(), CacheUtils.getLong(key, 0)),
                new CustomObserver<List<ServerChatMessage>>() {
                    @Override
                    public void doOnNext(List<ServerChatMessage> serverChatMessages) {
                        downLoadList = new ArrayList<>();
                        if (serverChatMessages.size() > 0)
                            downloadVoiceMsg(serverChatMessages);
                    }
                });
    }

    private static class MyHandler extends Handler {
        private WeakReference<VoicePresenter> weakReference;

        MyHandler(WeakReference<VoicePresenter> weakReference) {
            super();
            this.weakReference = weakReference;
        }

        Comparator comparator = new Comparator<ChatMessage>() {
            @Override
            public int compare(ChatMessage msg1, ChatMessage msg2) {
                if (msg2.getTimeStamp() - msg1.getTimeStamp() > 0) {
                    return -1;
                }
                return 1;
            }
        };

        @Override
        public void handleMessage(Message msg) {
            VoicePresenter voicePresenter = weakReference.get();
            if (msg.what == DOWNLOAD_SUCCESS) {
                ChatMessage chatMessage = (ChatMessage) msg.obj;
                voicePresenter.downLoadList.add(chatMessage);
                Collections.sort(voicePresenter.downLoadList, comparator);
            }
            if (msg.arg1 == voicePresenter.downLoadList.size()) {
                long downLoadTimeStamp = voicePresenter.downLoadList.get(voicePresenter.downLoadList.size() - 1).getTimeStamp();
                String key = voicePresenter.chatMessage.getWatchId() + CacheUtils.DOWNLOAD_TIMESTAMP;
                CacheUtils.putLong(key, downLoadTimeStamp);
                for (ChatMessage chatMessage : voicePresenter.downLoadList) {
                    voicePresenter.chatMessageDAO.addMessage(chatMessage);
                    voicePresenter.messageList.add(chatMessage);
                }
                if (null != voicePresenter.mView)
                    voicePresenter.mView.refreshVoiceList(voicePresenter.messageList);
            }
        }
    }


    private void downloadVoiceMsg(final List<ServerChatMessage> serverChatMessages) {

        size = serverChatMessages.size();
        for (int i = 0; i < size; i++) {
            final ChatMessage chatMessage = new ChatMessage();
            chatMessage.setVoiceDuration(serverChatMessages.get(i).getDuration());
            long timeStamp = serverChatMessages.get(i).getTimen();
            chatMessage.setTimeStamp(timeStamp);
            chatMessage.setTimeDateStr(TimeUtils.getLongDate(timeStamp));
            //chatMessage.setUserId(App.sharedUtility.getAccount());
            chatMessage.setWatchId(this.chatMessage.getWatchId());
            chatMessage.setVoiceDataUrl(serverChatMessages.get(i).getUrl());
            chatMessage.setContentType(Constants.ContentType.VOICE);
            chatMessage.setLayoutType(Constants.LayoutType.LEFT);

            RetrofitMethods.download(getApiService().update(chatMessage.getVoiceDataUrl()),
                    new CustomObserver<ResponseBody>() {
                        private InputStream inputStream;
                        private FileOutputStream fileOutputStream;

                        @Override
                        public void doOnNext(ResponseBody responseBody) {
                            String saveFile = VOICE_PATH + "/" + chatMessage.getTimeDateStr() + "download.amr";
                            try {
                                long total = responseBody.contentLength();
                                long sum = 0;

                                inputStream = responseBody.byteStream();
                                fileOutputStream = new FileOutputStream(saveFile);

                                byte[] buf = new byte[1024];
                                int len;

                                long firstTime = System.currentTimeMillis();

                                while ((len = inputStream.read(buf)) != -1) {
                                    sum = sum + len;
                                    int percent = (int) (((float) sum / total) * 100);
                                    LogUtils.e(String.valueOf(percent));
                                    fileOutputStream.write(buf, 0, len);
                                }

                                chatMessage.setVoiceDataLocalPath(saveFile);
                                Message msg = Message.obtain();
                                msg.what = DOWNLOAD_SUCCESS;
                                msg.obj = chatMessage;
                                msg.arg1 = size;
                                myHandler.sendMessage(msg);

                                long secondTime = System.currentTimeMillis();
                                long duration = secondTime - firstTime;
                                LogUtils.e("waste:---------" + String.valueOf(duration));

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                        }
                    });
        }


    }


    private void sendMessage(final ChatMessage message) {

        UploadMessage uploadMessage = new UploadMessage();
        uploadMessage.setAccount(message.getUserId());
        uploadMessage.setImei(message.getWatchId());
        uploadMessage.setTimeStamp(TimeUtils.getTimeLong(message.getTimeDateStr()));
        uploadMessage.setRecord(FileUtils.encodeBase64File(message.getVoiceDataLocalPath()));
        uploadMessage.setDuration(message.getVoiceDuration());

        RetrofitMethods.originRequest(getApiService().rxUploadVoiceMsg(uploadMessage),
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
    }

    private void showSendFailedMsg(ChatMessage message) {
        ChatMessage changeStateMsg = chatMessageDAO.changeUploadState(message);
        messageList.add(changeStateMsg);
        mView.refreshVoiceList(messageList);
    }

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
