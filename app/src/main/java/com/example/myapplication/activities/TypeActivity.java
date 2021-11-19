package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TypeAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.Type;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TypeActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView rcvType;
    TypeAdapter typeAdapter;
    List<Book> types;
    ImageView imgBackType;
    TextView tvBookTypeActivity;

    Type type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        imgBackType = this.findViewById(R.id.imgBackType);
        tvBookTypeActivity = this.findViewById(R.id.tvBookTypeActivity);

        imgBackType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        rcvType = findViewById(R.id.rcvType);
        rcvType.setLayoutManager(new LinearLayoutManager(this));


        types = new ArrayList<>();
        typeAdapter = new TypeAdapter(this, types);
        rcvType.setAdapter(typeAdapter);

        /////Sách trẻ em
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Trẻ Em")) {
=======
        if(type != null && type.equalsIgnoreCase("Trẻ Em")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Trẻ Em").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("Children");
                    }
                }
            });
        }

        /////Tiểu Thuyết
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Tiểu Thuyết")) {
=======
        if(type != null && type.equalsIgnoreCase("Tiểu Thuyết")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Tiểu Thuyết").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("Novel");
                    }
                }
            });
        }

        /////Sách giáo khoa
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Giáo Khoa")) {
=======
        if(type != null && type.equalsIgnoreCase("Giáo Khoa")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Giáo Khoa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("School");
                    }
                }
            });
        }

        /////Sách Văn Học - Nghệ Thuật
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Văn Học")) {
=======
        if(type != null && type.equalsIgnoreCase("Văn Học")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Văn Học").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("Literature");
                    }
                }
            });
        }

        /////Sách Khoa học - Công nghệ
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Khoa Học")) {
=======
        if(type != null && type.equalsIgnoreCase("Khoa Học")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Khoa Học").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("Technology");
                    }
                }
            });
        }

        /////Sách Chính trị - Xã hội
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Chính Trị")) {
=======
        if(type != null && type.equalsIgnoreCase("Chính Trị")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Chính Trị").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("Politics");
                    }
                }
            });
        }

        /////Sách lịch sử
<<<<<<< Updated upstream
        if (type != null && type.equalsIgnoreCase("Lịch Sử")) {
=======
        if(type != null && type.equalsIgnoreCase("Lịch Sử")){
>>>>>>> Stashed changes
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Lịch Sử").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Book type1 = documentSnapshot.toObject(Book.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                        tvBookTypeActivity.setText("History");
                    }
                }
            });
        }

    }
}