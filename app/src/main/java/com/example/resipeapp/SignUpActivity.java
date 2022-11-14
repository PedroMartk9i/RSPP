package com.example.resipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resipeapp.view.activities.LoginActivity;

public class SignUpActivity extends AppCompatActivity {
    TextView welcomtext,complementextview,questiontextView;
    ImageView Signlogoview;
    EditText Usutextview,Emailtextview,contratextview,cocotextview;
    Button  signbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Referenciamos imagen logo
        Signlogoview=findViewById(R.id.Signlogoview);

        //Referenciamos los TextView
        welcomtext=findViewById(R.id.welcomtext);
        complementextview=findViewById(R.id.complementextview);
        questiontextView=findViewById(R.id.questiontextView);

        //Referenciamos los EditText

        Usutextview=findViewById(R.id.Usueditview);
        Emailtextview=findViewById(R.id.Emaileditview);
        contratextview=findViewById(R.id.contraeditview);
        cocotextview=findViewById(R.id.cocoeditview);

        //Referenciamos el Boton

        signbtn=findViewById(R.id.signbtn);

        questiontextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transitionBack();

            }
        });

    }

    @Override
    public void onBackPressed(){
        transitionBack();
    }

    public void  transitionBack() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        Pair[] pairs = new Pair[7];
        pairs [0] = new Pair<View,String>(Signlogoview,"logoimagetrans");
        pairs [1] = new Pair<View,String>(welcomtext,"Bienvetextrans");
        pairs [2] = new Pair<View,String>(complementextview,"complementextrans");
        pairs [3] = new Pair<View,String>(questiontextView,"questionTrans");
        pairs [4] = new Pair<View,String>(Emailtextview,"emailtexTrans");
        pairs [5] = new Pair<View,String>(contratextview,"contratextrans");
        pairs [6] = new Pair<View,String>(signbtn,"signbTrans");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
            finish();
        }



    }
}