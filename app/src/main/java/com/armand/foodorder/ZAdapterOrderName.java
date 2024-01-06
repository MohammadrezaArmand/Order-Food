package com.armand.foodorder;


import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ZAdapterOrderName extends RecyclerView.Adapter<ZAdapterOrderName.ViewHloder> {



    Context context;
    int singledata;
    ArrayList<ModelHesabdari> models;
    SQLiteDatabase sqLiteDatabase;

    public ZAdapterOrderName(Context context , int singledata , ArrayList<ModelHesabdari> models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_ordername,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final ModelHesabdari model=models.get(position);


        holder.username.setText(model.getUsername());
        holder.allprice.setText(model.getAllprice());
        holder.delivery.setText("wating...");

        holder.lygo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentEditJob = new Intent(context,ZActivityOrder.class);
                intentEditJob.putExtra("code", model.getUsername());
                context.startActivity(intentEditJob);
                Log.i("LOG","aa"+model.getUsername());


            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        TextView username;
        TextView allprice;
        TextView delivery;
        LinearLayout lygo;
        public ViewHloder(@NonNull View itemView) {
            super(itemView);


            username=(TextView) itemView.findViewById(R.id.username);
            delivery=(TextView) itemView.findViewById(R.id.txtdelivery);
            allprice=(TextView) itemView.findViewById(R.id.txtallprice);
            lygo=(LinearLayout) itemView.findViewById(R.id.lygo);
        }
    }


}
