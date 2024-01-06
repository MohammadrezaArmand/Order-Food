package com.armand.foodorder;

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBPayment.TABLENAME4;
import static com.armand.foodorder.DBmain.TABLENAME1;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityOrder extends AppCompatActivity {


    DBOrder dBorder;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView rv;
    AdapterOrder adapterOrder;
   public static TextView txtpricetotal;

    NotificationManager mNotificationManager;
    String codem=ActivityLogin.idcus;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rv=(RecyclerView) findViewById(R.id.rvorder);
        txtpricetotal=(TextView)findViewById(R.id.txtpricetotal);
        dBorder=new DBOrder(this);

        list();
        showtotalprice();

        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        ImageView imgbackorder=(ImageView) findViewById(R.id.imgbackorder);
        Button btnpayment=(Button) findViewById(R.id.btnpaymaent);

        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pay();
                Noti();


            }
        });


        imgbackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ActivityOrder.this, ActivityMain.class);
                startActivity(iinent);
                ActivityOrder.this.finish();
            }
        });

    }

    private void list() {

        sqLiteDatabase =dBorder.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME2+" where code='"+codem+"'",null);
        ArrayList<Model> models2=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[] avatar=cursor.getBlob(1);
            String name=cursor.getString(2);
            String price=cursor.getString(3);
            String code=cursor.getString(4);
            String calorii=cursor.getString(5);
            Log.i("LOG","code"+code);

            models2.add(new Model(id,avatar,name,price,code,calorii));


        }

        cursor.close();
        adapterOrder=new AdapterOrder(this,R.layout.adapter_order,models2,sqLiteDatabase);
        rv.setAdapter(adapterOrder);

    }

    public void showtotalprice(){
        DBHesabdari dbhesab ;
        dbhesab=new DBHesabdari(this);
        sqLiteDatabase =dbhesab.getReadableDatabase();
        Cursor reserve=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where username='"+codem+"'",null);
        ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();
        while (reserve.moveToNext()){
            int id=reserve.getInt(0);
            String username=reserve.getString(1);
            String allprice=reserve.getString(2);
            String pay=reserve.getString(3);

            txtpricetotal.setText(allprice);
            Log.i("LOG","u="+username);
            Log.i("LOG","allpri="+allprice);
            Log.i("LOG","pay="+pay);

            modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
        }
        reserve.close();

    }

    public void pay(){
        ContentValues cvh=new ContentValues();
        DBHesabdari dbhesab ;
        dbhesab=new DBHesabdari(this);

        Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where username='"+codem+"'",null);
        ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();

        String pay = "0";
        String allprice="0";
        while (reserve1.moveToNext()){
            int id=reserve1.getInt(0);
            String username=reserve1.getString(1);
             allprice=reserve1.getString(2);
             pay=reserve1.getString(3);


            modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
        }
        Log.i("LOG","res111="+pay);
        Log.i("LOG","awsdg="+ allprice);

        ///////////////////////////////////
        if(pay.equals("0")) {
            cvh.put("pay", "1");
            sqLiteDatabase = dbhesab.getWritableDatabase();
            sqLiteDatabase.update(TABLENAME3, cvh, "username='"+codem+"'", null);

            DBPayment dbPayment;
            dbPayment=new DBPayment(this);
            ContentValues cvpay=new ContentValues();
            cvpay.put("username",codem);
            cvpay.put("allprice",allprice);
            cvpay.put("delivery","0");

            sqLiteDatabase=dbPayment.getWritableDatabase();
            sqLiteDatabase.insert(TABLENAME4,null,cvpay);




        }
        else{

            Log.i("LOG","you have pay="+pay);
            new AlertDialog.Builder(ActivityOrder.this)
                    .setTitle("Reservation")
                    .setMessage("You Order a Payment !!!!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }


    }

    private void Noti() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ActivityOrder.this, "notify_001");

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle("New Oeder");
        bigText.setSummaryText("Text in detail");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentText("You Have A New Order Check Your Admin");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());



    }

}