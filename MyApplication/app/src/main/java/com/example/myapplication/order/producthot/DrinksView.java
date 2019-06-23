package com.example.myapplication.order.producthot;


import com.example.myapplication.data.order.DataItem;

import java.util.List;

public interface DrinksView {

    void showToast(String s);

    void displayProduct(List<DataItem> orderResponse);

    void displayError(String s);
}
