package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class UserFragment extends Fragment {
    EditText edt1, edt3, edt5, edt7, edt9;
    FirebaseAuth auth;
    FirebaseFirestore database;
    String id;
    String name, email, ngay, phone, diachi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        edt1 = view.findViewById(R.id.edtname);
        edt3 = view.findViewById(R.id.edtgmail);
        edt5 = view.findViewById(R.id.edtphone);
        edt7 = view.findViewById(R.id.edtdiachi);
        edt9 = view.findViewById(R.id.edtdate);


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
                    diachi = task.getResult().getString("ADDRESS");
                    ngay = task.getResult().getString("BIRTHDAY");
                    edt1.setText(name);
                    edt3.setText(email);
                    edt5.setText(phone);
                    edt7.setText(diachi);
                    edt9.setText(ngay);
                }else {

                }
            }
        });

    }
}