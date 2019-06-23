package com.example.myapplication.order.adapter;

import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import androidx.recyclerview.widget.RecyclerView;

public class OrderLoadingViewHolder extends RecyclerView.ViewHolder{
    public ProgressBar progressBar;

    public OrderLoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}
