package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBPayment.TABLENAME4;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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

public class ZActivityOrderName extends AppCompatActivity {


    DBHesabdari dbHesabdari1;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    ZAdapterOrderName zAdapterOrderName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_ordername);

        rv=(RecyclerView) findViewById(R.id.rvorder);

        dbHesabdari1=new DBHesabdari(this);

        showres();


        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        ImageView imgbackorder=(ImageView) findViewById(R.id.imgbackorder);



        imgbackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityOrderName.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityOrderName.this.finish();
            }
        });

    }

    private void showres() {

        DBHesabdari dbHesabdari1 ;
        dbHesabdari1=new DBHesabdari(this);
        sqLiteDatabase =dbHesabdari1.getReadableDatabase();
        Cursor reserve=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where pay=1",null);
        ArrayList<ModelHesabdari> model121=new ArrayList<>();

        while (reserve.moveToNext()){
            int id=reserve.getInt(0);
            String username=reserve.getString(1);
            String allprice=reserve.getString(2);
             String pay=reserve.getString(3);


            Log.i("LOG","u="+username);
            Log.i("LOG","allpri="+allprice);
            Log.i("LOG","pay="+pay);

            model121.add(new ModelHesabdari(id,username,allprice,pay));
        }
        reserve.close();
        zAdapterOrderName=new ZAdapterOrderName(this,R.layout.adapter_show,model121,sqLiteDatabase);
        rv.setAdapter(zAdapterOrderName);


    }





}