package com.example.myapplication.fragment;

<<<<<<< HEAD
import android.content.Context;
=======
import android.content.Intent;
>>>>>>> e6c2dcf2d8e66c722efcd5d628e9c0e56a951739
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;


public class LoginFragment extends Fragment {
    EditText edtUsername_Login, edtPassword_Login;
    Button btnLogin;
    Context context;

    @Override
<<<<<<< HEAD
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);
=======
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup)  inflater.inflate(R.layout.login_tag_fragment, container, false);
>>>>>>> e6c2dcf2d8e66c722efcd5d628e9c0e56a951739
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtUsername_Login = view.findViewById(R.id.edtUsername_Login);
        edtPassword_Login = view.findViewById(R.id.edtPassword_Login);
        btnLogin = view.findViewById(R.id.btnLogin);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isError = validate();
//                if (isError) {
//                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

//    private boolean validate() {
//        boolean isError = false;
//
//        String username = edtUsername_Login.getText().toString().trim();
//        if (username.length() == 0) {
//            edtUsername_Login.setError("Tên người dùng không được để trống");
//            isError = true;
//        } else {
//            isError = false;
//        }
//        String password = edtPassword_Login.getText().toString().trim();
//        if (password.length() == 0) {
//            edtPassword_Login.setError("Password không được để trống");
//            isError = true;
//        } else {
//            isError = false;
//        }
//        return isError;
//    }
}