package com.example.andychen.model;

/**
 * Created by chenxujun on 16-10-23.
 */

public class ChatMessage {

    private String id;
    /**时间戳  */
    private String timestamp = "" ;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
}
