package com.example.resipeapp;

import static android.util.Patterns.EMAIL_ADDRESS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resipeapp.view.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    TextView welcomtext,complementextview,questiontextView;
    ImageView Signlogoview;
    EditText Usutextview,Emailtextview,contratextview,cocotextview;
    Button  signbtn;

    private FirebaseAuth mAuth;


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

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();


            }
        });
        mAuth=FirebaseAuth.getInstance();


    }

    public void validate(){
        String email=Emailtextview.getText().toString().trim();
        String password=contratextview.getText().toString().trim();
        String Confirmpassword=cocotextview.getText().toString().trim();

        if (email.isEmpty()|| !EMAIL_ADDRESS.matcher(email).matches()) {
            Emailtextview.setError("¡Correo Invalido!");
            return;
        }else {
            Emailtextview.setError(null);

        }
        if (password.isEmpty() || password.length()<8) {
            contratextview.setError("¡Tú contraseña debe tener mas de 8 caracteres!");
            return;

        }else if (!Pattern.compile("[0-9]").matcher(password).find()){
            contratextview.setError("¡Tu contraseña debe tener al menos un caracter numerico!");
            return;

        }else{
            contratextview.setError(null);
        }

        if (!cocotextview.equals(password)){
            registrar(email,password);


        }else{
            cocotextview.setError("¡No son iguales!");

        }

    }

    public void registrar(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(SignUpActivity.this,"No pudo realizarse el registro con Exito.",Toast.LENGTH_LONG).show();
                        }
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
        pairs [0] = new Pair<View,String>(Signlogoview,"LogoImageTrans");
        pairs [1] = new Pair<View,String>(welcomtext,"TitleTrans");
        pairs [2] = new Pair<View,String>(complementextview,"completeTrans");
        pairs [3] = new Pair<View,String>(questiontextView,"questionregTrans");
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