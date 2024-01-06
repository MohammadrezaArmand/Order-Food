package com.armand.foodorder;

import static com.armand.foodorder.DBSneg.TABLENAME5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


public class ZActivityAddNeg extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    EditText edtsubject;
    EditText edtpriceneg;
    TextView txtaddyear1;
    TextView txtaddmonth1;
    TextView txtaddday1;


    int nyear;
    int nmonth;
    int nday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_addneg);

         edtsubject=(EditText) findViewById(R.id.edtsubject);
         edtpriceneg=(EditText) findViewById(R.id.edtpriceneg);


         Button btnaddsubject=(Button) findViewById(R.id.btnaddsubject);
         Button btnbackadmin1=(Button) findViewById(R.id.btnbackadmin1);
          txtaddyear1=(TextView) findViewById(R.id.txtaddyear1);
          txtaddmonth1=(TextView) findViewById(R.id.txtaddmonth1);
          txtaddday1=(TextView) findViewById(R.id.txtaddday1);


         LinearLayout lydate1=(LinearLayout) findViewById(R.id.lydate1);

        lydate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();




            }
        });



         btnbackadmin1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent iinent= new Intent(ZActivityAddNeg.this, ZActivityAdmin.class);
                 startActivity(iinent);
                 ZActivityAddNeg.this.finish();

             }
         });

       btnaddsubject.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insertdata();

           }
       });




    }

    private void insertdata() {


        String data=nyear+"/"+nmonth+"/"+nday;

        Log.i("LOG","dd"+data);


        DBSneg dbSneg;
        SQLiteDatabase sqLiteDatabase;
        dbSneg=new DBSneg(this);
        ContentValues cv=new ContentValues();

        cv.put("subject",edtsubject.getText().toString());
        cv.put("priceneg",edtpriceneg.getText().toString());
        cv.put("date",data);
        sqLiteDatabase=dbSneg.getWritableDatabase();
        Long rec=sqLiteDatabase.insert(TABLENAME5,null,cv);
        if(rec!=null)
        {
            Toast.makeText(this,"insert sucss",Toast.LENGTH_LONG).show();
            Log.i("LOG","ssss");
            Log.i("LOG","date"+data);


        }



    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(ZActivityAddNeg.this, ""+year+"/"+monthOfYear+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
        nyear=year;
        nmonth=monthOfYear;
        nday=dayOfMonth;
        txtaddyear1.setText(""+nyear);
        txtaddmonth1.setText(""+nmonth);
        txtaddday1.setText(""+nday);

    }

    public  void date(){

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(ZActivityAddNeg.this
                ,now.get(Calendar.YEAR)
                ,now.get(Calendar.MONTH)
                ,now.get(Calendar.DAY_OF_MONTH));
        dpd.setTitle("Date");
        dpd.show(getSupportFragmentManager(),"Date");


    }

}
