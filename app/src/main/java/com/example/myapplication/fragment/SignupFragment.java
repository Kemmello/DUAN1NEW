package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class SignupFragment extends Fragment {
    EditText email,password,repeatPassword;
    Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup)  inflater.inflate(R.layout.signup_fragment, container, false);

        email = root.findViewById(R.id.edtEmail);
        password = root.findViewById(R.id.edtPassword_SignUp);
        repeatPassword = root.findViewById(R.id.edtRepeatPassword);
        btnSubmit = root.findViewById(R.id.btnSubmit);

//        mobileNumber.setTranslationX(800);
//        email.setTranslationX(800);
//        password.setTranslationX(800);
//        repeatPassword.setTranslationX(800);
//
//        mobileNumber.setAlpha(0);
//        email.setAlpha(0);
//        password.setAlpha(0);
//        repeatPassword.setAlpha(0);
//
//        mobileNumber.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        repeatPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}