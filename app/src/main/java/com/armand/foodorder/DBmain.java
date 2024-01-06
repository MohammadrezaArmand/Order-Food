package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {

    public static final String DBNAME="food.db";
    public static final String TABLENAME1="cours";
    public static final int VER=1;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME1+"(id integer primary key,avatar blob,name text,price text,code text,calori text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query="drop table if exists "+TABLENAME1+"";
        db.execSQL(query);

    }
}
