package com.armand.foodorder;


import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;
import static com.armand.foodorder.DBmain.TABLENAME1;

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

public class ZAdapterListFood extends RecyclerView.Adapter<ZAdapterListFood.ViewHloder> {


    Context context;
    int singledata;
    ArrayList<Model> models;
    SQLiteDatabase sqLiteDatabase;

    public ZAdapterListFood(Context context , int singledata , ArrayList<Model>models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_listfood,null);

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

                    DBmain dBmain=new DBmain(context);
                    sqLiteDatabase =dBmain.getReadableDatabase();
                    long delet =sqLiteDatabase.delete(TABLENAME1 ,"id="+model.getId() ,null);
                    if(delet!=-1){
                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                        models.remove(position);
                        notifyDataSetChanged();


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




}
