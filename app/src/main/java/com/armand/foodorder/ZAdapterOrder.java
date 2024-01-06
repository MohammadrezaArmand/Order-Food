package com.armand.foodorder;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ZAdapterOrder extends RecyclerView.Adapter<ZAdapterOrder.ViewHloder> {






    Context context;
    int singledata;
    ArrayList<Model> models;
    SQLiteDatabase sqLiteDatabase;

    public ZAdapterOrder(Context context , int singledata , ArrayList<Model>models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_order,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final Model model=models.get(position);
        byte[]image=model.getProv();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imgavatar.setImageBitmap(bitmap);
        holder.name.setText(model.getUsername());
        holder.txtcodemm.setText(model.getCode());
        holder.price.setText(model.getPrice());
       // holder.calori.setText(model.getCalori());

        holder.imgdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
        TextView txtcodemm;
        TextView code;
        ImageView imgdone;
        public ViewHloder(@NonNull View itemView) {
            super(itemView);

            imgavatar=(ImageView) itemView.findViewById(R.id.avatar);
            name=(TextView) itemView.findViewById(R.id.username);
            price=(TextView) itemView.findViewById(R.id.txtdelivery);
            txtcodemm=(TextView) itemView.findViewById(R.id.txtcodemm);
          //  code=(TextView) itemView.findViewById(R.id.txtcode);
            imgdone=(ImageView) itemView.findViewById(R.id.imgdone);
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
