package com.armand.foodorder;



import static com.armand.foodorder.DBmain.TABLENAME1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ActivityAddFood extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ImageView avatar;
    EditText edtname;
    EditText edtprice;
    EditText edtcalori;
    Button btnadd;



    int codefood=11;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        dBmain=new DBmain(this);

        avatar=(ImageView) findViewById(R.id.avatar);
        edtname=(EditText) findViewById(R.id.edtname);
        edtcalori=(EditText) findViewById(R.id.edtcalori);
        edtprice=(EditText) findViewById(R.id.edtprice);

        btnadd=(Button) findViewById(R.id.btnadd);
        Button btnbackadd=(Button) findViewById(R.id.btnbackadd);


        String[] type = new String[] {"Food", "Caffe", "Drinking", "Ice Cream"};
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(
                        this,
                        R.layout.menu,
                        type);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed_dropdown);
        autoCompleteTextView.setAdapter(adapter1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){codefood=1;}
                else if(position==1){codefood=2;}
                else if(position==2){codefood=3;}
                else if(position==3){codefood=4;}






                Log.i("LOG","v="+codefood);
            }
        });







        btnbackadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityAddFood.this, ZActivityAdmin.class);
               startActivity(iinent);
                ActivityAddFood.this.finish();


            }
        });



        insertdata();
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });




    }

    private void insertdata() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv=new ContentValues();
                cv.put("avatar",ImageViewToByte(avatar));
                cv.put("name",edtname.getText().toString());
                cv.put("price",edtprice.getText().toString());
                cv.put("calori",edtcalori.getText().toString());
                cv.put("code",codefood);
                sqLiteDatabase=dBmain.getWritableDatabase();
                Long rec=sqLiteDatabase.insert(TABLENAME1,null,cv);
                if(rec!=null)
                {
                    Toast.makeText(ActivityAddFood.this,"insert sucss",Toast.LENGTH_LONG).show();
                    Log.i("LOG","ssss");
                    avatar.setImageResource(R.mipmap.ic_launcher);
                    edtname.setText("");
                    edtprice.setText("");
                    edtcalori.setText("");
                }

            }
        });



    }
    private byte[] ImageViewToByte(ImageView avatar) {
        Bitmap bitmap=((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,stream);
        byte[]bytes=stream.toByteArray();
        return  bytes;

    }

    private void pickFromGallery() {
        CropImage.activity().start(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                Uri resultUri=result.getUri();
                Picasso.get().load(resultUri).into(avatar);

            }

        }

    }

}