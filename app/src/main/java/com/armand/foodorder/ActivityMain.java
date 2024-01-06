package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBPayment.TABLENAME4;
import static com.armand.foodorder.DBReservation.TABLERES;
import static com.armand.foodorder.DBmain.TABLENAME1;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    ImageSlider imageSlider;

    DBmain dbmain;
    SQLiteDatabase sqLiteDatabase1;
    public static   String idcus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageSlider = findViewById(R.id.denslider);

        ImageView imgorder = (ImageView) findViewById(R.id.imgorder);
        ImageView imgreserve = (ImageView) findViewById(R.id.imgreserve);
        ImageView imgkcal = (ImageView) findViewById(R.id.imgkcal);
        ImageView imgwallet = (ImageView) findViewById(R.id.imgwallet);
        ImageView imgprofile = (ImageView) findViewById(R.id.imgprofile);
        ImageView imgmessage = (ImageView) findViewById(R.id.imgmessage);
        ImageView imgfood = (ImageView) findViewById(R.id.imgfood);
        ImageView imgcaffe = (ImageView) findViewById(R.id.imgcaffe);
        ImageView imgdrink = (ImageView) findViewById(R.id.imgdrink);
        ImageView imgicecream = (ImageView) findViewById(R.id.imgicecream);
        LinearLayout lyleft = (LinearLayout) findViewById(R.id.lyleft);
        LinearLayout lyright = (LinearLayout) findViewById(R.id.lyright);


        Animation animationright = AnimationUtils.loadAnimation(ActivityMain.this, R.anim.animright);
        imgreserve.startAnimation(animationright);
        imgcaffe.startAnimation(animationright);
        imgicecream.startAnimation(animationright);
        lyright.startAnimation(animationright);

        Animation animationleft = AnimationUtils.loadAnimation(ActivityMain.this, R.anim.animleft);
        imgorder.startAnimation(animationleft);
        imgfood.startAnimation(animationleft);
        imgdrink.startAnimation(animationleft);
        lyleft.startAnimation(animationleft);


        slider();

        dbmain = new DBmain(this);


        imgreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iinent = new Intent(ActivityMain.this, ActivityReservation.class);
                startActivity(iinent);
                ActivityMain.this.finish();
            }
        });
        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityProfile.class);
                startActivity(iinent);
                ActivityMain.this.finish();
            }
        });
        imgorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityOrder.class);
                startActivity(iinent);
                ActivityMain.this.finish();

            }
        });

        imgfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityFood.class);
                startActivity(iinent);
                ActivityMain.this.finish();
            }
        });
        imgcaffe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityCaffe.class);
                startActivity(iinent);
                ActivityMain.this.finish();

            }
        });
        imgdrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityDrinking.class);
                startActivity(iinent);
                ActivityMain.this.finish();


            }
        });
        imgicecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        imgkcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent = new Intent(ActivityMain.this, ActivityCalori.class);
                startActivity(iinent);
                ActivityMain.this.finish();
            }
        });


    }

    public void slider() {
        List<SlideModel> sliderModelList = new ArrayList<>();

        sliderModelList.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        sliderModelList.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        sliderModelList.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        sliderModelList.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));
        sliderModelList.add(new SlideModel(R.drawable.slider6, ScaleTypes.FIT));
        sliderModelList.add(new SlideModel(R.drawable.slider7, ScaleTypes.FIT));
        imageSlider.setImageList(sliderModelList, ScaleTypes.FIT);

    }






}