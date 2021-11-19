package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.MyCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<MyCart> myCartList =(ArrayList<MyCart>) getIntent().getSerializableExtra("itemlist");

        if(myCartList!= null && myCartList.size() >0){
            for (MyCart cart : myCartList){

                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("TITLE", cart.getTITLE());
                cartMap.put("IMAGE", cart.getIMAGE());
                cartMap.put("TOTALPRICE", cart.getTOTALPRICE());
                cartMap.put("CURRENTDATE",cart.getCURRENTDATE() );
                cartMap.put("CURRENTTIME", cart.getCURRENTTIME());
                cartMap.put("TOTALQUANTITY", cart.getTOTALQUANTITY());

                firestore.collection("CURRENTUSER").document(auth.getCurrentUser().getUid())
                        .collection("MYORDER").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(BillActivity.this, "Your order has been complete", Toast.LENGTH_LONG).show();
                    }
            });
                }
    }
    }
}