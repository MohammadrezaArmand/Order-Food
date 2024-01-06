package com.armand.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DBReservation extends SQLiteOpenHelper {
    public static final String DBRES="res.db";
    public static final String TABLERES="reserve";
    public static final int VER=1;

    public DBReservation(@Nullable Context context) {
        super(context, DBRES, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLERES+"(id integer primary key,year integet,month integer,day integer,hour integer,min integer,number integer,username text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query="drop table if exists "+TABLERES+"";
        db.execSQL(query);

    }
}