package com.armand.foodorder;



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

import static com.armand.foodorder.DBHesabdari.TABLENAME3;
import static com.armand.foodorder.DBOrder.TABLENAME2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdapterShow extends RecyclerView.Adapter<AdapterShow.ViewHloder> {

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

    public AdapterShow(Context context , int singledata , ArrayList<Model>models, SQLiteDatabase sqLiteDatabase){
        this.context=context;
        this.singledata=singledata;
        this.sqLiteDatabase=sqLiteDatabase;
        this.models=models;

    }


    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.adapter_show,null);

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
        holder.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHesabdari dbhesabget ;
                dbhesabget=new DBHesabdari(context);
                sqLiteDatabase =dbhesabget.getReadableDatabase();
                Cursor reserve1=sqLiteDatabase.rawQuery("select * from "+TABLENAME3+" where username='"+codem+"'",null);
               ArrayList<ModelHesabdari> modelHesabdaris=new ArrayList<>();

                String allprice = "0";
                String pay ="0";
                while (reserve1.moveToNext()){
                    int id=reserve1.getInt(0);
                    String username=reserve1.getString(1);
                    allprice=reserve1.getString(2);
                     pay=reserve1.getString(3);

                    Log.i("LOG","username"+username);
                    Log.i("LOG","a3");

                    modelHesabdaris.add(new ModelHesabdari(id,username,allprice,pay));
                }
                Log.i("LOG","res1"+allprice);


                reserve1.close();

                if (pay.equals("0")){

                    DBOrder dbSfaresh;
                    SQLiteDatabase sqLiteDatabase;
                    dbSfaresh=new DBOrder(context);
                    Log.i("LOG","nameeee="+model.getUsername());
                    ContentValues cv=new ContentValues();
                    cv.put("avatar",ImageViewToByte(holder.imgavatar));
                    cv.put("name",model.getUsername());
                    cv.put("price",model.getPrice());
                    cv.put("calori",model.getCalori());
                    cv.put("code",codem);
                    sqLiteDatabase=dbSfaresh.getWritableDatabase();
                    Long rec=sqLiteDatabase.insert(TABLENAME2,null,cv);
                    Log.i("LOG","code"+codem);
                    if(rec!=null)
                    {
                        Toast.makeText(context,"insert sucss",Toast.LENGTH_LONG).show();
                        Log.i("LOG","ssss");
                        Log.i("LOG","ssss"+cv);
                        Log.i("LOG","qq"+model.getId());

                    }

                    /***********/
                    ////////////////
                    Log.i("LOG","a2");
                    int asd=Integer.parseInt(allprice);
                    int asd2=Integer.parseInt(model.getPrice());
                    int total=asd+asd2;
                    String total2=Integer.toString(total);
                    Log.i("LOG","res2"+total);
                    Log.i("LOG","res3"+model.getPrice());


                    ContentValues cvh=new ContentValues();
                    DBHesabdari dbhesab ;
                    dbhesab=new DBHesabdari(context);
                    cvh.put("allprice",total2);

                    sqLiteDatabase=dbhesab.getWritableDatabase();
                    sqLiteDatabase.update(TABLENAME3,cvh,"username='"+codem+"'",null);
                    //////////////


                }else{

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
        ImageView imgadd;
        public ViewHloder(@NonNull View itemView) {
            super(itemView);

            imgavatar=(ImageView) itemView.findViewById(R.id.avatar);
            name=(TextView) itemView.findViewById(R.id.username);
            price=(TextView) itemView.findViewById(R.id.txtdelivery);
            calori=(TextView) itemView.findViewById(R.id.txtallprice);
          //  code=(TextView) itemView.findViewById(R.id.txtcode);
            imgadd=(ImageView) itemView.findViewById(R.id.imgdone);
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
