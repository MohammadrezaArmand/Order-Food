package com.armand.foodorder;

import static com.armand.foodorder.DBReservation.TABLERES;
import static com.armand.foodorder.DBmain.TABLENAME1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ZActivityReservation  extends AppCompatActivity {

    DBReservation dbReservation;
    SQLiteDatabase sqLiteDatabase;


    RecyclerView rvres;
    ZAdapterReservation zAdapterReservation;

    TextView txtrdate,txtrtable,txtrtime,txtrusernam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_reservation);




        Button btnbackadmin=(Button)findViewById(R.id.btnbackadmin);

        rvres=(RecyclerView) findViewById(R.id.rvres);
        dbReservation=new DBReservation(this);

        list();
        rvres.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));




        btnbackadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityReservation.this,ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityReservation.this.finish();
            }
        });



        dbReservation=new DBReservation(this);






    }

    private void list() {



        sqLiteDatabase =dbReservation.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLERES,null);
        ArrayList<ModelReserve> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            int year=cursor.getInt(1);
            int month=cursor.getInt(2);
            int day=cursor.getInt(3);
            int hour=cursor.getInt(4);
            int min=cursor.getInt(5);
            int num=cursor.getInt(6);
            String username=cursor.getString(7);

            models2.add(new ModelReserve(id,year,month,day,hour,min,num,username));



        }

        cursor.close();
        zAdapterReservation=new ZAdapterReservation(this,R.layout.zadapter_reservation,models2,sqLiteDatabase);
        rvres.setAdapter(zAdapterReservation);

    }




    private void cancel() {
        dbReservation=new DBReservation(this);
        sqLiteDatabase =dbReservation.getReadableDatabase();
        long delet =sqLiteDatabase.delete(TABLERES ,"" ,null);
        if(delet!=-1){
            Toast.makeText(ZActivityReservation.this, "deleted", Toast.LENGTH_SHORT).show();

        }

    }

}
