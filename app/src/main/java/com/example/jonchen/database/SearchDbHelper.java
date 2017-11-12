package com.example.jonchen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public class SearchDbHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称
     */
    private final static String name = "SN_COUPON_SEARCH_RECORD";

    public SearchDbHelper(Context context, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table search_record(_id integer primary key autoincrement,keywords text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
