package com.armand.foodorder;

import static com.armand.foodorder.DBmain.TABLENAME1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityFood extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    AdapterShow adaptershow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        rv=(RecyclerView) findViewById(R.id.rvorder);
        dBmain=new DBmain(this);

        list();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        ImageView imgbackfood=(ImageView) findViewById(R.id.imgbackorder);


        imgbackfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ActivityFood.this, ActivityMain.class);
                startActivity(iinent);
                ActivityFood.this.finish();
            }
        });

    }

    private void list() {



        sqLiteDatabase =dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME1+" where code=1",null);
        ArrayList<Model> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[] avatar=cursor.getBlob(1);
            String name=cursor.getString(2);
            String price=cursor.getString(3);
            String code=cursor.getString(4);
            String calorii=cursor.getString(5);

            models2.add(new Model(id,avatar,name,price,code,calorii));



        }

        cursor.close();
        adaptershow=new AdapterShow(this,R.layout.adapter_show,models2,sqLiteDatabase);
        rv.setAdapter(adaptershow);

    }


}