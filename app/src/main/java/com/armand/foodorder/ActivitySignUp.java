package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBSignUp.TABLENAME9;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivitySignUp extends AppCompatActivity {




    EditText edtsuname;
    EditText edtsumobile;
    EditText edtsupass;
    EditText edtsupass2;
    DBSignUp dbSignUp;
    SQLiteDatabase sqLiteDatabase;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         edtsuname=(EditText) findViewById(R.id.edtsuname);
         edtsumobile=(EditText) findViewById(R.id.edtsumobile);
         edtsupass=(EditText) findViewById(R.id.edtsupass);
         edtsupass2=(EditText) findViewById(R.id.edtsupass2);
        Button btnsignup=(Button) findViewById(R.id.btnsignup);
        Button btnbacksup=(Button) findViewById(R.id.btnbacksup);

        dbSignUp=new DBSignUp(this);

        btnbacksup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iinent= new Intent(ActivitySignUp.this,ActivityLogin.class);
                startActivity(iinent);
                ActivitySignUp.this.finish();
            }
        });


        final String dsa = ActivityMain.idcus;

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String pass=edtsupass.getText().toString();
               String pass2=edtsupass2.getText().toString();
               if(pass.equals(pass2)){
                   inserts();

                   new AlertDialog.Builder(ActivitySignUp.this)
                           .setTitle("Hello")
                           .setMessage("Your SignUp Is Succ")
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent iinent= new Intent(ActivitySignUp.this,ActivityLogin.class);
                                   startActivity(iinent);
                                   ActivitySignUp.this.finish();
                               }
                           }).show();

               }else {
                   new AlertDialog.Builder(ActivitySignUp.this)
                           .setTitle("Error")
                           .setMessage("PassWord Is Not Match !!!!")
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           }).show();
               }

            }
        });



    }
    private void inserts(){

        DBSignUp dbSignUp;
        dbSignUp=new DBSignUp(this);
        ContentValues cvpay=new ContentValues();
        cvpay.put("name",edtsuname.getText().toString());
        cvpay.put("mobile",edtsumobile.getText().toString());
        cvpay.put("pass",edtsupass.getText().toString());

        sqLiteDatabase=dbSignUp.getWritableDatabase();
        sqLiteDatabase.insert(TABLENAME9,null,cvpay);


        /////////////////////////
        DBHesabdari dbHesabdari;
        dbHesabdari=new DBHesabdari(this);

        ContentValues cvq=new ContentValues();

        cvq.put("username",edtsumobile.getText().toString());
        cvq.put("allprice","0");
        cvq.put("pay","0");
        sqLiteDatabase=dbHesabdari.getWritableDatabase();
        sqLiteDatabase.insert(TABLENAME3,null,cvq);
        Log.i("LOG","a1");
    }

    private void list() {


        sqLiteDatabase =dbSignUp.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME9,null);
        ArrayList<ModelSignUp> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String mobile=cursor.getString(2);
            String pass=cursor.getString(3);
            Log.i("LOG","wqew="+mobile);

            models2.add(new ModelSignUp(id,name,mobile,pass));


        }

        cursor.close();



    }
}