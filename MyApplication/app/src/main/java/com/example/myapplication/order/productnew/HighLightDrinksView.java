package com.example.myapplication.order.productnew;


import com.example.myapplication.data.order.DataItem;

import java.util.List;

public interface HighLightDrinksView {
    void showToast(String s);

    void displayProduct(List<DataItem> dataItem);

    void displayError(String s);
}
