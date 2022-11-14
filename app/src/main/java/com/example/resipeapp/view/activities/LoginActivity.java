package com.example.resipeapp.view.activities;

import static android.util.Patterns.EMAIL_ADDRESS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resipeapp.R;
import com.example.resipeapp.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    //Referenciamos lo que estamos usando en el Layout
    EditText EmaileditView, contraeditView;
    ImageView logoimgview;
    TextView titletextview,infotitletextview,forgotpassword,questionregistertextview;
    Button ingresarbtn;
    //Importamos librerias de Authenticator
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences; // SharedPreferences sirve para guardar las contraseñas y que no nos pida cada vez ingresar los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Referenciamos imagen logo
        logoimgview=findViewById(R.id.logoimgview);

        //Referenciamos los TextView
        titletextview=findViewById(R.id.titletextview);
        infotitletextview=findViewById(R.id.infotitletextview);
        forgotpassword=findViewById(R.id.forgotpassword);
        questionregistertextview=findViewById(R.id.questionregistertextview);

        //Referenciamos los EditText

        EmaileditView=findViewById(R.id.EmaileditView);
        contraeditView=findViewById(R.id.contraeditView);
        mAuth=FirebaseAuth.getInstance();


        //Referenciamos el Boton

        ingresarbtn=findViewById(R.id.ingresarbtn);
        //btn_registrar=findViewById(R.id.btn_registrar);




        questionregistertextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[7];
                pairs [0] = new Pair<View,String>(logoimgview,"logoimagetrans");
                pairs [1] = new Pair<View,String>(titletextview,"Bienvetextrans");
                pairs [2] = new Pair<View,String>(infotitletextview,"complementextrans");
                pairs [3] = new Pair<View,String>(questionregistertextview,"questionTrans");
                pairs [4] = new Pair<View,String>(EmaileditView,"TextoAppTrans");
                pairs [5] = new Pair<View,String>(contraeditView,"passTrans");
                pairs [6] = new Pair<View,String>(ingresarbtn,"Logtrans");



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    finish();
                }

            }
        });


        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    public void validate(){
        String email=EmaileditView.getText().toString().trim();
        String password=contraeditView.getText().toString().trim();


        if (email.isEmpty()|| !EMAIL_ADDRESS.matcher(email).matches()) {
            EmaileditView.setError("¡Correo Invalido!");
            return;
        }else {
            EmaileditView.setError(null);

        }
        if (password.isEmpty() || password.length()<8) {
            contraeditView.setError("¡Tú contraseña debe tener mas de 8 caracteres!");
            return;

        }else if (!Pattern.compile("[0-9]").matcher(password).find()){
            contraeditView.setError("¡Tu contraseña debe tener al menos un caracter numerico!");
            return;

        }else{
            contraeditView.setError(null);

        }
        iniciarSesion(email,password);


    }


    public void iniciarSesion (String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(LoginActivity.this,"Credenciales Incorrectas, intenta de nuevo.",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}