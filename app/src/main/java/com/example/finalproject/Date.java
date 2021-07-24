package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Date extends Fragment {
    Spinner spinner;
    EditText date;
    ListView listView;
    public Date() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_date, container, false);
        spinner = rootView.findViewById(R.id.spinner1);
        date = rootView.findViewById(R.id.date);
        listView = rootView.findViewById(R.id.listViewUserDate);
        return rootView;
    }




}










//package com.example.finalproject;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Spinner;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class Date extends Fragment implements AdapterView.OnItemSelectedListener{
//    Spinner spinner;
//    EditText date;
//    DatabaseReference reference;
//    ListView listView;
//    UserList adapter;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser user;
//    List<UserData> userlist;
//    String data,showvalue;
//    Button shw;
//    String categorydata,showdate;
//    ArrayAdapter<CharSequence> adaptersp;
//    EditText searchdate;
//    Spinner spinner_db;
//    DatabaseReference spreference;
//    public Date() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        final View rootView = inflater.inflate(R.layout.fragment_date, container, false);
//
//        reference= FirebaseDatabase.getInstance().getReference("User");
//        listView=rootView.findViewById(R.id.listViewUser);
//        userlist=new ArrayList<>();
//
//        firebaseAuth= FirebaseAuth.getInstance();
//        user=firebaseAuth.getCurrentUser();
//        data =user.getEmail().toString().trim();
//
//        spinner_db = rootView.findViewById(R.id.spinner1);
//        spreference= FirebaseDatabase.getInstance().getReference("User");
//        final ArrayList<String> arrayList = new ArrayList<>();
//
//
//        searchdate=rootView.findViewById(R.id.date);
//        shw=rootView.findViewById(R.id.submit);
//
//        shw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showvalue=searchdate.getText().toString().trim();
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        userlist.clear();
//                        String id =reference.getKey();
//                        Log.d("TAG", "this is category:-" + categorydata);
//                        Log.d("TAG", "this is main date:-" + showvalue);
//                        for(DataSnapshot usersnapshot:dataSnapshot.getChildren()){
//
//                            UserData userData = usersnapshot.getValue(UserData.class);
//                            if((userData.email).equals(data)) {
//                                if((showvalue.equals(userData.date)) && (categorydata.equals(userData.cat)) ){
//                                    Log.d("TAG", "this is email login:-" + userData.email);
//                                    Log.d("TAG", "this is email:-" + data);
//                                    userlist.add(userData);
//                                }
//
//                            }
//                        }
//                        UserList adapter=new UserList((Activity) getContext(),userlist);
//                        listView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//        });
//
//        spreference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
//                for (DataSnapshot usersnapshot : dataSnapshot.getChildren()) {
//
//                    UserData userData = usersnapshot.getValue(UserData.class);
//                    if ((userData.email).equals(data)) {
//                        Boolean entry = arrayList.contains(userData.cat);
//                        if(!entry){
//                            arrayList.add(userData.cat);
//                        }else{
//                            continue;
//                        }
//
//
//                    }
//
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
//                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                spinner_db.setAdapter(adapter);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        return rootView;
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//}
//
//
//
//
