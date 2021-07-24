package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button reg,log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg=(Button)findViewById(R.id.signup);
        log=(Button)findViewById(R.id.login);


        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                register();

            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent=new Intent(MainActivity.this,Profile.class);
            startActivity(intent);
            finish();
        }
    }

    public void register(){
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void login(){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }

}
