package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class ShowData extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


    DatabaseReference reference;
    ListView listView;
    UserList adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<UserData> userlist;
    String data,showvalue;
    Button shw;
    String categorydata,showdate;
    ArrayAdapter<CharSequence> adaptersp;
    EditText searchdate;
    Spinner spinner_db;
    DatabaseReference spreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        reference=FirebaseDatabase.getInstance().getReference("User");
        listView=(ListView)findViewById(R.id.listViewUser);
        userlist=new ArrayList<>();
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        data =user.getEmail().toString().trim();

        spinner_db = (Spinner) findViewById(R.id.spinnershow);
        spreference= FirebaseDatabase.getInstance().getReference("User");
        final ArrayList<String> arrayList = new ArrayList<>();
//        sp=(Spinner)findViewById(R.id.spYear);

        searchdate=(EditText)findViewById(R.id.showdate);
        shw=(Button)findViewById(R.id.show);

//        adaptersp=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
//        adaptersp.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        sp.setAdapter(adaptersp);

        shw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showvalue=searchdate.getText().toString().trim();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userlist.clear();
                        String id =reference.getKey();
                        Log.d("TAG", "this is category:-" + categorydata);
                        Log.d("TAG", "this is main date:-" + showvalue);
                        for(DataSnapshot usersnapshot:dataSnapshot.getChildren()){

                            UserData userData = usersnapshot.getValue(UserData.class);
                            if((userData.email).equals(data)) {
                                if((showvalue.equals(userData.date)) && (categorydata.equals(userData.cat)) ){
                                    Log.d("TAG", "this is email login:-" + userData.email);
                                    Log.d("TAG", "this is email:-" + data);
                                    userlist.add(userData);
                                }

                    }
                        }
                        UserList adapter=new UserList(ShowData.this,userlist);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        spreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot usersnapshot : dataSnapshot.getChildren()) {

                    UserData userData = usersnapshot.getValue(UserData.class);
                    if ((userData.email).equals(data)) {
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



