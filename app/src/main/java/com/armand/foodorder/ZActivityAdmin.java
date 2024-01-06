package com.armand.foodorder;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ZActivityAdmin extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_admin);




        ImageView imglistcost=(ImageView) findViewById(R.id.imglistcost);
        ImageView imglistfood=(ImageView) findViewById(R.id.imglistfood);
        ImageView imgs1=(ImageView) findViewById(R.id.imgs1);
        ImageView imgs3=(ImageView) findViewById(R.id.imgs3);
        ImageView imgs2=(ImageView) findViewById(R.id.imgs2);
        ImageView imgres=(ImageView) findViewById(R.id.imgres);
        ImageView imgadminorder=(ImageView) findViewById(R.id.imgadminorder);
        ImageView imglogoutadmin=(ImageView) findViewById(R.id.imglogoutadmin);
        ImageView imgnull=(ImageView) findViewById(R.id.imgnull);


        imglistcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityCustomer.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();
            }
        });

        imgnull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        imgadminorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityOrderName.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();

            }
        });
        imgs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this, ZActivityListSubject.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();
            }
        });
        imgs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityListGain.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();

            }
        });
        imgres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityReservation.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();
            }
        });
        imgs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityGainFinal.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();
            }
        });
        imglistfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iinent= new Intent(ZActivityAdmin.this,ZActivityListFood.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();



            }
        });

        imglogoutadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityAdmin.this,ActivityLogin.class);
                startActivity(iinent);
                ZActivityAdmin.this.finish();
            }
        });



    }



}