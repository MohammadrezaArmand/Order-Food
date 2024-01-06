package com.armand.foodorder;


import static com.armand.foodorder.DBSneg.TABLENAME5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZAdapterListSubject extends RecyclerView.Adapter<ZAdapterListSubject.ViewHloder> {


    Context context;
    int singledata;
    ArrayList<ModelNeg> models1;
    SQLiteDatabase sqLiteDatabase;

    public ZAdapterListSubject(Context context , int singledata , ArrayList<ModelNeg>models1, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models1=models1;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_listsubject,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final ModelNeg model=models1.get(position);


       holder.txtsubject.setText(model.getSubject());
        holder.txtpriceneg.setText(model.getPriceneg());
        holder.txtdateneg.setText(model.getDate());

        holder.imgdelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DBSneg dbSneg=new DBSneg(context);
                    sqLiteDatabase =dbSneg.getReadableDatabase();
                    long delet =sqLiteDatabase.delete(TABLENAME5 ,"id="+model.getId() ,null);
                    if(delet!=-1){
                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                        models1.remove(position);
                        notifyDataSetChanged();


                    }


            }
        });

    }

    @Override
    public int getItemCount() {
        return models1.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        TextView txtsubject;
        TextView txtpriceneg;
        TextView txtdateneg;
        ImageView imgdelet;
        public ViewHloder(@NonNull View itemView) {
            super(itemView);


            txtsubject=(TextView) itemView.findViewById(R.id.txtsubject);
            txtpriceneg=(TextView) itemView.findViewById(R.id.txtpriceneg);
            txtdateneg=(TextView) itemView.findViewById(R.id.txtdateneg);
            imgdelet=(ImageView) itemView.findViewById(R.id.imgdone);
        }
    }




}
