package com.wmc.sp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GisimSQLite extends SQLiteOpenHelper {

    private SQLiteDatabase writableDatabase;
    private ContentValues contentValues=new ContentValues();

    public GisimSQLite(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(uid int,username String,context String,time long,headpath String)");
        writableDatabase = getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入消息
     * @param uid
     * @param username
     * @param context
     * @param time
     * @param headpath
     */

    public void insert(int uid,String username,String context,long time,String headpath){
        contentValues.put("username",username);
        contentValues.put("context",context);
        contentValues.put("time",time);
        contentValues.put("headpath",headpath);
        writableDatabase.insert("user",null,contentValues);
    }

    /**
     * 获取所有
     * @return
     */
    public Cursor getAll(){
        Cursor cursor = writableDatabase.query("user", null, null, null, null, null,"time");
        return cursor;
    }

    public void deleteAll(){
        writableDatabase.delete("user", null, null);
    }

    public void delete(String username,String context){
        writableDatabase.delete("user","username=?,context=?",new String[]{username,context});
    }

    /**
     * 关闭数据库
     */
    public void DatabaseClose(){
        writableDatabase.close();
    }


}
