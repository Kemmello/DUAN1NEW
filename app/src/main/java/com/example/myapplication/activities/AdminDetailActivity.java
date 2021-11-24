package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminDetailActivity extends AppCompatActivity {
    ImageView imageViewDetail , imgBack;
    TextView tvBookNameDetail, tvBookAuthorDetail , tvBookTypeDetail , tvBookPageDetail , tvBookIntroduction , tvBookPriceDetail , btnSaveAdmin;
    Book book = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Book){
            book = (Book) object;
        }

        final Object objectAll = getIntent().getSerializableExtra("all");
        if (objectAll instanceof Book){
            book = (Book) objectAll;
        }

        final Object objectType = getIntent().getSerializableExtra("type");
        if (objectType instanceof Book){
            book = (Book) objectType;
        }

        imageViewDetail = this.findViewById(R.id.imageViewDetail);
        imgBack = this.findViewById(R.id.imgBack);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetail);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetail);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetail);
        tvBookPageDetail = this.findViewById(R.id.tvBookPageDetail);
        tvBookIntroduction = this.findViewById(R.id.tvBookIntroduction);
        tvBookPriceDetail = this.findViewById(R.id.tvBookPriceDetail);
        btnSaveAdmin = this.findViewById(R.id.btnSaveAdmin);

        if (book != null){
            Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(book.getTITLE());
            tvBookAuthorDetail.setText(book.getAUTHOR());
            tvBookTypeDetail.setText(book.getTYPENAME());
            tvBookPageDetail.setText(book.getPAGE().toString());
            tvBookPriceDetail.setText(book.getPRICE().toString()+" VNĐ");
            tvBookIntroduction.setText(book.getINTRODUCTION());
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        btnSaveAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    private void addedToCart() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("TITLE", book.getTITLE());
        cartMap.put("IMAGE", book.getIMAGE());
        cartMap.put("TOTALPRICE", tvBookPriceDetail);
        cartMap.put("CURRENTDATE",saveCurrentDate );
        cartMap.put("CURRENTTIME", saveCurrentTime);
        cartMap.put("PRICE",book.getPRICE());

        firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
                .collection("CURRENTUSER").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(AdminDetailActivity.this, "Added complete", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}