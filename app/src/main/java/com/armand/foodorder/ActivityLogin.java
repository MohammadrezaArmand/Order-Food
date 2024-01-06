package com.armand.foodorder;

import static com.armand.foodorder.DBSignUp.TABLENAME9;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.elasticviews.ElasticButton;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity {

    DBSignUp dbSignUp;
    SQLiteDatabase sqLiteDatabase;



    EditText edtmobilefirst;
    EditText edtpassfirst;

    String getmobile;
    String getpass;
    String edtmobile;
    String edtpass;
    public static String idcus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbSignUp=new DBSignUp(this);
        TextView txtsignup=(TextView) findViewById(R.id.txtsignup);
         edtmobilefirst=(EditText) findViewById(R.id.edtmobilefirst);
         edtpassfirst=(EditText) findViewById(R.id.edtpassfirst);
        ElasticButton btnlogin = (ElasticButton)findViewById(R.id.btnlogin);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("LOG","edt"+edtmobilefirst.getText().toString());
                Log.i("LOG","edt1"+getmobile);
                if(edtmobilefirst.getText().toString().equals("admin")&& edtpassfirst.getText().toString().equals("22")){
                    Intent iinent= new Intent(ActivityLogin.this, ZActivityAdmin.class);
                    startActivity(iinent);
                    ActivityLogin.this.finish();

                }
                else{
                    readcus();
                    if(edtmobile.equals(getmobile) && edtpass.equals(getpass)){
                        idcus=edtmobile;
                        Intent iinent= new Intent(ActivityLogin.this, ActivityMain.class);
                        startActivity(iinent);
                        ActivityLogin.this.finish();

                    }
                    else {
                        Log.i("LOG","r");
                        new AlertDialog.Builder(ActivityLogin.this)
                                .setTitle("Log IN")
                                .setMessage("You Password Is Wong !!!!")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }

                }




            }
        });
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityLogin.this, ActivitySignUp.class);
                startActivity(iinent);
                ActivityLogin.this.finish();


            }
        });


    }

    private void readcus() {

         edtmobile=edtmobilefirst.getText().toString();
         edtpass=edtpassfirst.getText().toString();
        Log.i("LOG","qqqm="+edtmobile);


        sqLiteDatabase =dbSignUp.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME9+" where mobile='"+edtmobile+"'",null);
        ArrayList<ModelSignUp> models2=new ArrayList<>();
        Log.i("LOG","cu="+cursor);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String getname=cursor.getString(1);
             getmobile=cursor.getString(2);
             getpass=cursor.getString(3);
            Log.i("LOG","m="+getmobile);
            Log.i("LOG","p="+getpass);

            models2.add(new ModelSignUp(id,getname,getmobile,getpass));



        }

        cursor.close();



    }
}