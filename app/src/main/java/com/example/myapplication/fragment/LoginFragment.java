package com.example.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.ForgetPasswordActivity;
import com.example.myapplication.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText edtEmail_Login, edtPassword_Login;
    TextView tvForgetPassword;
    Button btnLogin;
    Context context;
    FirebaseAuth auth;
    String email;
    static String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        LoginFragment.pass = pass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtEmail_Login = view.findViewById(R.id.edtEmail_Login);
        edtPassword_Login = view.findViewById(R.id.edtPassword_Login);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvForgetPassword = view.findViewById(R.id.tvForgetPassword);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    public void login(){
        email = edtEmail_Login.getText().toString();
        pass = edtPassword_Login.getText().toString();
        setPass(pass);
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Email is empty",Toast.LENGTH_LONG).show();
            edtEmail_Login.requestFocus();
            edtPassword_Login.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(getActivity(),"Password is empty",Toast.LENGTH_LONG).show();
            edtPassword_Login.requestFocus();
            edtEmail_Login.setFocusable(false);
            return;
        }
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"Login success",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(),"Wrong email or password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}