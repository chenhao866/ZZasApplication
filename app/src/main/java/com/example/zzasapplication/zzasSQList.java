package com.example.zzasapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *context 上下文
 * name 数据库名
 * factory 可选的数据库游标工厂类，当查询(query)被提交时，该对象会被调用来实例化一个游标。默认为null。
 * ersion 数据库版本号
 */
public class zzasSQList extends SQLiteOpenHelper {
    public zzasSQList( Context context,String name,CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // 覆写onCreate方法，当数据库创建时就用SQL命令创建一个表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建一个t_users表，id主键，自动增长，字符类型的username和pass;
        db.execSQL("create table t_users(id integer primary key autoincrement,username varchar(200),pass varchar(200) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
