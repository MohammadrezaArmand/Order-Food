package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBReservation.TABLERES;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {



    int fyear=0;
    int fmonth=0;
    int fday=0;
    int fhour=0;
    int fminut=0;
    int numbertable=0;
    ImageView imgtable1,imgtable2,imgtable3,imgtable4,imgtable5,imgtable6,imgtable7;

    DBReservation dbReservation;
    SQLiteDatabase sqLiteDatabase;

    String codem=ActivityLogin.idcus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        ImageView imgback=(ImageView) findViewById(R.id.imgback);
        ImageView imgnotification=(ImageView) findViewById(R.id.imgnotification);
        imgtable2=(ImageView) findViewById(R.id.imgtable2);
        imgtable3=(ImageView) findViewById(R.id.imgtable3);
        imgtable4=(ImageView) findViewById(R.id.imgtable4);
        imgtable5=(ImageView) findViewById(R.id.imgtable5);
        imgtable6=(ImageView) findViewById(R.id.imgtable6);
        imgtable7=(ImageView) findViewById(R.id.imgtable7);
        imgtable1=(ImageView) findViewById(R.id.imgtable1);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityReservation.this,ActivityMain.class);
                startActivity(iinent);
                ActivityReservation.this.finish();
            }
        });

        imgnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityReservation.this,ActivityReservationShow.class);
                startActivity(iinent);
                ActivityReservation.this.finish();


            }

        });


        dbReservation=new DBReservation(this);
        onclick();



    }

    private void onclick() {

        imgtable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=1;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        imgtable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=2;
                            }
                        }).setNegativeButton("No", null).show();



            }
        });
        imgtable3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=3;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        imgtable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=4;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        imgtable5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=5;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        imgtable6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=6;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        imgtable7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ActivityReservation.this)
                        .setTitle("Reservation")
                        .setMessage("Are you sure you want this table?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date();
                                numbertable=7;
                            }
                        }).setNegativeButton("No", null).show();
            }
        });

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(ActivityReservation.this, ""+year+"/"+monthOfYear+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
        fyear=year;
        fmonth=monthOfYear;
        fday=dayOfMonth;
        time();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Toast.makeText(ActivityReservation.this, ""+hourOfDay+":"+minute, Toast.LENGTH_LONG).show();
        fhour=hourOfDay;
        fminut=minute;
        insertdata();
    }

    public  void time(){

        Calendar now =Calendar.getInstance();
        TimePickerDialog tpd=TimePickerDialog.newInstance(ActivityReservation.this
                ,now.get(Calendar.HOUR_OF_DAY)
                ,now.get(Calendar.MINUTE)
                ,true);
        tpd.setTitle("Set Time");
        tpd.show(getSupportFragmentManager(),"Time");

    }

    public  void date(){

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(ActivityReservation.this
                ,now.get(Calendar.YEAR)
                ,now.get(Calendar.MONTH)
                ,now.get(Calendar.DAY_OF_MONTH));
        dpd.setTitle("Reservation");
        dpd.show(getSupportFragmentManager(),"Date");


    }

    private void insertdata() {


        DBReservation dbReservation ;
        dbReservation=new DBReservation(this);
        sqLiteDatabase =dbReservation.getReadableDatabase();
        Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLERES+" where username='"+codem+"'",null);
        ArrayList<ModelReserve> modelReserves=new ArrayList<>();

        int year=0;
        while (reserve1.moveToNext()){
            int id=reserve1.getInt(0);
             year=reserve1.getInt(1);
            int month=reserve1.getInt(2);
            int day=reserve1.getInt(3);
            int hour=reserve1.getInt(4);
            int min=reserve1.getInt(5);
            int num=reserve1.getInt(6);
            String username=reserve1.getString(7);



            modelReserves.add(new ModelReserve(id,year,month,day,hour,min,num,username));
        }
        Log.i("LOG","yaer="+year);

        reserve1.close();


        if(year==0){

            ContentValues cv=new ContentValues();
            cv.put("year",fyear);
            cv.put("month",fmonth);
            cv.put("day",fday);
            cv.put("hour",fhour);
            cv.put("min",fminut);
            cv.put("number",numbertable);
            cv.put("username",codem);
            sqLiteDatabase=dbReservation.getWritableDatabase();
            Long rec=sqLiteDatabase.insert(TABLERES,null,cv);
            if(rec!=null)
            {
                Toast.makeText(ActivityReservation.this,"insert sucss",Toast.LENGTH_LONG).show();
                Log.i("LOG","ssss");

            }

        }else{
            Log.i("LOG","nooooo");

            new AlertDialog.Builder(ActivityReservation.this)
                    .setTitle("Reservation")
                    .setMessage("You have a resevation !!!!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

        }




    }



}