package com.example.resipeapp.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resipeapp.R;
import com.example.resipeapp.SignUpActivity;
import com.example.resipeapp.model.entities.Recetas;
import com.example.resipeapp.model.entities.Registro;
import com.example.resipeapp.model.local.BaseDatos;
import com.example.resipeapp.model.local.RecetasDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    //Declaramos la coleccion de firebase con el nombre que creamos en web(google FireBase)
    public static final String NOMBRE_COLECCION2 = "registro";

    private EditText etEmail, etPass;
    ImageView logoimgview;
    TextView titletextview,infotitletextview,forgotpassword,questionregistertextview;
    Button btn_ingresar,btn_registrar;
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

        etEmail=findViewById(R.id.tv_email);
        etPass=findViewById(R.id.tv_pass);


        //Referenciamos el Boton

        btn_ingresar=findViewById(R.id.btn_ingresar);
        //btn_registrar=findViewById(R.id.btn_registrar);




        // sharedPreferences = getSharedPreferences("preferencias", CTRL + espacio); y le seleccionamos MODE_PRIVATE
        sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);
        //hacemos la declaracion del guardado
        if (sharedPreferences.getBoolean("logueado", false)) { //ponemos el mismo nombre de abajo del editor del shared
            finish();//sirve para finalizar, si le damos atras a la app no se devulva al login sino que se cierre
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        referenciar();


        questionregistertextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[7];
                pairs [0] = new Pair<View,String>(logoimgview,"logoimagetrans");
                pairs [1] = new Pair<View,String>(titletextview,"Bienvetextrans");
                pairs [2] = new Pair<View,String>(infotitletextview,"complementextrans");
                pairs [3] = new Pair<View,String>(questionregistertextview,"questionregTrans");
                pairs [4] = new Pair<View,String>(etEmail,"TextoAppTrans");
                pairs [5] = new Pair<View,String>(etPass,"passTrans");
                pairs [6] = new Pair<View,String>(btn_ingresar,"Logtrans");



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    finish();
                }

            }
        });


















    }

    //referenciamos los campos de texto
    private void referenciar() {
        etEmail = findViewById(R.id.tv_email);
        etPass = findViewById(R.id.tv_pass);
    }

    public void clickLogin(View v) {
        String emailUser = etEmail.getText().toString();
        String passUser = etPass.getText().toString();
        if (emailUser.equals("aliriosanchez73251@gmail.com")&& passUser.equals("160720")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("logueado",true);
            editor.apply();
            finish();
        }else{
            Toast.makeText(this, "Usuario No Registrado", Toast.LENGTH_SHORT).show();
        }
        //Hacemos peticion asincronica
        /*FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLECCION2).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String emailUser = etEmail.getText().toString().trim();
                String passUser = etPass.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Alguno de los campos estan vacios", Toast.LENGTH_SHORT).show();
                    //regresamos documentos
                    for (DocumentSnapshot document : task.getResult()){
                        //Registro misRegistro = document.toObject(Registro.class);
                    }

                }else{
                    Log.e("errorFS",task.getException().getMessage());
                }
            }
        });*/
    }
    //creamos el metodo para el boton
    public void clickRegistroo(View view){
        Intent inte = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(inte);//este nos sirve para mandar a la activity que deseemos despues de poner los datos
    }




}