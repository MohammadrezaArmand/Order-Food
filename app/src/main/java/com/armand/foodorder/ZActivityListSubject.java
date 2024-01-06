package com.armand.foodorder;

import static com.armand.foodorder.DBSneg.TABLENAME5;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZActivityListSubject extends AppCompatActivity {

    DBSneg dbSneg;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    ZAdapterListSubject adpterlistsubject;

    TextView txttotalsubject;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_listnsubject);


        rv=(RecyclerView) findViewById(R.id.rvorder);
        dbSneg=new DBSneg(this);

        list();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        txttotalsubject=(TextView)findViewById(R.id.txttotalsubject);
        ImageView imgbackfood=(ImageView) findViewById(R.id.imgbackorder);
        Button btnaddsubject1=(Button) findViewById(R.id.btnaddsubject1);


        txttotalsubject.setText(""+total);

        btnaddsubject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityListSubject.this, ZActivityAddNeg.class);
                startActivity(iinent);
                ZActivityListSubject.this.finish();
            }
        });


        imgbackfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityListSubject.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityListSubject.this.finish();
            }
        });



    }


    private void list() {



        sqLiteDatabase =dbSneg.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME5,null);
        ArrayList<ModelNeg> models23=new ArrayList<>();

        String priceneg="0";
        int asd=0;
        total=asd+total;
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String subject=cursor.getString(1);
             priceneg=cursor.getString(2);
            String date=cursor.getString(3);

            asd=Integer.parseInt(priceneg);
            total=asd+total;
            Log.i("LOG","asd="+asd);

            models23.add(new ModelNeg(id,subject,priceneg,date));



        }
        Log.i("LOG","tt="+total);
        cursor.close();
        adpterlistsubject=new ZAdapterListSubject(this,R.layout.zadapter_listsubject,models23,sqLiteDatabase);
        rv.setAdapter(adpterlistsubject);

    }


}
