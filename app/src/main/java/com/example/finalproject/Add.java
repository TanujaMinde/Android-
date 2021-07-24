package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add extends AppCompatActivity {

    EditText amt,date_data;
    Spinner sp;
    Button add_b;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    DatabaseReference userreference;

    EditText datacat;
    String textData="";
    DatabaseReference catreference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spdatalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        amt=(EditText)findViewById(R.id.amount);
        date_data=(EditText)findViewById(R.id.date);
        sp=(Spinner)findViewById(R.id.spinner1);
        add_b=(Button)findViewById(R.id.insert);

        datacat=(EditText)findViewById(R.id.catname);
        catreference= FirebaseDatabase.getInstance().getReference("CategoryName");

        spdatalist=new ArrayList<>();
        adapter=new ArrayAdapter<String>(Add.this,R.layout.support_simple_spinner_dropdown_item,spdatalist);
        sp.setAdapter(adapter);
        Retrivedata();

        userreference=FirebaseDatabase.getInstance().getReference();
        add_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat=sp.getSelectedItem().toString();
                String amount=amt.getText().toString().trim();
                String date=date_data.getText().toString().trim();
                String id = userreference.push().getKey();
                firebaseAuth=FirebaseAuth.getInstance();
                user=firebaseAuth.getCurrentUser();
                String data =user.getEmail().toString().trim();

                UserData userData=new UserData(data,cat,amount,date);
                userreference.child("User").child(id).setValue(userData);
                Toast.makeText(Add.this,"User added",Toast.LENGTH_LONG).show();




            }
        });
    }
    public void Retrivedata(){
        listener=catreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    spdatalist.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void InsertCat(View view) {
        textData=datacat.getText().toString().trim();
        catreference.push().setValue(textData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                datacat.setText("");
                spdatalist.clear();
                Retrivedata();
                adapter.notifyDataSetChanged();
                Toast.makeText(Add.this,"Data Inserted",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
