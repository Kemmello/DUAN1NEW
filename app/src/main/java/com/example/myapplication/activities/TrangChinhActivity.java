package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.fragment.GioHangFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.SachFragment;
import com.example.myapplication.fragment.TrangChuFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangChinhActivity extends AppCompatActivity {

    FrameLayout main_content;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh);
        main_content = this.findViewById(R.id.frameLayout);
        drawerLayout = this.findViewById(R.id.drawerLayout);
        toolbar = this.findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = new Fragment();
                switch (id){
                    case R.id.nav_trangchu:
                        fragment = new TrangChuFragment();
                        break;
                    case R.id.nav_sach:
                        fragment = new SachFragment();
                        break;
                    case R.id.nav_giohang:
                        fragment = new GioHangFragment();
                        break;
//                    case R.id.nav_giohang:
//                        fragment = new ChiFragment();
//                        break;
//                    case R.id.nav_giohang:
//                        fragment = new ChiFragment();
//                        break;
//                    case R.id.nav_giohang:
//                        fragment = new ChiFragment();
//                        break;
//                    case R.id.nav_giohang:
//                        fragment = new ChiFragment();
//                        break;
//                    case R.id.nav_giohang:
//                        fragment = new ChiFragment();
//                        break;
//                    case R.id.nav_giohang:
//                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            HomeFragment fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout,fragment).commit();
        }
    }
}