package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOrder extends SQLiteOpenHelper {

    public static final String DBNAME="order.db";
    public static final String TABLENAME2="cours";
    public static final int VER=1;

    public DBOrder(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME2+"(id integer primary key,avatar blob,name text,price text,code text,calori text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="drop table if exists "+TABLENAME2+"";
        db.execSQL(query);

    }
}
