package com.armand.foodorder;


import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.ViewHloder> {

    public static  int a1=0;
    public static  int a2=0;
    public static int a3=0;
    public static int a4=0;
    public static int a5=0;





    Context context;
    int singledata;
    ArrayList<Model> models;
    SQLiteDatabase sqLiteDatabase;

    String codem=ActivityLogin.idcus;

    public AdapterOrder(Context context , int singledata , ArrayList<Model>models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.adapter_order,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final Model model=models.get(position);
        byte[]image=model.getProv();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imgavatar.setImageBitmap(bitmap);
        holder.name.setText(model.getUsername());
        //  holder.code.setText(model.getCode());
        holder.price.setText(model.getPrice());
        holder.calori.setText(model.getCalori());

        holder.imgdelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHesabdari dbhesabget ;
                dbhesabget=new DBHesabdari(context);
                sqLiteDatabase =dbhesabget.getReadableDatabase();
                Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where username='"+codem+"'",null);
                ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();

                String allprice = "0";
                String pay="0";
                while (reserve1.moveToNext()){
                    int id=reserve1.getInt(0);
                    String username=reserve1.getString(1);
                    allprice=reserve1.getString(2);
                     pay=reserve1.getString(3);


                    modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
                }
                Log.i("LOG","allp="+allprice);
                Log.i("LOG","pay="+pay);

                reserve1.close();

                if(pay.equals("0")){



                        int asd=Integer.parseInt(allprice);
                        int asd2=Integer.parseInt(model.getPrice());
                        int total=asd-asd2;
                        String total2=Integer.toString(total);
                        Log.i("LOG","res2"+total);
                        Log.i("LOG","res3"+model.getPrice());


                        ContentValues cvh=new ContentValues();
                        DBHesabdari dbhesab ;
                        dbhesab=new DBHesabdari(context);
                        cvh.put("allprice",total2);

                        sqLiteDatabase=dbhesab.getWritableDatabase();
                        sqLiteDatabase.update(TABLENAME3,cvh,"username='"+codem+"'",null);

                        ActivityOrder.txtpricetotal.setText(total2);


                    /*********/

                    DBOrder dbOrder=new DBOrder(context);
                    sqLiteDatabase =dbOrder.getReadableDatabase();
                    long delet =sqLiteDatabase.delete(TABLENAME2 ,"id="+model.getId() ,null);
                    if(delet!=-1){
                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                        models.remove(position);
                        notifyDataSetChanged();


                    }


                }else if(pay.equals("1")){

                    Log.i("LOG","resssssss3");
                    new AlertDialog.Builder(context)
                            .setTitle("Order")
                            .setMessage("You Order a Payment !!!!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {
        ImageView imgavatar;
        TextView name;
        TextView price;
        TextView calori;
        TextView code;
        ImageView imgdelet;
        public ViewHloder(@NonNull View itemView) {
            super(itemView);

            imgavatar=(ImageView) itemView.findViewById(R.id.avatar);
            name=(TextView) itemView.findViewById(R.id.username);
            price=(TextView) itemView.findViewById(R.id.txtdelivery);
            calori=(TextView) itemView.findViewById(R.id.txtallprice);
          //  code=(TextView) itemView.findViewById(R.id.txtcode);
            imgdelet=(ImageView) itemView.findViewById(R.id.imgdone);
        }
    }


    private byte[] ImageViewToByte(ImageView avatar) {
        Bitmap bitmap=((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return  bytes;

    }

    private void showres() {

        DBHesabdari dbhesab ;
        dbhesab=new DBHesabdari(context);


        sqLiteDatabase =dbhesab.getReadableDatabase();
        Cursor reserve=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where username='mreza'",null);
        ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();
        while (reserve.moveToNext()){
            int id=reserve.getInt(0);
            String username=reserve.getString(1);
            String allprice=reserve.getString(2);
            String pay=reserve.getString(3);


            Log.i("LOG","u="+username);
            Log.i("LOG","allpri="+allprice);
            Log.i("LOG","pay="+pay);

            modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
        }
        reserve.close();
    }






}
