package com.example.myapplication.order.productsale;


import com.example.myapplication.data.order.DataItem;

import java.util.List;

public interface SaleDrinksView {
    void showToast(String s);

    void displayProduct(List<DataItem> dataItem);

    void displayError(String s);
}
