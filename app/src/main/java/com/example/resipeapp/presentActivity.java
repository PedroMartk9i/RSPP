package com.example.resipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resipeapp.view.activities.LoginActivity;

public class presentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_present);
        //En este apartado agregamos los metodos de las animaciones
        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.desplazamientoarriba);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.desplazamientoabajo);
        TextView titulotextView=findViewById(R.id.titulotextView);
        ImageView logoimageView=findViewById(R.id.logoimageView);

        titulotextView.setAnimation(animacion2);
        logoimageView.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(presentActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        },4000);  }
}



