package com.example.myapplication.login.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginFragment extends Fragment {
    FragmentManager frm;
    Button btn_signup,btn_login,btn_logout;
    LoginButton loginButton;
    SignInButton signInButton;
    CallbackManager callbackManager;
    GoogleSignInClient gsc;
    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewId(view);
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.example.myapplication",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        callbackManager= CallbackManager.Factory.create();
        set_loginface();
        set_google();
    }

    private void initViewId(View view) {
        loginButton = view.findViewById(R.id.login_button_face);
        btn_signup = view.findViewById(R.id.btn_signup);
        btn_login = view.findViewById(R.id.Login);
        signInButton=view.findViewById(R.id.login_google);
    }

    private void moveFragment()
    {
        frm =getActivity().getSupportFragmentManager();
        FragmentTransaction ft = frm.beginTransaction();
        ft.replace(R.id.fragment,new RegisterFragment());
        ft.commit();
    }
    private void  set_google()
    {
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc= GoogleSignIn.getClient(getContext(),gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenGoogle=gsc.getSignInIntent();
                startActivityForResult(intenGoogle,0);
            }
        });
    }
    private void set_loginface() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();
                startActivity(new Intent(getContext(), MainActivity.class));
                Log.e("HHHH","ok login");
            }

            @Override
            public void onCancel() {
                Log.e("HHHH","onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("HHHH",error.toString());
            }
        });
    }

    private void result() {
        GraphRequest graphRequest=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.e("json",response.toString());

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0)
        {
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            hanleSignInResult(task);
        }
    }

    private void hanleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account=task.getResult(ApiException.class);
            startActivity(new Intent(getContext(),MainActivity.class));
        } catch (ApiException e) {
            e.printStackTrace();
            Log.e("Google sign", String.valueOf(e.getStatusCode()));
        }
    }
}
