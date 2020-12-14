package com.wmc.sp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class SQLiteManager {
    private static SQLiteManager sqLiteManager;

    public static SQLiteManager getInstance(Context context){
        if (sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);
            return sqLiteManager;
        }
        return sqLiteManager;
    }

    public static SQLiteManager getInstance(){
        return sqLiteManager;
    }

    private GisimSQLite gisimSQLite;
    private SQLiteDatabase database;

    /**
     * 初始化数据库
     * @param context
     */
    private SQLiteManager(Context context){
        gisimSQLite = new GisimSQLite(context,"gisim");
        database = gisimSQLite.getWritableDatabase();
    }


    /**
     * 保存用户名、密码.
     * @param username
     * @param pwd
     */
    private void setUser(String username,String pwd){
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("pwd",pwd);
        database.insert("user",null,values);
    }


    /**
     * 修改密码
     * @param username
     * @param pwd
     */
    private void updatePwd(String username,String pwd){
        ContentValues values = new ContentValues();
        values.put("pwd",pwd);
        database.update("user",values,"username = ?",new String[]{username});
    }



}
