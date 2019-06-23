package com.example.myapplication.order;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.CheckNetworkState;
import com.example.myapplication.R;
import com.example.myapplication.main.FragmentInteractionListener;
import com.example.myapplication.order.producthot.DrinksFragment;
import com.example.myapplication.order.productnew.HighLightDrinks;
import com.example.myapplication.order.productsale.SaleDrinks;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class OrderFragment extends Fragment {
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment, container, false);
    }

    private FragmentManager mFragmentManager;
    private DrinksFragment drinksFragment;
    private HighLightDrinks highLightDrinks;
    private SaleDrinks saleDrinks;
    private FormatPrice formatPrice = new FormatPrice();
    private FragmentInteractionListener mListener;
    private TabLayout tabLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewId(view);
        initView(view);
        initEvent();
        Toast.makeText(getContext(), "order", Toast.LENGTH_SHORT).show();
    }

    private void initViewId(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab (tabLayout.newTab ().setText ("Món nổi bật"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Món khuyến mãi"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Món mới"));
        tabLayout.setOnTabSelectedListener(onTabSelectedListener);
    }


    private void initEvent() {

    }

    private void initView(View view) {
        mFragmentManager = getChildFragmentManager ();
        highLightDrinks = HighLightDrinks.newInstance();
        drinksFragment = DrinksFragment.newInstance();
        saleDrinks = SaleDrinks.newInstance();
    }
    private TabLayout.OnTabSelectedListener onTabSelectedListener  = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition ();
            switch (position) {
                case 0:
                    addTab (drinksFragment);
                    break;
                case 1:
                    addTab (highLightDrinks);
                    break;
                case 2:
                    addTab(saleDrinks);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
    private void addTab(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.order_frame_layout, fragment).addToBackStack(null).commit();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (CheckNetworkState.getInstance(getActivity().getApplication()).checkNetwork()) {
        } else {
            Toast.makeText(getContext(), "Không có kết nối internet", Toast.LENGTH_SHORT).show();
        }

    }
}
