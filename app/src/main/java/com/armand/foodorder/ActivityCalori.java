package com.armand.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityCalori extends AppCompatActivity {

    private RadioGroup rdgroup;
    private RadioGroup rd4;
    private Button btnok;
    private EditText edtage;
    private EditText edtweight;
    private EditText edtheight;


    private int bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calori);



        edtage=(EditText)findViewById(R.id.edtage);
        edtweight=(EditText)findViewById(R.id.edtweight);
        edtheight=(EditText)findViewById(R.id.edtheight);
        TextView txtcalori=(TextView)findViewById(R.id.txtcalori);
        Button btnbackmain=(Button) findViewById(R.id.btnbackmain);

        rdgroup = (RadioGroup) findViewById(R.id.rdgroup);
        rd4 = (RadioGroup) findViewById(R.id.rd4);

        btnok = (Button) findViewById(R.id.btnok);


        btnbackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(ActivityCalori.this,ActivityMain.class);
                startActivity(iinent);
                ActivityCalori.this.finish();
            }
        });

        btnok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rdgroup.getCheckedRadioButtonId();
                int selectedIdrd4 = rd4.getCheckedRadioButtonId();

                int age ;
                int weight ;
                int height ;

                Log.i("LOG","Jender="+selectedId);
                Log.i("LOG","active="+selectedIdrd4);

                if(TextUtils.isEmpty(edtage.getText().toString()) ||TextUtils.isEmpty(edtweight.getText().toString()) || TextUtils.isEmpty(edtheight.getText().toString())){
                    Log.i("LOG","nul=edt");
                    Toast.makeText(ActivityCalori.this, "Plz Enter All Fild", Toast.LENGTH_LONG).show();
                }else {
                    age = Integer.parseInt(edtage.getText().toString());
                    weight = Integer.parseInt(edtweight.getText().toString());
                    height = Integer.parseInt(edtheight.getText().toString());


                    if(selectedId==2131296735){
                        int bmr1;
                        int finalbmr = 0;
                        bmr1= (int) (88.362 + (13.397 * weight) + (4.799 * height));
                        bmr= (int) (bmr1-(5.677 * age));

                        if(selectedIdrd4==2131296729){finalbmr= (int) (bmr*1.2);}
                        else if(selectedIdrd4==2131296730){finalbmr= (int) (bmr*1.375);}
                        else if(selectedIdrd4==2131296731){finalbmr= (int) (bmr*1.55);}
                        else if(selectedIdrd4==2131296732){finalbmr= (int) (bmr*1.725);}
                        else if(selectedIdrd4==2131296733){finalbmr= (int) (bmr*1.9);}
                        else {}
                        txtcalori.setText(String.valueOf(finalbmr));
                        Log.i("LOG","drmen="+finalbmr);
                    }else if (selectedId==2131296736){
                        int bmr1;
                        int finalbmr = 0;
                        bmr1= (int) (447.593 + (9.247 * weight) + (3.098 * height));
                        bmr= (int) (bmr1-(4.330 * age));
                        if(selectedIdrd4==2131296729){finalbmr= (int) (bmr*1.2);}
                        else if(selectedIdrd4==2131296730){finalbmr= (int) (bmr*1.375);}
                        else if(selectedIdrd4==2131296731){finalbmr= (int) (bmr*1.55);}
                        else if(selectedIdrd4==2131296732){finalbmr= (int) (bmr*1.725);}
                        else if(selectedIdrd4==2131296733){finalbmr= (int) (bmr*1.9);}
                        else {}


                        txtcalori.setText(String.valueOf(finalbmr));
                        Log.i("LOG","drwomwn="+finalbmr);
                    }else {
                        Log.i("LOG","nulslctmen="+selectedId);
                    }

                }







            }

        });

    }




}