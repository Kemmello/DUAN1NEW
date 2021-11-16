package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    List<Type> types;

    Type type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        rcvType = findViewById(R.id.rcvType);
        rcvType.setLayoutManager(new LinearLayoutManager(this));


        types = new ArrayList<>();
        typeAdapter = new TypeAdapter(this, types);
        rcvType.setAdapter(typeAdapter);

        /////Sách trẻ em
        if (type != null && type.equalsIgnoreCase("Trẻ Em")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Trẻ Em").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Tiểu Thuyết
        if (type != null && type.equalsIgnoreCase("Tiểu Thuyết")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Tiểu Thuyết").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách giáo khoa
        if (type != null && type.equalsIgnoreCase("Giáo Khoa")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Giáo Khoa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Văn Học - Nghệ Thuật
        if (type != null && type.equalsIgnoreCase("Văn Học")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Văn Học").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Khoa học - Công nghệ
        if (type != null && type.equalsIgnoreCase("Khoa Học")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Khoa Học").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Chính trị - Xã hội
        if (type != null && type.equalsIgnoreCase("Chính Trị")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Chính Trị").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách lịch sử
        if (type != null && type.equalsIgnoreCase("Lịch Sử")) {
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Lịch Sử").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


    }
}