package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.Type;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewDetail , imgAdd , imgMinus , imgBack;
    TextView tvBookNameDetail, tvBookAuthorDetail , tvBookTypeDetail , tvBookPageDetail , tvBookIntroduction , tvBookPriceDetail , tvQuantity;
    Button btnAddCart;
    Book book = null;
    Type type = null;

    int totalQuantity = 1;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_item);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Book){
            book = (Book) object;
        }

        final Object objectAll = getIntent().getSerializableExtra("all");
        if (objectAll instanceof Book){
            book = (Book) objectAll;
        }

        final Object objectType = getIntent().getSerializableExtra("type");
        if (objectType instanceof Type){
            type = (Type) objectType;
        }

        imageViewDetail = this.findViewById(R.id.imageViewDetail);
        imgAdd = this.findViewById(R.id.imgAdd);
        imgMinus = this.findViewById(R.id.imgMinus);
        imgBack = this.findViewById(R.id.imgBack);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetail);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetail);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetail);
        tvBookPageDetail = this.findViewById(R.id.tvBookPageDetail);
        tvBookIntroduction = this.findViewById(R.id.tvBookIntroduction);
        tvBookPriceDetail = this.findViewById(R.id.tvBookPriceDetail);
        tvQuantity = this.findViewById(R.id.tvQuantity);

        if (book != null){
            Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(book.getTITLE());
            tvBookAuthorDetail.setText(book.getAUTHOR());
            tvBookTypeDetail.setText(book.getTYPENAME());
            tvBookPageDetail.setText(book.getPAGE().toString());
            tvBookPriceDetail.setText(book.getPRICE().toString()+" VNĐ");
            tvBookIntroduction.setText(book.getINTRODUCTION());
        }

        if (type != null){
            Glide.with(getApplicationContext()).load(type.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(type.getTITLE());
            tvBookAuthorDetail.setText(type.getAUTHOR());
            tvBookTypeDetail.setText(type.getTYPENAME());
            tvBookPageDetail.setText(type.getPAGE().toString());
            tvBookPriceDetail.setText(type.getPRICE().toString()+" VNĐ");
            tvBookIntroduction.setText(type.getINTRODUCTION());
        }

        btnAddCart = this.findViewById(R.id.btnAddCart);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 10){
                    totalQuantity++;
                    tvQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity > 0){
                    totalQuantity--;
                    tvQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }

    private void addedToCart() {
            int totalPrice = totalQuantity * book.getPRICE();


            String saveCurrentDate, saveCurrentTime;
            Calendar calForDate = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
            saveCurrentDate = currentDate.format(calForDate.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calForDate.getTime());

            final HashMap<String, Object> cartMap = new HashMap<>();

            cartMap.put("TITLE", book.getTITLE());
            cartMap.put("TOTALPRICE", totalPrice);
            cartMap.put("CURRENTDATE",saveCurrentDate );
            cartMap.put("CURRENTTIME", saveCurrentTime);
            cartMap.put("TOTALQUANTITY", tvQuantity.getText().toString());

            firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(DetailActivity.this, "Added complete", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }
}