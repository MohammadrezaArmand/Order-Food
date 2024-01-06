package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBSignUp.TABLENAME9;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZActivityCustomer extends AppCompatActivity {


    DBSignUp dbSignUp;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    ZAdapterCustomer zAdapterCustomer;

    String getmobile;
    String getpass;
    String getname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_customer);

        dbSignUp=new DBSignUp(this);
        rv=(RecyclerView) findViewById(R.id.rvorder);



        showres();


        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        ImageView imgbackorder=(ImageView) findViewById(R.id.imgbackorder);



        imgbackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityCustomer.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityCustomer.this.finish();
            }
        });

    }

    private void showres() {

        sqLiteDatabase =dbSignUp.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME9,null);
        ArrayList<ModelSignUp> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
             getname=cursor.getString(1);
            getmobile=cursor.getString(2);
            getpass=cursor.getString(3);
            Log.i("LOG","m="+getmobile);
            Log.i("LOG","p="+getpass);

            models2.add(new ModelSignUp(id,getname,getmobile,getpass));



        }

        cursor.close();
        zAdapterCustomer=new ZAdapterCustomer(this,R.layout.zadapter_customer,models2,sqLiteDatabase);
        rv.setAdapter(zAdapterCustomer);


    }





}