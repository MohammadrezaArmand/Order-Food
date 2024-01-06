package com.armand.foodorder;


import static com.armand.foodorder.DBPayment.TABLENAME4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZAdapterCustomer extends RecyclerView.Adapter<ZAdapterCustomer.ViewHloder> {



    Context context;
    int singledata;
    ArrayList<ModelSignUp> models;
    SQLiteDatabase sqLiteDatabase;
    DBPayment dbPayment;

    public ZAdapterCustomer(Context context , int singledata , ArrayList<ModelSignUp> models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_customer,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final ModelSignUp model=models.get(position);


        holder.txtcusername.setText(model.getName());
        holder.txtcmobile.setText(model.getMobile());
        holder.ratingBar.setRating(2.5F);
        holder.ratingBar.setNumStars(4);
        holder.ratingBar.setStepSize(0.1f);




        dbPayment=new DBPayment(context);
        sqLiteDatabase =dbPayment.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME4+" where username='"+model.getMobile()+"'",null);
        ArrayList<ModelPayment> models23=new ArrayList<>();

        String allprice="0";
        int qwe;
        int zxc=0;
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String username=cursor.getString(1);
            allprice=cursor.getString(2);
            String delivery=cursor.getString(3);

            qwe=Integer.parseInt(allprice);
            zxc=zxc+qwe;
            Log.i("LOG","pr2:"+allprice);
            Log.i("LOG","ttt:"+zxc);
            models23.add(new ModelPayment(id,username,allprice,delivery));
        }
        cursor.close();

        holder.txtctotal.setText(""+zxc);
        Log.i("LOG","pr:"+allprice);


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        TextView txtcusername;
        TextView txtcmobile;
        TextView txtctotal;
        RatingBar ratingBar;

        public ViewHloder(@NonNull View itemView) {
            super(itemView);


            txtcusername=(TextView) itemView.findViewById(R.id.txtcusername);
            txtcmobile=(TextView) itemView.findViewById(R.id.txtcmobile);
            txtctotal=(TextView) itemView.findViewById(R.id.txtctotal);
           ratingBar=(RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }


    private void list() {





    }

}
