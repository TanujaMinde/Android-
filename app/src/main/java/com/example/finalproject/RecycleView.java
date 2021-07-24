package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity {


    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<UserData> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        userDataList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new ProductAdapter(this,userDataList);
        recyclerView.setAdapter(adapter);
    }
}
