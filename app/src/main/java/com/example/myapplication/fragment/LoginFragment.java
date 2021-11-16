package com.example.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText edtEmail_Login, edtPassword_Login;
    Button btnLogin;
    Context context;
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtEmail_Login = view.findViewById(R.id.edtEmail_Login);
        edtPassword_Login = view.findViewById(R.id.edtPassword_Login);
        btnLogin = view.findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login(){
        String email = edtEmail_Login.getText().toString();
        String pass = edtPassword_Login.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Email để trống",Toast.LENGTH_LONG).show();
            edtEmail_Login.requestFocus();
            edtPassword_Login.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(getActivity(),"Mật khẩu để trống",Toast.LENGTH_LONG).show();
            edtPassword_Login.requestFocus();
            edtEmail_Login.setFocusable(false);
            return;
        }
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(),"Nhập sai email hoặc mật khẩu",Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }

}