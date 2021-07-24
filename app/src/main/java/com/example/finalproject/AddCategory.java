package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCategory extends AppCompatActivity {

    EditText amt,date_data;
    Spinner sp_cat;
    Button add_b;



    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        amt=(EditText)findViewById(R.id.amount);
        date_data=(EditText)findViewById(R.id.date);
        sp_cat=(Spinner)findViewById(R.id.spinner1);
        add_b=(Button)findViewById(R.id.insert);


        databaseReference= FirebaseDatabase.getInstance().getReference("user");

        add_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUser();
            }
        });
    }
    private void addUser(){
        String cat=sp_cat.getSelectedItem().toString();
        String amount=amt.getText().toString().trim();
        String date=date_data.getText().toString().trim();

        if(!TextUtils.isEmpty(amount)){

           String id = databaseReference.push().getKey();
           User user=new User(id,cat,amount,date);
           databaseReference.child(id).setValue(user);
           Toast.makeText(this,"User added",Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this,"User should enter amount",Toast.LENGTH_LONG).show();
        }
    }

}
