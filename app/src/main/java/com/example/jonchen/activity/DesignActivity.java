package com.example.jonchen.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jonchen.adapter.BottomSheetDialogAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.presenter.DesignPresenter;
import com.example.jonchen.mvpview.DesignView;
import com.example.jonchen.R;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxujun on 16-9-19.
 */
public class DesignActivity extends BaseActivity<DesignPresenter> implements DesignView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    LinearLayout tab_layout;
    @BindView(R.id.btn_bottom_sheet_control)
    Button btn_bottom_sheet_control;
    @BindView(R.id.record)
    Button record;
    @BindView(R.id.stop_record)
    Button stop_record;
    @BindView(R.id.play)
    Button play;

    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior mBehavior;
    public static final String VOICE_PATH = Environment.getExternalStorageDirectory() + "/cxj/voice/";
    private File voicePath;
    private File audioRecFile;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.acitivity_design;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(tab_layout);
        initBottomSheet();
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = new DesignPresenter(this);
    }

    @Override
    public void initDate() {
        mPresenter.getBottomSheetDialogData();
        voicePath = new File(VOICE_PATH);
        if (!voicePath.exists()) {
            voicePath.mkdirs();
        }

    }

    @OnClick({R.id.btn_bottom_sheet_control, R.id.btn_bottom_dialog_control, R.id.record, R.id.stop_record, R.id.play,R.id.voiceBtn})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn_bottom_sheet_control:
                changeBottomSheetStatus();
                break;
            case R.id.btn_bottom_dialog_control:
                showBottomSheetDialog();
                break;
            case R.id.record:
                ToastUtils.show("录音");
                startRecording();
                break;
            case R.id.stop_record:
                ToastUtils.show("stop");
                stopRecording();
                break;
            case R.id.play:
                playRecord();
                break;
            case R.id.voiceBtn:
                //IntentUtils.startActivityLeftIn(this,VoiceActivity.class);
                break;
        }
    }

    @Override
    public void createBottomSheetDialog(List<String> data) {
        bottomSheetDialog = new BottomSheetDialog(this);
        View view = View.inflate(this, R.layout.dialog_bottom_sheet, null);
        RecyclerView recyclerView = ButterKnife.findById(view, R.id.recyclerView);
        bottomSheetDialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(1000);


        BottomSheetDialogAdapter adapter = new BottomSheetDialogAdapter(data, R.layout.item);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showBottomSheetDialog() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetDialog.show();
    }

    @Override
    public void initBottomSheet() {
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void changeBottomSheetStatus() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setHideable(true);
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void startRecording() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    audioRecFile = File.createTempFile("Record_", ".amr", voicePath);
                    LogUtils.e(audioRecFile.getPath());
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 采样音频源为麦克风
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);// 输出文件格式
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频编码方式
                    mediaRecorder.setOutputFile(audioRecFile.getAbsolutePath());// 输出文件位置/storage/emulated/0/cxj/voice/Record_-1469417521.amr
                    audioRecFile.getPath();///storage/emulated/0/cxj/voice/Record_-1469417521.amr
                    // mediaRecorder.setMaxDuration(60);//设置最大录制时间
                    mediaRecorder.setAudioSamplingRate(8000);    // 采样率
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }

    private void stopRecording() {
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
//            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void playRecord() {
        if (audioRecFile != null && audioRecFile.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                FileInputStream fis = new FileInputStream(audioRecFile);
                mediaPlayer.setDataSource(fis.getFD());
                fis.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    public void onCompletion(MediaPlayer arg0) {
                        stopMediaPlayer();
                    }

                });
            } catch (Exception e) {
                LogUtils.e("Voice Playing Error", e);
                ToastUtils.show("语音播放错误");
                stopMediaPlayer();
            }
        }
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
