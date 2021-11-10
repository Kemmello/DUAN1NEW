package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvShowMoreExplore,tvShowMoreNew,tvShowMoreSell,tvShowMoreSale,tvShowMoreRecommend;
    CardView cvChildren,cvNovel,cvSchool,cvLiterature,cvTechnology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//        // Add a new document with a generated ID
//        db.collection("BOOK")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

//        // Create a new user with a first, middle, and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);
//
//        // Add a new document with a generated ID
//        db.collection("BOOK")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

    //Read data:
        db.collection("BOOK")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

//        ImageSlider imageSlider = findViewById(R.id.slider);
//        List<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel("https://www.pixelstalk.net/wp-content/uploads/2016/11/Dandelion-Background-Full-HD.jpg","1 Image"));
//        slideModels.add(new SlideModel("https://scr.vn/wp-content/uploads/2020/07/H%C3%ACnh-n%E1%BB%81n-Anime-4k-cho-desktop.jpg","2 Image"));
//        slideModels.add(new SlideModel("http://3.bp.blogspot.com/-Cb0sId5ZlhY/U4RDKxBRFxI/AAAAAAAARvk/-5tjDo7U9FE/s1600/Winter-2.jpg","3 Image"));
//        slideModels.add(new SlideModel("http://1.bp.blogspot.com/-9B2JNcvWR_E/VFbtDZuaCCI/AAAAAAAAG_U/roxyNysD_RI/s1600/phong-canh-3d-13.jpg","4 Image"));
//        slideModels.add(new SlideModel("https://socbay.mobi/publics/uploads/2-2020/post/13053449290220_bo-hinh-nen-desktop-do-phan-giai-cao.jpg","5 Image"));
//        imageSlider.setImageList(slideModels,true);

        tvShowMoreExplore = this.findViewById(R.id.tvShowMoreExplore);
        tvShowMoreNew = this.findViewById(R.id.tvShowMoreNew);
        tvShowMoreSell = this.findViewById(R.id.tvShowMoreSell);
        tvShowMoreSale = this.findViewById(R.id.tvShowMoreSale);
        tvShowMoreRecommend = this.findViewById(R.id.tvShowMoreRecommend);
        cvChildren = this.findViewById(R.id.cvChildren);
        cvNovel = this.findViewById(R.id.cvNovel);
        cvSchool = this.findViewById(R.id.cvSchool);
        cvLiterature = this.findViewById(R.id.cvLiterature);
        cvTechnology = this.findViewById(R.id.cvTechnology);

        tvShowMoreExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        tvShowMoreNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        tvShowMoreSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        tvShowMoreSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        tvShowMoreRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        cvChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        cvNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        cvSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        cvLiterature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        cvTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}