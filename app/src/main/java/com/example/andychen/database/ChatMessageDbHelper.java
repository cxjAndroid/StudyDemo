package com.example.andychen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andychen.utils.LogUtils;


/**
 * Created by chenxujun on 16-10-23.
 */

public class ChatMessageDbHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称
     */
    public  final static String DATABASE_NAME = "KM_CHAT";
    public  final static String TABLE_NAME = "chat_message";

    public ChatMessageDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ChatMessageDbHelper(Context context, int version) {
        this(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table chat_message(_id integer primary key autoincrement" +
                ",timeDateStr text" +
                ",timestamp long" +
                ",userId text" +
                ",watchId text" +
                ",voiceDataUrl text" +
                ",voiceLocalPath text" +
                ",voiceDuration integer" +
                ",contentType integer" +
                ",layoutType integer" +
                ",showTimeFlag integer" +
                ",uploadState integer" +
                ",headPicUrl text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
