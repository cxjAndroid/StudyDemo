package com.example.jonchen.database;

import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jonchen.base.BaseApplication;
import com.example.jonchen.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/18
 */

public class SearchDao {

    private SearchDbHelper helper;

    public SearchDao() {
        PackageManager packageManager = BaseApplication.getApplication().getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(BaseApplication.getApplication().getPackageName(), 0);
            helper = new SearchDbHelper(BaseApplication.getApplication(), packInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入搜索关键字
     *
     * @param keywords 搜索关键字
     * @return 是否添加成功
     */
    public boolean addKeywords(String keywords) {
        deleteKeywords(keywords);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("keywords", keywords);
        long result = database.insert("search_record", null, values);
        database.close();
        return result != -1;
    }

    /**
     * 删除搜索关键字
     *
     * @param keywords 搜索关键字
     */
    public void deleteKeywords(String keywords) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.execSQL("delete from search_record where keywords = ?", new String[]{keywords});
        database.close();
    }

    /**
     * 查询所有关键字
     *
     * @param count 查询数量
     * @return 关键字list
     */
    public List<String> queryAllKeywords(int count) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "select keywords from search_record order by _id desc limit ?";
        List<String> keywordsList = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{Integer.toString(count)});
        try {
            database.beginTransaction();
            while (cursor.moveToNext()) {
                String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
                if (!StringUtils.isEmpty(keywords) && !keywordsList.contains(keywords)) {
                    keywordsList.add(keywords);
                }
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        cursor.close();
        database.close();
        return keywordsList;
    }

    /**
     * 删除所有关键字
     *
     * @return 关键字list
     */

    public List<String> deleteAllKeywords() {
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "select keywords from search_record";
        Cursor cursor = database.rawQuery(sql, null);
        try {
            database.beginTransaction();
            while (cursor.moveToNext()) {
                String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
                database.execSQL("delete from search_record where keywords = ?", new String[]{keywords});
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        List<String> his = queryAllKeywords(10);
        cursor.close();
        database.close();
        return his;
    }
}
