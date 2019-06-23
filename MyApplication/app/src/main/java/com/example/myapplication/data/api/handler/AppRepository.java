package com.example.myapplication.data.api.handler;

import com.example.myapplication.data.order.Category;
import com.example.myapplication.data.order.Order;

import java.util.List;

import io.reactivex.Observable;

public interface AppRepository {
    Observable<Order> getProduct();
    io.reactivex.Observable<List<Category>> getCategory();

}
