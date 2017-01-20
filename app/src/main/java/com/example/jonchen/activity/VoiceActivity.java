package com.example.jonchen.activity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.adapter.VoiceAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.ChatMessage;
import com.example.jonchen.R;
import com.example.jonchen.presenter.VoicePresenter;
import com.example.jonchen.swipy_refresh_layout.RefreshLayout;
import com.example.jonchen.swipy_refresh_layout.RefreshLayoutDirection;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.VoiceView;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxujun on 16-12-22.
 */


public class VoiceActivity extends BaseActivity<VoicePresenter> implements VoiceView, RefreshLayout.OnRefreshListener {
    @BindView(R.id.btnVoice)
    ImageButton btnVoice;
    @BindView(R.id.chatList)
    ListView chatList;
    @BindView(R.id.icon_record)
    ImageView icon_record;
    @BindView(R.id.text_record_tip)
    TextView text_record_tip;
    @BindView(R.id.icon_record_cancel)
    ImageView icon_record_cancel;
    @BindView(R.id.ll_record)
    RelativeLayout ll_record;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.text_tip)
    TextView text_tip;
    @BindView(R.id.text_click)
    TextView text_click;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ChatMessage chatMessage;
    private VoiceAdapter voiceAdapter;
    private List<ChatMessage> chatMessageList;

    /**
     * 记录录音按下时手指初始y坐标值
     */
    private float recordOriginalY = 0.0f;
    /**
     * 标识录音时手指是否移动过，用于取消录音时的判断
     */
    private boolean isFingerMove = false;
    private boolean isLoadMore;
    private int lastMsgListSize;
    private VoiceListHandler voiceListHandler;
    private VoiceListRunnable voiceListRunnable;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_voice;
    }

    @Override
    protected void initView() {
        registerEventBus();
        initToolBar(toolbar);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage<ChatMessage> eventMessage) {
        chatMessage = eventMessage.getMessage();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initDate();
    }

    @Override
    public void initDate() {

        chatMessageList = mPresenter.getChatMsgFromDatabase();
        //mPresenter.getVoiceList();

        voiceListRunnable = new VoiceListRunnable();
        voiceListHandler = new VoiceListHandler(new WeakReference<>(this));
        voiceListHandler.sendEmptyMessage(0);

        //titleBar.setTextTitle(CacheUtils.getString(chatMessage.getWatchId()+"watchRealName", ""));
        btnVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPresenter.startRecording();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mPresenter.recordComplete(isFingerMove);
                        //mPresenter.stopRecording(!isFingerMove);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getY();
                        if (moveY - recordOriginalY < -50) {
                            isFingerMove = true;
                            showFingerUpCancelPic();
                        } else {
                            isFingerMove = false;
                            showSlippingUpCancelPic();
                        }
                }
                return false;
            }
        });
        initRefreshLayout(refreshLayout).setOnRefreshListener(this);
    }


    static class VoiceListHandler extends Handler {
        private VoiceActivity voiceActivity;

        public VoiceListHandler(WeakReference<VoiceActivity> weakReference) {
            super();
            VoiceActivity voiceActivity = weakReference.get();
            this.voiceActivity = voiceActivity;
        }

        @Override
        public void handleMessage(Message msg) {
            voiceActivity.mPresenter.getVoiceList();
            postDelayed(voiceActivity.voiceListRunnable, 10000);
        }
    }

    class VoiceListRunnable implements Runnable {
        @Override
        public void run() {
            mPresenter.getVoiceList();
            voiceListHandler.postDelayed(this, 10000);
        }
    }


    @Override
    public void onRefresh(RefreshLayoutDirection direction) {
        switch (direction) {
            case TOP:
                //上滑加载更多
                //lastVisiblePosition = chatList.getLastVisiblePosition();
                isLoadMore = true;
                mPresenter.loadMoreMsgFromDatabase(chatMessageList);
                refreshLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        voiceListHandler.removeCallbacks(voiceListRunnable);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = new VoicePresenter(this, chatMessage);
    }

    @Override
    public void refreshVoiceList(List<ChatMessage> chatMessageList) {
        if (voiceAdapter != null) {
            voiceAdapter.notifyDataSetChanged();
            if (!isLoadMore) {
                isLoadMore = false;
                chatList.setSelection(voiceAdapter.getCount() - 1);
            } else {
                isLoadMore = false;
                chatList.setSelection(chatMessageList.size() - lastMsgListSize);
            }
        } else {
            voiceAdapter = new VoiceAdapter(chatMessageList, chatMessage);
            chatList.setAdapter(voiceAdapter);
            if (voiceAdapter.getCount() > 1) {
                if (!isLoadMore) {
                    isLoadMore = false;
                    chatList.setSelection(voiceAdapter.getCount() - 1);
                }
            }
        }
        lastMsgListSize = chatMessageList.size();
    }

    @Override
    public void showSlippingUpCancelPic() {
        text_tip.setText(getString(R.string.slipping_up_cancel));
        text_record_tip.setText(getResources().getString(R.string.slipping_up_cancel));
        ll_record.setVisibility(View.VISIBLE);
        icon_record_cancel.setVisibility(View.GONE);
        icon_record.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFingerUpCancelPic() {
        text_tip.setText(getResources().getString(R.string.finger_up_cancel));
        icon_record_cancel.setVisibility(View.VISIBLE);
        icon_record.setVisibility(View.GONE);
    }

    @Override
    public void restoreVoicePage() {
        text_tip.setText(getString(R.string.voice_talk));
        stopTiming();
        ll_record.setVisibility(View.GONE);
    }

    @Override
    public void showNoMoreDataTip() {
        ToastUtils.show(getString(R.string.no_more_data));
    }


    @Override
    public void showTiming(long time) {
        text_click.setVisibility(View.VISIBLE);
        long l = 60 - time / 1000;
        text_click.setText("0:" + l);
    }

    @Override
    public void stopTiming() {
        text_click.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRecordVolume(MediaRecorder mediaRecorder) {
        if (mediaRecorder == null) return;
        int voice = mediaRecorder.getMaxAmplitude();
        if (voice < 12000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend1);
        } else if (voice >= 12000 && voice < 16000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend2);
        } else if (voice >= 16000 && voice < 20000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend3);
        } else if (voice >= 20000 && voice < 24000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend4);
        } else if (voice >= 24000 && voice < 28000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend5);
        } else if (voice >= 28000) {
            icon_record.setImageResource(R.drawable.msg_bg_voicesend6);
        }
    }

}
