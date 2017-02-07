package com.example.jonchen.model.entity;

/**
 * Created by chenxujun on 16-10-23.
 */

public class ChatMessage {

    private String id;
    /**时间戳  */
    private String timeDateStr = "" ;

    /**语音文件网络地址 */
    private String voiceDataUrl = "" ;

    /**语音文件本地地址*/
    private String voiceDataLocalPath = "" ;

    /**语音时长  */
    private int voiceDuration = 0 ;

    private int contentType;

    private int layoutType;

    private String headPicUrl;
    //手表imei号
    private String watchId;

    private String userId;

    private boolean isPlaying;

    private int showTimeFlag;
    /** 语音上传状态 0成功 1失败*/
    private int uploadState = 0;

    private long timeStamp;

    public String watchName;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getUploadState() {
        return uploadState;
    }

    public void setUploadState(int uploadState) {
        this.uploadState = uploadState;
    }

    public int getShowTimeFlag() {
        return showTimeFlag;
    }

    public void setShowTimeFlag(int showTimeFlag) {
        this.showTimeFlag = showTimeFlag;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeDateStr() {
        return timeDateStr;
    }

    public void setTimeDateStr(String timeDateStr) {
        this.timeDateStr = timeDateStr;
    }

    public String getVoiceDataUrl() {
        return voiceDataUrl;
    }

    public void setVoiceDataUrl(String voiceDataUrl) {
        this.voiceDataUrl = voiceDataUrl;
    }

    public String getVoiceDataLocalPath() {
        return voiceDataLocalPath;
    }

    public void setVoiceDataLocalPath(String voiceDataLocalPath) {
        this.voiceDataLocalPath = voiceDataLocalPath;
    }

    public int getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(int voiceDuration) {
        this.voiceDuration = voiceDuration;
    }

    public String getWatchName() {
        return watchName;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public ChatMessage(String id, String timeDateStr, long timeStamp, String voiceDataUrl,
                       String voiceDataLocalPath, int voiceDuration, int contentType,
                       int layoutType, String headPicUrl, String watchId, String userId,
                       int showTimeFlag, int uploadState) {
        this.id = id;
        this.timeDateStr = timeDateStr;
        this.timeStamp = timeStamp;
        this.voiceDataUrl = voiceDataUrl;
        this.voiceDataLocalPath = voiceDataLocalPath;
        this.voiceDuration = voiceDuration;
        this.contentType = contentType;
        this.layoutType = layoutType;
        this.headPicUrl = headPicUrl;
        this.watchId = watchId;
        this.userId = userId;
        this.showTimeFlag = showTimeFlag;
        this.uploadState = uploadState;
    }

    public ChatMessage() {
    }


}
