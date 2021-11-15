package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import static android.content.ContentValues.TAG;

public class BookFragment extends Fragment {
    FirebaseFirestore db;
    BookAllAdapter bookAllAdapter;
    RecyclerView rcvAllBook;
    List<Book> books;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_all_book, container, false);
        db = FirebaseFirestore.getInstance();

        rcvAllBook = root.findViewById(R.id.rcvAllBook);
        rcvAllBook.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        books = new ArrayList<>();
        bookAllAdapter = new BookAllAdapter(getActivity(), books);
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
        return root;
    }
}