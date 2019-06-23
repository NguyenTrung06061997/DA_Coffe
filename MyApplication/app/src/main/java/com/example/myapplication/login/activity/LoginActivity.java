package com.example.myapplication.login.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.login.fragment.LoginFragment;
import com.example.myapplication.login.fragment.RegisterFragment;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager frm;
    Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        frm =getSupportFragmentManager();
        FragmentTransaction ft = frm.beginTransaction();
        ft.replace(R.id.fragment,new LoginFragment());
        ft.commit();

    }
    private void init()
    {
        btn_signup=findViewById(R.id.btn_signup);
    }
    private void moveFragment()
    {
        frm =getSupportFragmentManager();
        FragmentTransaction ft = frm.beginTransaction();
        ft.replace(R.id.fragment,new RegisterFragment());
        ft.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signup:
                moveFragment();
                Log.e("HHHH","ok");
                break;
        }
    }
}
