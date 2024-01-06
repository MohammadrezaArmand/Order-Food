package com.armand.foodorder;

import static com.armand.foodorder.DBPayment.TABLENAME4;
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

public class ZActivityListGain extends AppCompatActivity {

    DBPayment dbPayment;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    ZAdapterListGain adpterlistgain;

    TextView txttotalgain;

    int total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_listngain);


        rv=(RecyclerView) findViewById(R.id.rvorder);
        dbPayment=new DBPayment(this);

        list();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        ImageView imgbackfood=(ImageView) findViewById(R.id.imgbackorder);
        txttotalgain=(TextView) findViewById(R.id.txttotalgain);

        txttotalgain.setText(""+total);

        imgbackfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityListGain.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityListGain.this.finish();
            }
        });



    }


    private void list() {



        sqLiteDatabase =dbPayment.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME4,null);
        ArrayList<ModelPayment> models23=new ArrayList<>();

        String allprice="0";
        int asd=0;
        total=asd+total;

        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String username=cursor.getString(1);
             allprice=cursor.getString(2);
            String delivery=cursor.getString(3);

            asd=Integer.parseInt(allprice);
            total=asd+total;
            Log.i("LOG","asd="+asd);
            models23.add(new ModelPayment(id,username,allprice,delivery));



        }



       Log.i("LOG","tt="+total);


        cursor.close();
        adpterlistgain=new ZAdapterListGain(this,R.layout.zadapter_listsubject,models23,sqLiteDatabase);
        rv.setAdapter(adpterlistgain);

    }


}
