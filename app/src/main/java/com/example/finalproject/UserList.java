package com.example.finalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserList extends ArrayAdapter<UserData> {

    private Activity context;
    private List<UserData> userList;

    public UserList(Activity context,List<UserData> userList){

        super(context,R.layout.list_layout,userList);
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewCat=(TextView) listViewItem.findViewById(R.id.cat_d);
        TextView textViewAmt=(TextView) listViewItem.findViewById(R.id.amt_d);
        TextView textViewDate=(TextView) listViewItem.findViewById(R.id.date_d);
        TextView textViewEmail=(TextView) listViewItem.findViewById(R.id.email_d);

        UserData userData=userList.get(position);
        textViewEmail.setText(userData.getEamil());
        textViewCat.setText(userData.getCat());
        textViewAmt.setText(userData.getAmt());
        textViewDate.setText(userData.getDate());


        return listViewItem;
    }
}
