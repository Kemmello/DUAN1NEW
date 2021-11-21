package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyCartAdapter;
import com.example.myapplication.model.MyCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BillFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    RecyclerView rcvBill;
    List<MyCart> myCartList;
    MyCartAdapter cartAdapter;


    public BillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_bill, container, false);

        rcvBill = root.findViewById(R.id.rcvBill);
        rcvBill.setLayoutManager(new LinearLayoutManager(getActivity()));

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        myCartList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), myCartList);
        rcvBill.setAdapter(cartAdapter);

        firestore.collection("CURRENTUSER").document(auth.getCurrentUser().getUid())
                .collection("MYORDER").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        MyCart cart = documentSnapshot.toObject(MyCart.class);
                        cart.setDOCUMENTID(documentId);


                        myCartList.add(cart);
                        cartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        return root;
    }
}