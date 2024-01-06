package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHesabdari extends SQLiteOpenHelper {

    public static final String DBNAME="hesabdari.db";
    public static final String TABLENAME3="cours";
    public static final int VER=1;

    public DBHesabdari(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME3+"(id integer primary key,username text,allprice text,pay text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="drop table if exists "+TABLENAME3+"";
        db.execSQL(query);
    }
}
