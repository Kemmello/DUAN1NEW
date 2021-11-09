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
import com.example.myapplication.fragment.BookFragment;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = this.findViewById(R.id.drawerLayout_main);
        toolbar = this.findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(navlistener);
        NavigationView navigationView_main = findViewById(R.id.navView_main);
        navigationView_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = new Fragment();
                switch (id) {
                    case R.id.nav_trangchu:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_sach:
                        fragment = new BookFragment();
                        break;
                    case R.id.nav_giohang:
                        fragment = new CartFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame,fragment).commit();
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
                    .add(R.id.frame,fragment).commit();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new BookFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectfrg = null;
            switch (item.getItemId()) {
                case R.id.favorite:
                    selectfrg = new BookFragment();
                    break;
                case R.id.cart:
                    selectfrg = new HomeFragment();
                    break;
                case R.id.user:
                    selectfrg = new CartFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectfrg).commit();
            return true;
        }
    };
}