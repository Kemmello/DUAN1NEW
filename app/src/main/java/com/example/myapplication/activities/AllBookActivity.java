package com.example.myapplication.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookAllAdapter;
import com.example.myapplication.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllBookActivity extends AppCompatActivity {
    FirebaseFirestore db;
    BookAllAdapter bookAllAdapter;
    RecyclerView rcvAllBook;
    List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);

        db = FirebaseFirestore.getInstance();

        rcvAllBook = findViewById(R.id.rcvAllBook);
        rcvAllBook.setLayoutManager(new GridLayoutManager(AllBookActivity.this,2));


        books = new ArrayList<>();
        bookAllAdapter = new BookAllAdapter(this, books);
        rcvAllBook.setAdapter(bookAllAdapter);
        db.collection("BOOK")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Book book = document.toObject(Book.class);
                                books.add(book);
                                bookAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}