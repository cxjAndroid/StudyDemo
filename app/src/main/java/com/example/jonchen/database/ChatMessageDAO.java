package com.example.jonchen.database;

import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jonchen.base.BaseApplication;
import com.example.jonchen.model.ChatMessage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenxujun on 16-10-24.
 */

public class ChatMessageDAO {

    /**
     * 聊天页面的消息列表时间显示间隔
     */
    public static final int CHAT_MSG_TIME_SHOW_SPACE = 5 * 1000 * 60;
    private ChatMessageDbHelper helper;
    private static ChatMessageDAO chatMessageDAO;
    private long lastMsgTimestamp;

    private ChatMessageDAO() {
        PackageManager packageManager = BaseApplication.getApplication().getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(BaseApplication.getApplication().getPackageName(), 0);
            if (helper == null) {
                helper = new ChatMessageDbHelper(BaseApplication.getApplication(), packInfo.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ChatMessageDAO getInstance() {
        if (chatMessageDAO == null) {
            synchronized (ChatMessageDAO.class) {
                if (chatMessageDAO == null) {
                    chatMessageDAO = new ChatMessageDAO();
                }
            }
        }
        return chatMessageDAO;
    }

   /* public boolean addMessage(String timestamp, String userId, String watchId, String voiceDataUrl, String voiceLocalPath,
                              int voiceDuration, int contentType, int layoutType, String headPicUrl) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("userId", userId);
        values.put("watchId", watchId);
        values.put("voiceDataUrl", voiceDataUrl);
        values.put("voiceLocalPath", voiceLocalPath);
        values.put("voiceDuration", voiceDuration);
        values.put("contentType", contentType);
        values.put("layoutType", layoutType);
        values.put("headPicUrl", headPicUrl);
        long result = database.insert(ChatMessageDbHelper.TABLE_NAME, null, values);
        database.close();
        return result != -1;
    }*/


    /*public ChatMessage deleteMessage(ChatMessage chatMessage){
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "delete form chat_message where "
    }*/


    public ChatMessage addMessage(ChatMessage chatMessage) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "select timestamp from chat_message where userId = ? and watchId = ? and showTimeFlag = 1 order by _id desc limit 0,1";
        Cursor cursor = database.rawQuery(sql, new String[]{chatMessage.getUserId(), chatMessage.getWatchId()});
        while (cursor.moveToNext()) {
            lastMsgTimestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
        }
        ContentValues values = new ContentValues();
        if (Math.abs(chatMessage.getTimeStamp() - lastMsgTimestamp)
                > CHAT_MSG_TIME_SHOW_SPACE) {
            values.put("showTimeFlag", 1);
            chatMessage.setShowTimeFlag(1);
        } else {
            values.put("showTimeFlag", 0);
        }
        values.put("timeDateStr", chatMessage.getTimeDateStr());
        values.put("timestamp", chatMessage.getTimeStamp());
        values.put("userId", chatMessage.getUserId());
        values.put("watchId", chatMessage.getWatchId());
        values.put("voiceDataUrl", chatMessage.getVoiceDataUrl());
        values.put("voiceLocalPath", chatMessage.getVoiceDataLocalPath());
        values.put("voiceDuration", chatMessage.getVoiceDuration());
        values.put("contentType", chatMessage.getContentType());
        values.put("layoutType", chatMessage.getLayoutType());
        values.put("headPicUrl", chatMessage.getHeadPicUrl());
        values.put("uploadState", chatMessage.getUploadState());
        long result = database.insert(ChatMessageDbHelper.TABLE_NAME, null, values);
        cursor.close();
        database.close();
        return chatMessage;
    }

    public ChatMessage changeUploadState(ChatMessage chatMessage) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "update chat_message set uploadState = 1 where timestamp = ?";
        chatMessage.setUploadState(1);
        database.execSQL(sql, new String[]{chatMessage.getTimeDateStr()});
        return chatMessage;
    }

    public ChatMessage changeShowTimeState(ChatMessage chatMessage) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "update chat_message set showTimeFlag = 1 where timestamp = ?";
        chatMessage.setShowTimeFlag(1);
        database.execSQL(sql, new String[]{chatMessage.getTimeDateStr()});
        return chatMessage;
    }


    public List<ChatMessage> queryChatMessage(String userId, String watchId, int count, int offset) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "select * from chat_message where userId = ? and watchId = ? order by _id desc limit ? offset ?";
        List<ChatMessage> messageList = new LinkedList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{userId, watchId, Integer.toString(count), Integer.toString(offset)});
        try {
            database.beginTransaction();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String timeDateStr = cursor.getString(cursor.getColumnIndex("timeDateStr"));
                long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
                userId = cursor.getString(cursor.getColumnIndex("userId"));
                watchId = cursor.getString(cursor.getColumnIndex("watchId"));
                String voiceDataUrl = cursor.getString(cursor.getColumnIndex("voiceDataUrl"));
                String voiceLocalPath = cursor.getString(cursor.getColumnIndex("voiceLocalPath"));
                int voiceDuration = cursor.getInt(cursor.getColumnIndex("voiceDuration"));
                int contentType = cursor.getInt(cursor.getColumnIndex("contentType"));
                int layoutType = cursor.getInt(cursor.getColumnIndex("layoutType"));
                int showTimeFlag = cursor.getInt(cursor.getColumnIndex("showTimeFlag"));
                int uploadState = cursor.getInt(cursor.getColumnIndex("uploadState"));
                String headPicUrl = cursor.getString(cursor.getColumnIndex("headPicUrl"));
                ChatMessage chatMessage = new ChatMessage(String.valueOf(id), timeDateStr, timestamp,
                        voiceDataUrl, voiceLocalPath, voiceDuration, contentType,
                        layoutType, headPicUrl, watchId, userId, showTimeFlag, uploadState);
                messageList.add(0, chatMessage);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        cursor.close();
        database.close();
        return messageList;
    }

    public List<ChatMessage> queryChatMessage(String userId, String watchId, int count) {
        return queryChatMessage(userId, watchId, count, 0);
    }


}
