package com.example.myapplication.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.login.fragment.LoginFragment;
import com.example.myapplication.news.NewsFragment;
import com.example.myapplication.order.OrderFragment;
import com.example.myapplication.store.StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private BottomNavigationView navigation;
    private  int itemId = 0;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadFragment(NewsFragment.newInstance());
    }

    private void initView(View view) {
        navigation = view.findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mFragmentManager = getFragmentManager();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    loadFragment(NewsFragment.newInstance());
                    return true;
                case R.id.navigation_order:
                    loadFragment(OrderFragment.newInstance());
                    return true;
                case R.id.navigation_store:
                    loadFragment(StoreFragment.newInstance());
                    return true;
                case R.id.navigation_profile:
                    loadFragment(LoginFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        mFragmentManager.beginTransaction ()
                .replace (R.id.fl_frame_layout, fragment)
                .commit ();
    }

    @Override
    public void onResume() {
        super.onResume();
        navigation.setSelectedItemId(itemId);
    }

    @Override
    public void onPause() {
        super.onPause();
        itemId = navigation.getSelectedItemId();
    }
}
