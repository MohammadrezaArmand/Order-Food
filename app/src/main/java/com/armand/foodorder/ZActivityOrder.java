package com.armand.foodorder;


import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBPayment.TABLENAME4;
import static com.armand.foodorder.DBmain.TABLENAME1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZActivityOrder extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DBOrder dBorder;
    ZAdapterOrder zadapterOrder;
    RecyclerView rv;
    String codem=ActivityLogin.idcus;
    int total;


    String codemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zactivity_order);

        rv=(RecyclerView) findViewById(R.id.rvorder);
        ImageView imgbackorder=(ImageView) findViewById(R.id.imgbackorder);
        Button btndelivery=(Button) findViewById(R.id.btndeliveryed);
        TextView txtpricetotal=(TextView)findViewById(R.id.txtpricetotal) ;

        Bundle b = getIntent().getExtras();
         codemo = b.getString("code");
        //qq=Integer.parseInt(codemo);
       // Log.i("LOG","aa1"+qq);
        Log.i("LOG","aa2"+codemo);

        dBorder=new DBOrder(this);

        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        btndelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery();

            }
        });

        imgbackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinent= new Intent(ZActivityOrder.this, ZActivityAdmin.class);
                startActivity(iinent);
                ZActivityOrder.this.finish();
            }
        });


        checkpay();
        txtpricetotal.setText(""+total);




    }

    public void checkpay(){

        DBHesabdari dbhesabget ;
        dbhesabget=new DBHesabdari(this);
        sqLiteDatabase =dbhesabget.getReadableDatabase();
        Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where pay=1",null);
        ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();

        String allprice = "0";
        String pay = "0";
        while (reserve1.moveToNext()){
            int id=reserve1.getInt(0);
            String username=reserve1.getString(1);
            allprice=reserve1.getString(2);
            pay=reserve1.getString(3);

            Log.i("LOG","username="+username);

            modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
        }
        Log.i("LOG","pay="+pay);
        if(pay.equals("1")){
            list();

        }else if(pay.equals("0")){
            Log.i("LOG","nooopay="+pay);

        }

        reserve1.close();



    }

    private void list() {

        sqLiteDatabase =dBorder.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME2+" where code='"+codemo+"'",null);
        ArrayList<Model> models2=new ArrayList<>();

        String price="0";
        int asd=0;
        total=asd+total;
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[] avatar=cursor.getBlob(1);
            String name=cursor.getString(2);
             price=cursor.getString(3);
            String code=cursor.getString(4);
            String calorii=cursor.getString(5);

            asd=Integer.parseInt(price);
            total=asd+total;
            Log.i("LOG","asd="+asd);

            models2.add(new Model(id,avatar,name,price,code,calorii));


        }

        cursor.close();
        zadapterOrder=new ZAdapterOrder(this,R.layout.zadapter_order,models2,sqLiteDatabase);
        rv.setAdapter(zadapterOrder);

    }

    private  void delivery(){

        ContentValues cvh=new ContentValues();
        DBPayment dbPayment ;
        dbPayment=new DBPayment(this);
        sqLiteDatabase =dbPayment.getReadableDatabase();
        Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLENAME4+" where username='"+codemo+"'",null);
        ArrayList<ModelPayment> modelPayments=new ArrayList<>();

        String delivery = "0";
        String allprice="0";
        while (reserve1.moveToNext()){
            int id=reserve1.getInt(0);
            String username=reserve1.getString(1);
            allprice=reserve1.getString(2);
            delivery=reserve1.getString(3);


            modelPayments.add(new ModelPayment(id,username,allprice,delivery));
        }
        Log.i("LOG","res111="+delivery);
        Log.i("LOG","awsdg="+ allprice);

        ///////////////////////////////////
        if(delivery.equals("0")) {
            Log.i("LOG","aaaa");
            cvh.put("delivery", "1");
            sqLiteDatabase = dbPayment.getWritableDatabase();
            sqLiteDatabase.update(TABLENAME4, cvh, "username='"+codemo+"'", null);

            delettable11();
            zeropay();





        }
        else{

            Log.i("LOG","you have pay="+delivery);
            new AlertDialog.Builder(ZActivityOrder.this)
                    .setTitle("Reservation")
                    .setMessage("You Order a delyyy !!!!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }


    }

    private void delettable11() {

        DBOrder dbOrder;
        dbOrder = new DBOrder(this);
        sqLiteDatabase = dbOrder.getReadableDatabase();
        long delet = sqLiteDatabase.delete(TABLENAME2, "code='"+codemo+"'", null);
        if (delet != -1) {

            Log.i("LOG", "delet order");


        }


    }

    private void zeropay(){
        DBHesabdari dbhesab ;
        dbhesab=new DBHesabdari(this);
        SQLiteDatabase sqLiteDatabase;
        ContentValues cvh=new ContentValues();
        cvh.put("pay", "0");
        cvh.put("allprice", "0");
        sqLiteDatabase = dbhesab.getWritableDatabase();
        sqLiteDatabase.update(TABLENAME3, cvh, "username='"+codemo+"'", null);



    }

}