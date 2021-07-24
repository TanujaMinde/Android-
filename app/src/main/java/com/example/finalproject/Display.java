package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Display extends AppCompatActivity {


    DatabaseReference reference;
    ListView listView;
    UserList adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<UserData> userlist;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        reference=FirebaseDatabase.getInstance().getReference("User");
        listView=(ListView)findViewById(R.id.listViewUser);
        userlist=new ArrayList<>();
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        data =user.getEmail().toString().trim();



    }


    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                String id =reference.getKey();
                for(DataSnapshot usersnapshot:dataSnapshot.getChildren()){


                        UserData userData = usersnapshot.getValue(UserData.class);
                        if((userData.email).equals(data)) {
                            Log.d("TAG", "this is email login:-" + userData.email);
                            Log.d("TAG", "this is email:-" + data);
                            userlist.add(userData);
                        }
                }
                UserList adapter=new UserList(Display.this,userlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
