package com.example.myapplication.activities;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDetailActivity extends AppCompatActivity {
    ImageView imageViewDetail, imgBack;
    EditText tvBookNameDetail, tvBookAuthorDetail, tvBookTypeDetail, tvBookPageDetail, tvBookIntroduction, tvBookPriceDetail;
    String bookNameDetail, bookAuthorDetail, bookTypeDetail, bookPageDetail, bookIntroduction, bookPriceDetail, id;
    static String imageUrl;
    TextView btnSaveAdmin;
    Book book=null;

    List<Book> list;
    int position;

    FirebaseFirestore firestore;
//    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);
        firestore = FirebaseFirestore.getInstance();
//        auth = FirebaseAuth.getInstance();



        final String idtype = getIntent().getStringExtra("type");
        if (idtype != null) {
            id = idtype;
        }
        final String idall = getIntent().getStringExtra("all");
        if (idall != null) {
            id = idall;
        }
        final String iddetail = getIntent().getStringExtra("detail");
        if (iddetail != null) {
            id = iddetail;
        }
//        id = getIntent().getStringExtra("type");
//
//        id = getIntent().getStringExtra("detail");

//        final Object objectType = getIntent().getSerializableExtra("type");
//        if (objectType instanceof Book) {
//            book = (Book) objectType;
//        }

        imageViewDetail = this.findViewById(R.id.imageViewDetail);
        imgBack = this.findViewById(R.id.imgBack);

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetailAD);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetailAD);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetailAD);
        tvBookPageDetail = this.findViewById(R.id.tvBookPageDetailAD);
        tvBookIntroduction = this.findViewById(R.id.tvBookIntroductionAD);
        tvBookPriceDetail = this.findViewById(R.id.tvBookPriceDetailAD);
        btnSaveAdmin = this.findViewById(R.id.btnSaveAdmin);


        getBookDetail();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSaveAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBook();
                getBookDetail();
            }
        });

    }

    public void updateBook(){
        bookNameDetail = tvBookNameDetail.getText().toString();
        bookAuthorDetail = tvBookAuthorDetail.getText().toString();
        bookTypeDetail = tvBookTypeDetail.getText().toString();
        bookPageDetail = tvBookPageDetail.getText().toString();
        bookIntroduction = tvBookIntroduction.getText().toString();
        bookPriceDetail = tvBookPriceDetail.getText().toString();

        if (TextUtils.isEmpty(bookNameDetail)) {
            Toast.makeText(this, "Tên sách không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.requestFocus();
            tvBookAuthorDetail.setFocusable(false);
            tvBookTypeDetail.setFocusable(false);
            tvBookPageDetail.setFocusable(false);
            tvBookIntroduction.setFocusable(false);
            tvBookPriceDetail.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(bookAuthorDetail)) {
            Toast.makeText(this, "Tác giả không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.setFocusable(false);
            tvBookAuthorDetail.requestFocus();
            tvBookTypeDetail.setFocusable(false);
            tvBookPageDetail.setFocusable(false);
            tvBookIntroduction.setFocusable(false);
            tvBookPriceDetail.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(bookTypeDetail)) {
            Toast.makeText(this, "Thể loại không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.setFocusable(false);
            tvBookAuthorDetail.setFocusable(false);
            tvBookTypeDetail.requestFocus();
            tvBookPageDetail.setFocusable(false);
            tvBookIntroduction.setFocusable(false);
            tvBookPriceDetail.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(bookPageDetail)) {
            Toast.makeText(this, "Số trang không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.setFocusable(false);
            tvBookAuthorDetail.setFocusable(false);
            tvBookTypeDetail.setFocusable(false);
            tvBookPageDetail.requestFocus();
            tvBookIntroduction.setFocusable(false);
            tvBookPriceDetail.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(bookIntroduction)) {
            Toast.makeText(this, "Thể loại không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.setFocusable(false);
            tvBookAuthorDetail.setFocusable(false);
            tvBookTypeDetail.setFocusable(false);
            tvBookPageDetail.setFocusable(false);
            tvBookIntroduction.requestFocus();
            tvBookPriceDetail.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(bookPriceDetail)) {
            Toast.makeText(this, "Giới thiệu không hợp lệ !", Toast.LENGTH_LONG).show();
            tvBookNameDetail.setFocusable(false);
            tvBookAuthorDetail.setFocusable(false);
            tvBookTypeDetail.setFocusable(false);
            tvBookPageDetail.setFocusable(false);
            tvBookIntroduction.setFocusable(false);
            tvBookPriceDetail.requestFocus();
            return;
        }


        String price = bookPriceDetail.replace(" VNĐ","");
        int priceInt = parseInt(price);
        int pageInt = parseInt(bookPageDetail);


        DocumentReference documentReference = firestore.collection("BOOK").document(id);
        Map<String, Object> book = new HashMap<>();
//            user.put("EMAIL",email);
        book.put("TITLE", bookNameDetail);
        book.put("AUTHOR", bookAuthorDetail);
        book.put("TYPENAME", bookTypeDetail); //uploadFile());
        book.put("PAGE", pageInt);
        book.put("INTRODUCTION", bookIntroduction);
        book.put("PRICE", priceInt);
        book.put("IMAGE", imageUrl);
        documentReference.set(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdminDetailActivity.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getBookDetail(){
        DocumentReference documentReference = firestore.collection("BOOK").document(id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Book book = documentSnapshot.toObject(Book.class);

                Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
//        Toast.makeText(this, "123"+book.getDOCUMENTID(),Toast.LENGTH_LONG).show();
                tvBookNameDetail.setText(book.getTITLE());
                tvBookAuthorDetail.setText(book.getAUTHOR());
                tvBookTypeDetail.setText(book.getTYPENAME());
                tvBookPageDetail.setText(book.getPAGE().toString());
                tvBookPriceDetail.setText(book.getPRICE().toString() + " VNĐ");
                tvBookIntroduction.setText(book.getINTRODUCTION());
                imageUrl = book.getIMAGE();
            }
        });

    }

}