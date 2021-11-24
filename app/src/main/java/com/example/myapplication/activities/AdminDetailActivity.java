package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    ImageView imageViewDetail, imgBack;
    EditText tvBookNameDetail, tvBookAuthorDetail, tvBookTypeDetail, tvBookPageDetail, tvBookIntroduction, tvBookPriceDetail;
    TextView btnSaveAdmin;
    Book book = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Book) {
            book = (Book) object;
        }

        final Object objectAll = getIntent().getSerializableExtra("all");
        if (objectAll instanceof Book) {
            book = (Book) objectAll;
        }

        final Object objectType = getIntent().getSerializableExtra("type");
        if (objectType instanceof Book) {
            book = (Book) objectType;
        }

        imageViewDetail = this.findViewById(R.id.imageViewDetail);
        imgBack = this.findViewById(R.id.imgBack);

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetailAD);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetailAD);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetailAD);
        tvBookPageDetail = this.findViewById(R.id.tvBookPageDetailAD);
        tvBookIntroduction = this.findViewById(R.id.tvBookIntroductionAD);
        tvBookPriceDetail = this.findViewById(R.id.tvBookPriceDetailAD);
        btnSaveAdmin = this.findViewById(R.id.btnSaveAdmin);

        if (book != null) {
            Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(book.getTITLE());
            tvBookAuthorDetail.setText(book.getAUTHOR());
            tvBookTypeDetail.setText(book.getTYPENAME());
            tvBookPageDetail.setText(book.getPAGE().toString());
            tvBookPriceDetail.setText(book.getPRICE().toString() + " VNĐ");
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

}