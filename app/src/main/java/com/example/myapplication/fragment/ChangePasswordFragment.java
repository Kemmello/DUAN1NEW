package com.example.myapplication.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edtOldPassword, edtPassword_Change, edtRepeatPassword_Change;
    private Button btnOk_Change, btnCancel;
    private ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseFirestore database;
    View mView;


//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
//        mView = inflater.inflate(R.layout.signup_fragment, container, false);
//        initUi();
//        btnOk_Change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OnClickChangePass();
//            }
//        });
//        return mView;
//    }

    private void initUi(){
        progressDialog = new ProgressDialog(getActivity());
        edtOldPassword = mView.findViewById(R.id.edtOldPassword);
        edtPassword_Change = mView.findViewById(R.id.edtPassword_Change);
        edtRepeatPassword_Change = mView.findViewById(R.id.edtRepeatPassword_Change);
        btnOk_Change = mView.findViewById(R.id.btnOk_Change);
        btnCancel = mView.findViewById(R.id.btnCancel);
    }


    public void OnClickChangePass(){
        String oldPassword =edtOldPassword.getText().toString().trim();
        String password_Change = edtPassword_Change.getText().toString();
        String rppassword_Change = edtRepeatPassword_Change.getText().toString();
        progressDialog.show();

        if (TextUtils.isEmpty(oldPassword)){
            Toast.makeText(getActivity(),"Nhập mật khẩu cũ",Toast.LENGTH_LONG).show();
            edtOldPassword.requestFocus();
            edtPassword_Change.setFocusable(false);
            edtRepeatPassword_Change.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(password_Change)){
            Toast.makeText(getActivity(),"Nhập mật khẩu mới",Toast.LENGTH_LONG).show();
            edtPassword_Change.requestFocus();
            edtOldPassword.setFocusable(false);
            edtRepeatPassword_Change.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(rppassword_Change)){
            Toast.makeText(getActivity(),"Mật khẩu để trống",Toast.LENGTH_LONG).show();
            edtRepeatPassword_Change.requestFocus();
            edtPassword_Change.setFocusable(false);
            edtOldPassword.setFocusable(false);
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(password_Change)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"User password updated.", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });

        auth.getCurrentUser();
//        auth.updateCurrentUser("","","Email",);
//        auth.createUserWithEmailAndPassword(email,password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
////                            User user = new User(email,password,name,",",",",0,2);
//                            String id = auth.getCurrentUser().getUid();
//                            DocumentReference documentReference = database.collection("USER").document(id);
//                            Map<String , Object> user = new HashMap<>();
//                            user.put("EMAIL",email);
//                            user.put("PASSWORD",password);
//                            user.put("NAME",name);
//                            user.put("ADDRESS","");
//                            user.put("BIRTHDAY","");
//                            user.put("PHONE","");
//                            user.put("ROLE",2);
//                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(getActivity(),"Tạo tài khoản thành công",Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//                        }else {
//                            Toast.makeText(getActivity(),"Tạo tài khoản thất bại",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }


        public ChangePasswordFragment() {
    }

    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onViewCreated( View view,  Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        edtOldPassword = view.findViewById(R.id.edtOldPassword);
//        edtPassword_Change = view.findViewById(R.id.edtPassword_Change);
//        edtRepeatPassword_Change = view.findViewById(R.id.edtRepeatPassword_Change);
//        btnOk_Change = view.findViewById(R.id.btnOk_Change);
//        btnCancel = view.findViewById(R.id.btnCancel);
//        auth = FirebaseAuth.getInstance();
//        database = FirebaseFirestore.getInstance();
//        btnOk_Change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChangePass();
//            }
//        });
//    }
}