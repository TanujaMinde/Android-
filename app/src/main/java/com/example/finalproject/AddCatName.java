package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddCatName extends AppCompatActivity {

    Spinner sp;
    EditText data;
    String textData="";
    DatabaseReference reference;

    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spdatalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat_name);
        sp=(Spinner)findViewById(R.id.mysipnner);
        data=(EditText)findViewById(R.id.Catdata);
        reference= FirebaseDatabase.getInstance().getReference("CategoryName");

        spdatalist=new ArrayList<>();
        adapter=new ArrayAdapter<String>(AddCatName.this,R.layout.support_simple_spinner_dropdown_item,spdatalist);
        sp.setAdapter(adapter);
        Retrivedata();



    }

    public void Retrivedata(){
        listener=reference.addValueEventListener(new ValueEventListener() {
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
        textData=data.getText().toString().trim();
        reference.push().setValue(textData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                data.setText("");
                spdatalist.clear();
                Retrivedata();
                adapter.notifyDataSetChanged();
                Toast.makeText(AddCatName.this,"Data Inserted",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
