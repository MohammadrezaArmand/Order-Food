package com.armand.foodorder;


import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBReservation.TABLERES;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ZAdapterReservation extends RecyclerView.Adapter<ZAdapterReservation.ViewHloder> {



    Context context;
    int singledata;
    ArrayList<ModelReserve> models;
    SQLiteDatabase sqLiteDatabase;

    DBReservation dbReservation;


    public ZAdapterReservation(Context context , int singledata , ArrayList<ModelReserve> models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_reservation,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final ModelReserve model=models.get(position);
        String date=model.getYear()+"/"+model.getMonth()+"/"+model.getDay();
        String time=model.getHour()+":"+model.getMin();

        holder.txtrusernam.setText(model.getUsername());
        holder.txtrtime.setText(time);
        holder.txtrtable.setText(""+model.getNum());
        holder.txtrdate.setText(date);
        holder.imgrdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(context)
                        .setTitle("Done Reservation")
                        .setMessage("Are you sure to cancel Or Done?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbReservation=new DBReservation(context);
                                sqLiteDatabase =dbReservation.getReadableDatabase();
                                long delet =sqLiteDatabase.delete(TABLERES ,"username='"+model.getUsername()+"'" ,null);
                                if(delet!=-1){
                                    Log.i("LOG","uhf");

                                }
                                models.remove(position);
                                notifyDataSetChanged();

                            }
                        }).setNegativeButton("No", null).show();







            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        TextView txtrusernam;
        TextView txtrtime;
        TextView txtrtable;
        TextView txtrdate;
        ImageView imgrdone;

        public ViewHloder(@NonNull View itemView) {
            super(itemView);


            txtrusernam=(TextView) itemView.findViewById(R.id.txtrusernam);
            txtrtime=(TextView) itemView.findViewById(R.id.txtrtime);
            txtrtable=(TextView) itemView.findViewById(R.id.txtrtable);
            txtrdate=(TextView) itemView.findViewById(R.id.txtrdate);
            imgrdone=(ImageView) itemView.findViewById(R.id.imgrdone);

        }
    }


    private byte[] ImageViewToByte(ImageView avatar) {
        Bitmap bitmap=((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return  bytes;

    }


}
