package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBSneg extends SQLiteOpenHelper {

    public static final String DBNAME="negetive.db";
    public static final String TABLENAME5="cours";
    public static final int VER=1;

    public DBSneg(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME5+"(id integer primary key,subject text,priceneg text,date text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query="drop table if exists "+TABLENAME5+"";
        db.execSQL(query);

    }
}
