package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<UserData> userDataList;

    public ProductAdapter(Context mCtx, List<UserData> userDataList) {
        this.mCtx = mCtx;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(mCtx);
        View view=layoutInflater.inflate(R.layout.layoutlist,null);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        UserData userData=userDataList.get(position);
        holder.id.setText(userData.getEamil());
        holder.category.setText(userData.getCat());
        holder.amount.setText(userData.getAmt());
        holder.date_data.setText(userData.getDate());
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView id,category,amount,date_data;

        public  ProductViewHolder(View itemView){
            super(itemView);

            id=itemView.findViewById(R.id.email);
            category=itemView.findViewById(R.id.cat);
            amount=itemView.findViewById(R.id.amt);
            date_data=itemView.findViewById(R.id.date);

        }


    }


}
