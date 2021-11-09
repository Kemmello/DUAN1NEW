package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.activities.SignUpActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvShowMoreExplore,tvShowMoreNew,tvShowMoreSell,tvShowMoreSale,tvShowMoreRecommend;
    CardView cvChildren,cvNovel,cvSchool,cvLiterature,cvTechnology;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//                ImageSlider imageSlider = findViewById(R.id.slider);
//        List<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel("https://www.pixelstalk.net/wp-content/uploads/2016/11/Dandelion-Background-Full-HD.jpg","1 Image"));
//        slideModels.add(new SlideModel("https://scr.vn/wp-content/uploads/2020/07/H%C3%ACnh-n%E1%BB%81n-Anime-4k-cho-desktop.jpg","2 Image"));
//        slideModels.add(new SlideModel("http://3.bp.blogspot.com/-Cb0sId5ZlhY/U4RDKxBRFxI/AAAAAAAARvk/-5tjDo7U9FE/s1600/Winter-2.jpg","3 Image"));
//        slideModels.add(new SlideModel("http://1.bp.blogspot.com/-9B2JNcvWR_E/VFbtDZuaCCI/AAAAAAAAG_U/roxyNysD_RI/s1600/phong-canh-3d-13.jpg","4 Image"));
//        slideModels.add(new SlideModel("https://socbay.mobi/publics/uploads/2-2020/post/13053449290220_bo-hinh-nen-desktop-do-phan-giai-cao.jpg","5 Image"));
//        imageSlider.setImageList(slideModels,true);

        tvShowMoreExplore = view.findViewById(R.id.tvShowMoreExplore);
        tvShowMoreNew = view.findViewById(R.id.tvShowMoreNew);
        tvShowMoreSell = view.findViewById(R.id.tvShowMoreSell);
        tvShowMoreSale = view.findViewById(R.id.tvShowMoreSale);
        tvShowMoreRecommend = view.findViewById(R.id.tvShowMoreRecommend);
        cvChildren = view.findViewById(R.id.cvChildren);
        cvNovel = view.findViewById(R.id.cvNovel);
        cvSchool = view.findViewById(R.id.cvSchool);
        cvLiterature = view.findViewById(R.id.cvLiterature);
        cvTechnology = view.findViewById(R.id.cvTechnology);

        tvShowMoreExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvLiterature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}