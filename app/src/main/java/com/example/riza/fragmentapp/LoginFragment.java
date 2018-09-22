package com.example.riza.fragmentapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.ed_username)
    EditText edUserName;

    @BindView(R.id.ed_pass)
    EditText edPassword;

    @OnClick(R.id.btn_login) void login(){
//            ganti fragment di main activity
        if(TextUtils.isEmpty(edUserName.getText().toString())){
            edUserName.setError("This cannot be empty");
            return;
        }
        if(TextUtils.isEmpty(edPassword.getText().toString())){
            edPassword.setError("This cannot be empty");
            return;
        }
        if(callback!=null){
            callback.onLogIn(edUserName.getText().toString(), edPassword.getText().toString());
        }
    }

    private Unbinder unbinder;
    private LoginCallback callback;

    public LoginFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_calculate, container, false);
        unbinder = ButterKnife.bind(this, parent);
        return parent;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LoginCallback){
            callback = (LoginCallback) context;
        }else {
            Toast.makeText(context, "Must Implement callback", Toast.LENGTH_SHORT).show();
        }
    }

    public interface LoginCallback{
        void onLogIn(String username, String password);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
