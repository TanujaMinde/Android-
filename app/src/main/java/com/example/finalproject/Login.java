package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ProgressBar pb;
    Button log,reg;
    EditText email,passw;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pb=findViewById(R.id.progressBar);
        email=(EditText) findViewById(R.id.email);
        passw=(EditText) findViewById(R.id.password);
        log=(Button)findViewById(R.id.login);
        reg=(Button)findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uemail=email.getText().toString().trim();
                String password=passw.getText().toString().trim();
                pb.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(uemail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            finish();
                            Toast.makeText(Login.this,"User Logined.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Profile.class));
                        }else{
                            Toast.makeText(Login.this,"Error !"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}

