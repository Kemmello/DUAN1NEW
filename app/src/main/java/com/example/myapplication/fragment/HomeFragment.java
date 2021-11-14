package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.TypeActivity;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.adapter.SliderAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.SliderItem;
import com.example.myapplication.activities.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView tvShowMoreNew, tvShowMoreSell, tvShowMoreSale, tvShowMoreRecommend;
    CardView cvChildren, cvNovel, cvSchool, cvLiterature, cvTechnology,cvPolitics,cvHistory;

    ProgressBar progressBar;
    ScrollView scvHome;

    //List
    FirebaseFirestore firestore;
    RecyclerView recyclerNewBook , recyclerViewTopSell , recyclerViewSale , recyclerViewRecommend;
    List<Book> newlist;
    List<Book> selllist;
    List<Book> salelist;
    List<Book> recommendlist;
    BookAdapter bookAdapter , sellAdapter , saleAdapter , recommendAdapter;

    //Slide
    ViewPager2 viewPager2;
    Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        firestore = FirebaseFirestore.getInstance();
        recyclerNewBook = root.findViewById(R.id.recyclerNewBook);
        recyclerViewTopSell = root.findViewById(R.id.recyclerViewTopSell);
        recyclerViewSale = root.findViewById(R.id.recyclerViewSale);
        recyclerViewRecommend = root.findViewById(R.id.recyclerViewRecommend);

        scvHome = root.findViewById(R.id.scvHome);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scvHome.setVisibility(View.GONE);

        recyclerNewBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewTopSell.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewSale.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        newlist = new ArrayList<>();
        selllist = new ArrayList<>();
        salelist = new ArrayList<>();
        recommendlist = new ArrayList<>();

        bookAdapter = new BookAdapter(getActivity(), newlist);
        sellAdapter = new BookAdapter(getActivity(), selllist);
        saleAdapter = new BookAdapter(getActivity(), salelist);
        recommendAdapter = new BookAdapter(getActivity(), recommendlist);

        recyclerNewBook.setAdapter(bookAdapter);
        recyclerViewTopSell.setAdapter(sellAdapter);
        recyclerViewSale.setAdapter(saleAdapter);
        recyclerViewRecommend.setAdapter(recommendAdapter);

        firestore.collection("NEWBOOK")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = document.toObject(Book.class);
                                newlist.add(book);
                                bookAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scvHome.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        firestore.collection("TOPSELL")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = document.toObject(Book.class);
                                selllist.add(book);
                                sellAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        firestore.collection("BOOKSALE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = document.toObject(Book.class);
                                salelist.add(book);
                                saleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        firestore.collection("RECOMMEND")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = document.toObject(Book.class);
                                recommendlist.add(book);
                                recommendAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvShowMoreNew = view.findViewById(R.id.tvShowMoreNew);
        tvShowMoreSell = view.findViewById(R.id.tvShowMoreSell);
        tvShowMoreSale = view.findViewById(R.id.tvShowMoreSale);
        tvShowMoreRecommend = view.findViewById(R.id.tvShowMoreRecommend);
        cvChildren = view.findViewById(R.id.cvChildren);
        cvNovel = view.findViewById(R.id.cvNovel);
        cvSchool = view.findViewById(R.id.cvSchool);
        cvLiterature = view.findViewById(R.id.cvLiterature);
        cvTechnology = view.findViewById(R.id.cvTechnology);
        cvPolitics = view.findViewById(R.id.cvPolitics);
        cvHistory = view.findViewById(R.id.cvHistory);

        tvShowMoreNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        tvShowMoreSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        tvShowMoreSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        tvShowMoreRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách trẻ em");
                startActivity(intent);
            }
        });
        cvNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Tiểu thuyết");
                startActivity(intent);
            }
        });
        cvSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách giáo khoa");
                startActivity(intent);
            }
        });
        cvLiterature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách Văn Học - Nghệ Thuật");
                startActivity(intent);
            }
        });
        cvTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách Khoa học - Công nghệ");
                startActivity(intent);
            }
        });
        cvPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách Chính trị - Xã hội");
                startActivity(intent);
            }
        });
        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("type", "Sách lịch sử");
                startActivity(intent);
            }
        });

        viewPager2 = view.findViewById(R.id.ViewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.a));
        sliderItems.add(new SliderItem(R.drawable.a1));
        sliderItems.add(new SliderItem(R.drawable.a2));
        sliderItems.add(new SliderItem(R.drawable.a3));
        sliderItems.add(new SliderItem(R.drawable.a4));
        sliderItems.add(new SliderItem(R.drawable.a5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 4000);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 4000);
    }
}