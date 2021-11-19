package com.example.myapplication.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.BillFragment;
import com.example.myapplication.fragment.BookFragment;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.ChangePasswordFragment;
import com.example.myapplication.fragment.ContactFragment;
import com.example.myapplication.fragment.ExitFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.RevenueFragment;
import com.example.myapplication.fragment.SearchFragment;
import com.example.myapplication.fragment.TopFragment;
import com.example.myapplication.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title;
    ImageView ivsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = this.findViewById(R.id.drawerLayout_main);
        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_nav);
        title = findViewById(R.id.title);
        ivsearch = findViewById(R.id.ivsearch);
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
                        title.setText("BOOK STORE");
                        break;
                    case R.id.nav_sach:
                        fragment = new BookFragment();
                        title.setText("ALL OF BOOK");
                        break;
                    case R.id.nav_doanhthu:
                        fragment = new RevenueFragment();
                        title.setText("REVENUE");
                        break;
                    case R.id.nav_topsach:
                        fragment = new TopFragment();
                        title.setText("TOP BOOK");
                        break;
                    case R.id.nav_lienhe:
                        fragment = new ContactFragment();
                        title.setText("CONTACT US");
                        break;
                    case R.id.nav_doimatkhau:
                        fragment = new ChangePasswordFragment();
                        title.setText("CHANGE PASSWORD");
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
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

        ivsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                fragment = new SearchFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame,fragment).commit();

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectfrg = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selectfrg = new HomeFragment();
                    break;
                case R.id.cart:
                    selectfrg = new CartFragment();
                    title.setText("CART");
                    break;
                case R.id.user:
                    selectfrg = new UserFragment();
                    title.setText("USER");
                    break;
                case R.id.list_bottom:
                    selectfrg = new BillFragment();
                    title.setText("BILL");
                    break;
                case R.id.exit:
                    selectfrg = new ExitFragment();
                    title.setText("EXIT");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectfrg).commit();
            return true;
        }
    };



}