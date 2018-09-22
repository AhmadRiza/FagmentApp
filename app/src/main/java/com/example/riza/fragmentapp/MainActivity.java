package com.example.riza.fragmentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginCallback,ResultFragment.LogoutCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new LoginFragment());
    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.my_frame, fragment);
//        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }


    @Override
    public void onLogIn(String username, String password) {
        if(
                username.trim().equalsIgnoreCase("Riza")&&
                password.trim().equalsIgnoreCase("riza")
                ){
            ResultFragment fragment = new ResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }else{
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void logout() {
        replaceFragment(new LoginFragment());
    }
}
