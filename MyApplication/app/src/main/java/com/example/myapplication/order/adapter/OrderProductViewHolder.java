package com.example.myapplication.order.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.order.DataItem;
import com.example.myapplication.order.FormatPrice;

import androidx.recyclerview.widget.RecyclerView;

public class OrderProductViewHolder extends RecyclerView.ViewHolder {

    private TextView mTxtProductName, mTxtProductSize, mTxtProductPrice;
    private ImageView mProductImage;
    private FormatPrice formatPrice = new FormatPrice();

    public OrderProductViewHolder(View view) {
        super (view);
        mTxtProductName = view.findViewById (R.id.tv_name_product);
        mTxtProductSize = view.findViewById (R.id.tv_size);
        mTxtProductPrice = view.findViewById (R.id.tv_cost);
        mProductImage = view.findViewById (R.id.img_product);
    }

    public void bindToViewHolder(DataItem currentProduct) {

        mTxtProductName.setText (currentProduct.getProductName ());
        mTxtProductSize.setText (currentProduct.getVariants ().get (0).getVal ());
        mTxtProductPrice.setText (formatPrice.formatPrice (Integer.parseInt (String.valueOf (currentProduct.getBasePrice ()))));
        Glide.with (itemView.getContext ()).load (currentProduct.getImage ()).centerCrop ().placeholder (R.drawable.asdhhnn).into (mProductImage);
    }
}
