package com.armand.foodorder;

import static com.armand.foodorder.DBmain.TABLENAME1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZActivityListFood extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    ZAdapterListFood adaptershow;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_listfood);


        rv=(RecyclerView) findViewById(R.id.rvorder);
        dBmain=new DBmain(this);

        list();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        ImageView imgbackfood=(ImageView) findViewById(R.id.imgbackorder);
        Button btnaddfoodadmin=(Button) findViewById(R.id.btnaddfoodadmin);

        btnaddfoodadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityListFood.this, ActivityAddFood.class);
                startActivity(iinent);
                ZActivityListFood.this.finish();
            }
        });


        imgbackfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityListFood.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityListFood.this.finish();
            }
        });



    }


    private void list() {



        sqLiteDatabase =dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME1,null);
        ArrayList<Model> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[] avatar=cursor.getBlob(1);
            String name=cursor.getString(2);
            String price=cursor.getString(3);
            String code=cursor.getString(4);
            String calorii=cursor.getString(5);

            Log.i("LOG",""+id);
            models2.add(new Model(id,avatar,name,price,code,calorii));



        }

        cursor.close();
        adaptershow=new ZAdapterListFood(this,R.layout.zadapter_listfood,models2,sqLiteDatabase);
        rv.setAdapter(adaptershow);

    }



}
