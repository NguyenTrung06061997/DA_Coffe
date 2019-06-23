package com.example.myapplication.main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.myapplication.Constant;
import com.example.myapplication.data.order.DataItem;
import com.example.myapplication.order.adapter.OnOrderListItemInteractionListener;
import com.example.myapplication.order.cart.database.CartViewModel;
import com.example.myapplication.order.productdetail.DetailDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.login.fragment.LoginFragment;
import com.example.myapplication.news.NewsFragment;
import com.example.myapplication.order.OrderFragment;
import com.example.myapplication.store.StoreFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity implements OnOrderListItemInteractionListener {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView ();
        addFragment(MainFragment.newInstance());
    }



    private void initView() {
        mFragmentManager = getSupportFragmentManager ();
    }

    private void addFragment(Fragment fragment) {
        mFragmentManager.beginTransaction ()
                .add (R.id.content_main, fragment, Constant.MAIN_FRAGMENT)
                .addToBackStack (null)
                .commit ();
    }

    private void loadFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction ()
                .replace (R.id.content_main, fragment, tag)
                .addToBackStack (tag)
                .commit ();
    }

    @Override
    public void onItemClickListener(DataItem dataItem) {
        DetailDialogFragment.newInstance(dataItem).show(mFragmentManager,"Product");
    }
}
