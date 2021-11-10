package com.example.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.DrawerActivity;
import com.example.myapplication.activities.MainActivity;

public class LoginFragment extends Fragment {
    EditText edtUsername_Login, edtPassword_Login;
    Button btnLogin;

    private String username = "admin";
    private String password = "admin";

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtUsername_Login = view.findViewById(R.id.edtUsername_Login);
        edtPassword_Login = view.findViewById(R.id.edtPassword_Login);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String inputUserName = edtUsername_Login.getText().toString();
                String inputPassword = edtPassword_Login.getText().toString();

                boolean isValid = false;
//                if (inputUserName.isEmpty()||inputPassword.isEmpty()) {
//                    Toast.makeText().show();
//                } else {
//                    isValid =  validate(inputUserName,inputPassword);
//                    if (!isValid){
//
//                    }
//                }

                Intent intent = new Intent(getActivity(), DrawerActivity.class);
                startActivity(intent);

                boolean isError = validate();
                if (isError) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
 
    private boolean validate() {
        boolean isError = false;


    private boolean validate(String inputUsername, String inputPassword) {

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
//        String password = edtPassword_Login.getText().toString().trim();
//        if (password.length() == 0) {
//            edtPassword_Login.setError("Password không được để trống");
//            isError = true;
//        } else {
//            isError = false;
//        }
//        return isError;

        String username = edtUsername_Login.getText().toString().trim();
        if (username.length() == 0) {
            edtUsername_Login.setError("Tên người dùng không được để trống");
            isError = true;
        } else {
            isError = false;
        }
        String password = edtPassword_Login.getText().toString().trim();
        if (password.length() == 0) {
            edtPassword_Login.setError("Password không được để trống");
            isError = true;
        } else {
            isError = false;
        }
        return isError;

    }
}