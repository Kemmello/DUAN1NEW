package com.example.myapplication.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    EditText edtUserName, edtDate, edtPhone, edtEmail, edtAddress;
    FirebaseAuth auth;
    FirebaseFirestore database;
    String id;
    String name, email, birthday, phone, address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        edtUserName = view.findViewById(R.id.edtUserName);
        edtDate = view.findViewById(R.id.edtDate);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtAddress = view.findViewById(R.id.edtAddress);

        return view;
    }

    public UserFragment() {
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onStart() {
        super.onStart();

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();


        DocumentReference reference = database.collection("USER").document(id);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    name  = task.getResult().getString("NAME");
                    email = task.getResult().getString("EMAIL");
                    phone = task.getResult().getString("PHONE");
                    address = task.getResult().getString("ADDRESS");
                    birthday = task.getResult().getString("BIRTHDAY");
                    edtUserName.setText(name);
                    edtDate.setText(birthday);
                    edtPhone.setText(phone);
                    edtEmail.setText(email);
                    edtAddress.setText(address);
                }else {

                }
            }
        });

    }
}