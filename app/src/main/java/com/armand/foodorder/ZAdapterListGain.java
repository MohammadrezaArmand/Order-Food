package com.armand.foodorder;


import static com.armand.foodorder.DBSneg.TABLENAME5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZAdapterListGain extends RecyclerView.Adapter<ZAdapterListGain.ViewHloder> {


    Context context;
    int singledata;
    ArrayList<ModelPayment> models1;
    SQLiteDatabase sqLiteDatabase;

    public ZAdapterListGain(Context context , int singledata , ArrayList<ModelPayment>models1, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models1=models1;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.zadapter_listgain,null);

        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder,  int position) {
        final ModelPayment model=models1.get(position);


       holder.txtusernamegain.setText(model.getUsername());
        holder.txtpricegain.setText(model.getAllprice());

        if(model.getDelivery().equals("0")){
            holder.txtdeliverygain.setText("Wating...");
        }else if(model.getDelivery().equals("1")){
            holder.txtdeliverygain.setText("Delivered...");

        }else{
            Log.i("LOG","nottt");}



    }

    @Override
    public int getItemCount() {
        return models1.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        TextView txtusernamegain;
        TextView txtpricegain;
        TextView txtdeliverygain;

        public ViewHloder(@NonNull View itemView) {
            super(itemView);


            txtusernamegain=(TextView) itemView.findViewById(R.id.txtusernamegain);
            txtpricegain=(TextView) itemView.findViewById(R.id.txtpricegain);
            txtdeliverygain=(TextView) itemView.findViewById(R.id.txtdeliverygain);

        }
    }




}
