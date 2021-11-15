package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TypeAdapter;
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
        if(type != null && type.equalsIgnoreCase("Sách trẻ em")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách trẻ em").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Tiểu Thuyết
        if(type != null && type.equalsIgnoreCase("Tiểu thuyết")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Tiểu thuyết").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách giáo khoa
        if(type != null && type.equalsIgnoreCase("Sách giáo khoa")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách giáo khoa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Văn Học - Nghệ Thuật
        if(type != null && type.equalsIgnoreCase("Sách Văn Học - Nghệ Thuật")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách Văn Học - Nghệ Thuật").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Khoa học - Công nghệ
        if(type != null && type.equalsIgnoreCase("Sách Khoa học - Công nghệ")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách Khoa học - Công nghệ").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách Chính trị - Xã hội
        if(type != null && type.equalsIgnoreCase("Sách Chính trị - Xã hội")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách Chính trị - Xã hội").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////Sách lịch sử
        if(type != null && type.equalsIgnoreCase("Sách lịch sử")){
            firestore.collection("BOOK").whereEqualTo("TYPENAME", "Sách lịch sử").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        Type type1 = documentSnapshot.toObject(Type.class);
                        types.add(type1);
                        typeAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


    }
}