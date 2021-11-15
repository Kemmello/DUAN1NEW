package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Book;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewDetail , imgAdd , imgMinus , imgBack;
    TextView tvBookNameDetail, tvBookAuthorDetail , tvBookTypeDetail , tvBookPageDetail , tvQuantity;
    Button btnAddCart;
    Book book = null;

    int totalQuantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_item);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Book){
            book = (Book) object;
        }

        imageViewDetail = this.findViewById(R.id.imageViewDetail);
        imgAdd = this.findViewById(R.id.imgAdd);
        imgMinus = this.findViewById(R.id.imgMinus);
        imgBack = this.findViewById(R.id.imgBack);

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetail);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetail);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetail);
        tvBookPageDetail = this.findViewById(R.id.tvBookPageDetail);
        tvQuantity = this.findViewById(R.id.tvQuantity);

        if (book != null){
            Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(book.getTITLE());
            tvBookAuthorDetail.setText(book.getAUTHOR());
            tvBookTypeDetail.setText(book.getTYPENAME());
            tvBookPageDetail.setText(book.getPAGE()+"");
        }

        btnAddCart = this.findViewById(R.id.btnAddCart);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
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
}