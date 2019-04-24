package com.example.zzasapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDAOImpl {
    private zzasSQList dbOpenHandler;

    public SQLiteDAOImpl(Context context) {
        this.dbOpenHandler = new zzasSQList(context, "dbtest.db", null, 1);
    }
    // 插入记录
    public void save(TUsers tusers) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();// 取得数据库操作
        db.execSQL("insert into t_users (username,pass) values(?,?)", new Object[] { tusers.getUsername(), tusers.getPass() });
        db.close();// 记得关闭数据库操作
    }
    // 删除纪录
    public void delete(Integer id) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from t_users where id=?", new Object[] { id.toString() });
        db.close();
    }
    // 修改纪录
    public void update(TUsers tusers) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("update t_users set username=?,pass=? where" + " id=?", new Object[] { tusers.getUsername(), tusers.getPass(), tusers.getId() });
        db.close();
    }
    // 根据ID查找纪录
    public TUsers find(Integer id) {
        TUsers tusers = null;
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        // 用游标Cursor接收从数据库检索到的数据
        Cursor cursor = db.rawQuery("select * from t_users where id=?", new String[] { id.toString() });
        if (cursor.moveToFirst()) {// 依次取出数据
            tusers = new TUsers();
            tusers.setId(cursor.getInt(cursor.getColumnIndex("id")));
            tusers.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            tusers.setPass(cursor.getString(cursor.getColumnIndex("pass")));
        }
        db.close();
        return tusers;
    }
    // 查询所有记录
    public List<TUsers> findAll() {
        List<TUsers> lists = new ArrayList<TUsers>();
        TUsers tusers = null;
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        // Cursor cursor=db.rawQuery("select * from t_users limit ?,?", new
        // String[]{offset.toString(),maxLength.toString()});
        // //这里支持类型MYSQL的limit分页操作

        Cursor cursor = db.rawQuery("select * from t_users ", null);
        while (cursor.moveToNext()) {
            tusers = new TUsers();
            tusers.setId(cursor.getInt(cursor.getColumnIndex("id")));
            tusers.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            tusers.setPass(cursor.getString(cursor.getColumnIndex("pass")));
            lists.add(tusers);
        }
        db.close();
        return lists;
    }
    //统计所有记录数
    public long getCount() {
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from t_users ", null);
        cursor.moveToFirst();
        db.close();
        return cursor.getLong(0);
    }
}