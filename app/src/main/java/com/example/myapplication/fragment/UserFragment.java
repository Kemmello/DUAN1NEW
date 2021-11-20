package com.example.myapplication.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class UserFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String email, name, phone, address, birthday;
    FirebaseUser auth;
    EditText edtUserName, edtDate, edtPhone, edtEmail, edtAddress;
    Button btnSave, btnCancel;
    FirebaseFirestore database;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        edtUserName = view.findViewById(R.id.edtUserName);
        edtDate = view.findViewById(R.id.edtDate);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtAddress = view.findViewById(R.id.edtAddress);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancelUpdate);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();

            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth auth;
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();
        getUser();

    }

    public void getUser() {
        DocumentReference reference = database.collection("USER").document(id);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    name = task.getResult().getString("NAME");
                    email = user.getEmail();
                    phone = task.getResult().getString("PHONE");
                    address = task.getResult().getString("ADDRESS");
                    birthday = task.getResult().getString("BIRTHDAY");
                    edtUserName.setText(name);
                    edtDate.setText(birthday);
                    edtPhone.setText(phone);
                    edtEmail.setText(email);
                    edtAddress.setText(address);
                } else {
                    Toast.makeText(getActivity(), "vui long dang nhap lai!:", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateUser() {

        name = edtUserName.getText().toString();
        email = edtEmail.getText().toString();
        birthday = edtDate.getText().toString();
        phone = edtPhone.getText().toString();
        address = edtAddress.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "Tên không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.requestFocus();
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Email không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.requestFocus();
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(getActivity(), "Ngày sinh không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.requestFocus();
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "Sđt không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.requestFocus();
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getActivity(), "địa chỉ không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.requestFocus();
            return;
        }
//        if (task.isSuccessful()){
//                            User user = new User(email,password,name,",",",",0,2);
        DocumentReference documentReference = database.collection("USER").document(id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> user = new HashMap<>();
//            user.put("EMAIL",email);
                            user.put("NAME", name);
                            user.put("ADDRESS", address);
                            user.put("BIRTHDAY", birthday);
                            user.put("PHONE", phone);
                            documentReference.set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}



