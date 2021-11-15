package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Book;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewDetail , imgAdd , imgMinus;
    TextView tvBookNameDetail, tvBookAuthorDetail , tvBookTypeDetail , tvQuantity;
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

        tvBookNameDetail = this.findViewById(R.id.tvBookNameDetail);
        tvBookAuthorDetail = this.findViewById(R.id.tvBookAuthorDetail);
        tvBookTypeDetail = this.findViewById(R.id.tvBookTypeDetail);
        tvQuantity = this.findViewById(R.id.tvQuantity);

        if (book != null){
            Glide.with(getApplicationContext()).load(book.getIMAGE()).into(imageViewDetail);
            tvBookNameDetail.setText(book.getNEWBOOK());
            tvBookAuthorDetail.setText(book.getAUTHOR());
            tvBookTypeDetail.setText(book.getTYPENAME());
        }

        btnAddCart = this.findViewById(R.id.btnAddCart);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 0){
                    totalQuantity--;
                    tvQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    tvQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }
}