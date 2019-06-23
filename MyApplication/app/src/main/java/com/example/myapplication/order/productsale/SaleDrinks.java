package com.example.myapplication.order.productsale;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.order.DataItem;
import com.example.myapplication.order.Loading.ILoadMore;
import com.example.myapplication.order.adapter.OnOrderListItemInteractionListener;
import com.example.myapplication.order.adapter.OrderProductAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SaleDrinks extends Fragment implements SaleDrinksView {

    private RecyclerView mListProduct;
    private SalePresenter orderPresenter;
    private OrderProductAdapter mAdapter;
    private OnOrderListItemInteractionListener mListener;

    public static SaleDrinks newInstance() {
        SaleDrinks fragment = new SaleDrinks();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof OnOrderListItemInteractionListener) {
            mListener = (OnOrderListItemInteractionListener) context;
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.content_order, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        initView (view);
        setupMVP ();
        getProduct();
    }

    private void initView(View view) {
        mListProduct = view.findViewById (R.id.recycler_view_new);
        mListProduct.setLayoutManager (new GridLayoutManager (getContext (), 2));
        List<DataItem> listProducts = new ArrayList<> ();
        mAdapter = new OrderProductAdapter (getContext (), listProducts,mListProduct);
        mListProduct.setAdapter (mAdapter);
        mAdapter.setListener(mListener);
        mAdapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (listProducts.size() <= 6)// Bạn có thể change max giá trị load ở đây , load tới số lượng như này thì có kéo nữa cũng không load nữa , bỏ điều kiện này đi thì nó cứ thế mà load
                {
                    listProducts.add(null); // Add 1 cái null , để làm gì ? Quay lại cái Adapter của chúng ta mà thấy , nếu gặp item null thì nó sẽ coi đó là Loading View
                    mAdapter.notifyItemInserted(listProducts.size() - 1);// Báo với adapter là có sự thay đổi
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {// Cái này là mình giả lập , bạn có thể replace cái Handler này với hàm fetch tới Web API hoặc database của các bạn để load dữ liệulistProducts.remove(listProducts.size() - 1);//Gỡ bỏ thằng null vừa thêm vào khi nãy
                            mAdapter.notifyItemRemoved(listProducts.size());// Báo với adapter là có sự thay đổi
                            mListProduct.setLayoutManager (new GridLayoutManager (getContext (), 2));
                            List<DataItem> listProducts = new ArrayList<>();
                            mAdapter = new OrderProductAdapter (getContext (), listProducts,mListProduct);
                            mListProduct.setAdapter (mAdapter);
                            mAdapter.setListener (mListener);
                            mAdapter.setLoader();
                        }
                    }, 3000);// Thời gian load dữ liệu
                } else {
                    // Khi đã load hết toàn bộ dữ liệu ta thông báo đã tải xong
                    Toast.makeText(getContext(), "Load data completed !", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void setupMVP() {
        orderPresenter = new SalePresenter(this);
    }

    @Override
    public void showToast(String s) {
    }

    @Override
    public void displayProduct(List<DataItem> itemList) {
        mAdapter.setValues (itemList);
    }

    @Override
    public void displayError(String s) {
    }

    private void getProduct() {
        orderPresenter.getProduct ();
    }

    @Override
    public void onResume() {
        super.onResume ();
        getProduct ();
    }
}
