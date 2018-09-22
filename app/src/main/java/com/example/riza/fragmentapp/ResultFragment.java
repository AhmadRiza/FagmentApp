package com.example.riza.fragmentapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @OnClick(R.id.btn_logout) void logout(){
        callback.logout();
    }

    private Unbinder unbinder;
    private String  name = "";
    private LogoutCallback callback;

    public ResultFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getArguments()!=null) name = getArguments().getString("username","");

        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_result, container, false);
        unbinder = ButterKnife.bind(this, parent);
        tvWelcome.setText(String.format("Welcome %s", name));
        return parent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LogoutCallback){
            callback = (LogoutCallback) context;
        }else{
            Toast.makeText(context, "implement callback!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface LogoutCallback{
        void logout();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }
}
