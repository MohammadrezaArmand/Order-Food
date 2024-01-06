package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBPayment extends SQLiteOpenHelper {


    public static final String DBNAME="payment.db";
    public static final String TABLENAME4="cours";
    public static final int VER=1;

    public DBPayment(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME4+"(id integer primary key,username text,allprice text,delivery text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query="drop table if exists "+TABLENAME4+"";
        db.execSQL(query);

    }
}
