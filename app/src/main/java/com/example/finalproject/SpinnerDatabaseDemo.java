package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SpinnerDatabaseDemo extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Spinner spinner_db;
    DatabaseReference reference;
//    List<UserData> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_database_demo);
        spinner_db = (Spinner) findViewById(R.id.spinnerDb);
        reference = FirebaseDatabase.getInstance().getReference("User");
        final ArrayList<String> arrayList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot usersnapshot : dataSnapshot.getChildren()) {

                    UserData userData = usersnapshot.getValue(UserData.class);
                    if ((userData.email).equals("tpminde@mitaoe.ac.in")) {
                        Boolean entry = arrayList.contains(userData.cat);
                        if(!entry){
                            arrayList.add(userData.cat);
                        }else{
                            continue;
                        }


                    }

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner_db.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
