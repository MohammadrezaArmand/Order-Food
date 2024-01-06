package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBSignUp extends SQLiteOpenHelper {

    public static final String DBNAME="signup.db";
    public static final String TABLENAME9="cours";
    public static final int VER=1;

    public DBSignUp(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME9+"(id integer primary key,name text,mobile text,pass text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query="drop table if exists "+TABLENAME9+"";
        db.execSQL(query);

    }
}
