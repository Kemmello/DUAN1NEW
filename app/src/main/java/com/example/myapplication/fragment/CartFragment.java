package com.example.myapplication.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyCartAdapter;
import com.example.myapplication.model.MyCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CartFragment extends Fragment {

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    TextView tvTotalAmount;
    Button btnBuy;

    RecyclerView rcvCart;
    MyCartAdapter cartAdapter;
    List<MyCart> myCartList;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View root = inflater.inflate(R.layout.fragment_cart, container, false);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        rcvCart = root.findViewById(R.id.rcvCart);
        rcvCart.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnBuy = root.findViewById(R.id.btnBuy);

        tvTotalAmount = root.findViewById(R.id.tvTotalAmount);

        myCartList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), myCartList);
        rcvCart.setAdapter(cartAdapter);



        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();
                firestore = FirebaseFirestore.getInstance();

                if(myCartList!= null && myCartList.size() >0){
                    for (MyCart cart : myCartList){

                        final HashMap<String, Object> cartMap = new HashMap<>();

                        cartMap.put("TITLE", cart.getTITLE());
                        cartMap.put("PRICE", cart.getPRICE());
                        cartMap.put("IMAGE", cart.getIMAGE());
                        cartMap.put("TOTALPRICE", cart.getTOTALPRICE());
                        cartMap.put("CURRENTDATE",cart.getCURRENTDATE() );
                        cartMap.put("CURRENTTIME", cart.getCURRENTTIME());
                        cartMap.put("TOTALQUANTITY", cart.getTOTALQUANTITY());

                        firestore.collection("CURRENTUSER").document(auth.getCurrentUser().getUid())
                                .collection("MYORDER").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(getContext(), "Your order has been complete", Toast.LENGTH_LONG).show();
                            }
                        });
                        firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
                                .collection("CURRENTUSER")
                                .document(cart.getDOCUMENTID())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            myCartList.remove(cart);
                                            cartAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

        myCartList.clear();

        firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
                .collection("CURRENTUSER").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();
                        MyCart cart = documentSnapshot.toObject(MyCart.class);
                        cart.setDOCUMENTID(documentId);
                        myCartList.add(cart);
                        cartAdapter.notifyDataSetChanged();
                        TotalMoney(myCartList);
                    }

                }
            }
        });
        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();
            TotalMoney(myCartList);
        }

    }
    public  void TotalMoney(List<MyCart> list){
        int totalAmount = 0;
        for (MyCart myCart : list){
            totalAmount += myCart.getTOTALPRICE();
        }
        tvTotalAmount.setText("Total : "+totalAmount);

    }
}