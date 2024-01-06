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

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ZActivityGainFinal extends AppCompatActivity {

    PieChart piechart1;

    int totalgain;
    int totalsubject;
    DBPayment dbPayment;
    DBSneg dbSneg;
    SQLiteDatabase sqLiteDatabase12;

    int gain2;
    int sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_gainfinal);

        piechart1=(PieChart) findViewById(R.id.piechart1);
        ImageView imgback=(ImageView) findViewById(R.id.imgbackchart);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityGainFinal.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityGainFinal.this.finish();
            }
        });


        dbSneg=new DBSneg(this);
        dbPayment=new DBPayment(this);
       listgain();
        listsubject();



        Log.i("LOG","ttsubj="+totalsubject);
        Log.i("LOG","tt="+totalgain);

         int aa;
          int gain;

         aa=totalgain+totalsubject;
         gain=(100*totalgain)/aa;
          gain2=gain+1;
         sub=(100*totalsubject)/aa;
        Log.i("LOG","gggg="+gain2);
        Log.i("LOG","ssss="+sub);

        adminchart1();

    }

    private void adminchart1() {






        ArrayList<PieEntry> pieEntries=new ArrayList<>();
        PieEntry pieEntry=new PieEntry(gain2,"+");
        PieEntry pieEntry1=new PieEntry(sub,"-");
        pieEntries.add(pieEntry);
        pieEntries.add(pieEntry1);





        PieDataSet pieDataSet=new PieDataSet(pieEntries,"Chart");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        piechart1.setData(new PieData(pieDataSet));
        piechart1.animateXY(2000,2000);
        piechart1.getDescription().setEnabled(false);
        piechart1.animate();
        piechart1.getData().setDrawValues(true);




    }


    private void listsubject() {

        sqLiteDatabase12 =dbSneg.getReadableDatabase();
        Cursor cursor=sqLiteDatabase12.rawQuery("select * from "+TABLENAME5,null);
        ArrayList<ModelNeg> models23=new ArrayList<>();

        String priceneg="0";
        int asd=0;
        totalsubject=asd+totalsubject;
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String subject=cursor.getString(1);
            priceneg=cursor.getString(2);
            String date=cursor.getString(3);

            asd=Integer.parseInt(priceneg);
            totalsubject=asd+totalsubject;
            Log.i("LOG","asd="+asd);

            models23.add(new ModelNeg(id,subject,priceneg,date));

        }

        cursor.close();


    }
    private void listgain() {



        sqLiteDatabase12 =dbPayment.getReadableDatabase();
        Cursor cursor=sqLiteDatabase12.rawQuery("select * from "+TABLENAME4,null);
        ArrayList<ModelPayment> models23=new ArrayList<>();

        String allprice="0";
        int asd=0;
        totalgain=asd+totalgain;

        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String username=cursor.getString(1);
            allprice=cursor.getString(2);
            String delivery=cursor.getString(3);

            asd=Integer.parseInt(allprice);
            totalgain=asd+totalgain;

            models23.add(new ModelPayment(id,username,allprice,delivery));



        }






        cursor.close();


    }

}
