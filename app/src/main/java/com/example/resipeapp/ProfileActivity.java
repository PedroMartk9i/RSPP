package com.example.resipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resipeapp.view.activities.LoginActivity;
import com.example.resipeapp.view.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView EmaileditView;
    Button outbtn,outbtn2;
    ImageView profileimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EmaileditView=findViewById(R.id.EmaileditView);
        outbtn=findViewById(R.id.outbtn);
        outbtn2=findViewById(R.id.outbtn);
        profileimage=findViewById(R.id.profileimage);


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user !=null){
            EmaileditView.setText(user.getEmail());

        }

        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        outbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}