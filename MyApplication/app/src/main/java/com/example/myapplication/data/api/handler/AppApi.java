package com.example.myapplication.data.api.handler;

import com.example.myapplication.data.order.Category;
import com.example.myapplication.data.order.Order;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AppApi {

    @GET("api/v2/menu")
    Observable<Order> getProduct();

    @GET("api/v2/category/web")
    Observable<List<Category>> getCategory();
}
