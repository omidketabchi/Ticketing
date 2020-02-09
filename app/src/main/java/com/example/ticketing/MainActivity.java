package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ticketing.Adapter.NavigationAdapter;
import com.example.ticketing.Model.NavigationModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtEmail;
    RecyclerView recyclerView;
    Button btnSupport;

    List<NavigationModel> navigationModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        setMenu();
    }

    private void setupViews() {

        navigationModels = new ArrayList<>();

        btnSupport = (Button) findViewById(R.id.btn_navigation_support);
        recyclerView = (RecyclerView) findViewById(R.id.rv_navigation_recyclerView);
        txtEmail = (TextView) findViewById(R.id.txt_navigation_email);
        viewPager = (ViewPager) findViewById(R.id.vp_tabLayout_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout_tab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setMenu() {

        NavigationModel navigationModel1 = new NavigationModel();
        navigationModel1.setTitle("پروفایل کاربری");
        navigationModel1.setIcon(R.drawable.ic_person_black_24dp);
        navigationModels.add(0, navigationModel1);

        NavigationModel navigationModel2 = new NavigationModel();
        navigationModel2.setTitle("لیست مسافران");
        navigationModel2.setIcon(R.drawable.ic_supervisor_account_black_24dp);
        navigationModels.add(1, navigationModel2);


        NavigationModel navigationModel3 = new NavigationModel();
        navigationModel3.setTitle("سوابق تراکنش");
        navigationModel3.setIcon(R.drawable.ic_monetization_on_black_24dp);
        navigationModels.add(2, navigationModel3);

        NavigationModel navigationModel4 = new NavigationModel();
        navigationModel4.setTitle("خرید های من");
        navigationModel4.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        navigationModels.add(3, navigationModel4);

        NavigationModel navigationModel5 = new NavigationModel();
        navigationModel5.setTitle("خروج از حساب کاربری");
        navigationModel5.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        navigationModels.add(4, navigationModel5);

        recyclerView.setAdapter(new NavigationAdapter(MainActivity.this, navigationModels));
    }
}
