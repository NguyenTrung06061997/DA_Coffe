package com.example.myapplication.data.api.handler;
import com.example.myapplication.data.order.Category;
import com.example.myapplication.data.order.Order;

import java.util.List;

import io.reactivex.Observable;

public class AppRespositoryImp implements AppRepository {

    @Override
    public Observable<List<Category>> getCategory() {
        return ApiHandler.getInstance().getAppApi().getCategory();
    }


    @Override
    public Observable<Order> getProduct() {
        return ApiHandler.getInstance().getAppApi().getProduct();
    }
}
