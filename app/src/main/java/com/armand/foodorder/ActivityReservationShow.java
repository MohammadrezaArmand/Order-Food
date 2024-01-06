package com.armand.foodorder;

import static com.armand.foodorder.DBReservation.TABLERES;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityReservationShow extends AppCompatActivity {

    DBReservation dbReservation;
    SQLiteDatabase sqLiteDatabase;

    TextView txtyear,txtmonth,txtday,txthour,txtmin,txtnumber;
    String codem=ActivityLogin.idcus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationshow);

        txtyear=(TextView)findViewById(R.id.txtyear);
        txtmonth=(TextView)findViewById(R.id.txtmonth);
        txtday=(TextView)findViewById(R.id.txtday);
        txthour=(TextView)findViewById(R.id.txthour);
        txtmin=(TextView)findViewById(R.id.txtmin);
        txtnumber=(TextView)findViewById(R.id.txtnumber);
        Button btnbacknotification=(Button)findViewById(R.id.btnbacknotification);
        Button btncancelres=(Button)findViewById(R.id.btncancelres);

        btnbacknotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityReservationShow.this,ActivityReservation.class);
                startActivity(iinent);
                ActivityReservationShow.this.finish();
            }
        });
        btncancelres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ActivityReservationShow.this)
                        .setTitle("Cancel Reservation")
                        .setMessage("Are you sure to cancel?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancel();
                              //  finish();
                               // startActivity(getIntent());
                            }
                        }).setNegativeButton("No", null).show();





            }
        });




        dbReservation=new DBReservation(this);



        showres();


    }

    private void cancel() {
        dbReservation=new DBReservation(this);
        sqLiteDatabase =dbReservation.getReadableDatabase();
        long delet =sqLiteDatabase.delete(TABLERES ,"username='"+codem+"'" ,null);
        if(delet!=-1){
            Toast.makeText(ActivityReservationShow.this, "deleted", Toast.LENGTH_SHORT).show();

            txtyear.setText("0");
            txtmonth.setText("0");
            txtday.setText("0");
            txthour.setText("0");
            txtmin.setText("0");
            txtnumber.setText("0");



        }

    }

    private void showres() {

        sqLiteDatabase =dbReservation.getReadableDatabase();
        Cursor reserve=sqLiteDatabase.rawQuery("select * from "+TABLERES+" where username='"+codem+"'",null);
        ArrayList<ModelReserve> models2=new ArrayList<>();
        while (reserve.moveToNext()){
            int id=reserve.getInt(0);
            int year=reserve.getInt(1);
            int month=reserve.getInt(2);
            int day=reserve.getInt(3);
            int hour=reserve.getInt(4);
            int min=reserve.getInt(5);
            int num=reserve.getInt(6);
            String username=reserve.getString(7);

            txtyear.setText(""+year);
            txtmonth.setText(""+month);
            txtday.setText(""+day);
            txthour.setText(""+hour);
            txtmin.setText(""+min);
            txtnumber.setText(""+num);
            Log.i("LOG","us:"+username);

            models2.add(new ModelReserve(id,year,month,day,hour,min,num,username));
        }
        reserve.close();
    }


}
