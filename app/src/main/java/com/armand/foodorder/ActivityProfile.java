package com.armand.foodorder;

import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBSignUp.TABLENAME9;
import static com.armand.foodorder.DBmain.TABLENAME1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityProfile extends AppCompatActivity {

    DBSignUp dbSignUp;
    SQLiteDatabase sqLiteDatabase;
    String codem=ActivityLogin.idcus;

    TextView txtpusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


      ImageView imgbackp=(ImageView) findViewById(R.id.imgbackp);
      ImageView imglogoutp=(ImageView) findViewById(R.id.imglogoutp);
      ImageView imgddeletac=(ImageView) findViewById(R.id.imgddeletac);
       txtpusername=(TextView) findViewById(R.id.txtpusername);

        dbSignUp=new DBSignUp(this);


        showres();

        imgddeletac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ActivityProfile.this)
                        .setTitle("Sur?")
                        .setMessage("Are you sur delete account?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delettable11();
                                Intent iinent= new Intent(ActivityProfile.this,ActivityLogin.class);
                                startActivity(iinent);
                                ActivityProfile.this.finish();
                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();




            }
        });
      imgbackp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent iinent= new Intent(ActivityProfile.this,ActivityMain.class);
              startActivity(iinent);
              ActivityProfile.this.finish();
          }
      });
      imglogoutp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent iinent= new Intent(ActivityProfile.this,ActivityLogin.class);
              startActivity(iinent);
              ActivityProfile.this.finish();
          }
      });



    }

    private void showres() {

        sqLiteDatabase =dbSignUp.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME9+" where mobile='"+codem+"'",null);
        ArrayList<ModelSignUp> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
           String getname=cursor.getString(1);
           String getmobile=cursor.getString(2);
            String getpass=cursor.getString(3);

           txtpusername.setText(getname);
            Log.i("LOG","m="+getmobile);
            Log.i("LOG","p="+getname);

            models2.add(new ModelSignUp(id,getname,getmobile,getpass));



        }

        cursor.close();



    }

    private void delettable11() {

        DBSignUp dbSignUp;
        dbSignUp = new DBSignUp(this);
        sqLiteDatabase = dbSignUp.getReadableDatabase();
        long delet = sqLiteDatabase.delete(TABLENAME9, "mobile='"+codem+"'", null);
        if (delet != -1) {

            Log.i("LOG", "delet order");


        }


    }


}