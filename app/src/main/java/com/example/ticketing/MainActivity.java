package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticketing.Adapter.NavigationAdapter;
import com.example.ticketing.Adapter.ViewPagerAdapter;
import com.example.ticketing.Fragment.FragmentBus;
import com.example.ticketing.Fragment.FragmentHotel;
import com.example.ticketing.Fragment.FragmentInsideFlight;
import com.example.ticketing.Fragment.FragmentOutsideFlight;
import com.example.ticketing.Fragment.FragmentTrain;
import com.example.ticketing.Model.NavigationModel;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String email = "";
    String password = "";

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtEmail;
    ImageView imgMenu;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    Button btnSupport;
    NavigationAdapter navigationAdapter;

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
        imgMenu = (ImageView) findViewById(R.id.img_main_hamberMenu);
        tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout_tab);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawerLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new FragmentHotel(), "هتل");
        pagerAdapter.addFragment(new FragmentBus(), "اتوبوس");
        pagerAdapter.addFragment(new FragmentTrain(), "قطار");
        pagerAdapter.addFragment(new FragmentOutsideFlight(), "پرواز خاجی");
        pagerAdapter.addFragment(new FragmentInsideFlight(), "پرواز داخلی");

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(4);
        tabLayout.setupWithViewPager(viewPager);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "تماس با پشتیبانی", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMenu() {

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        txtEmail.setText(sharedPreferences.getString("email", ""));

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

        navigationAdapter = new NavigationAdapter(MainActivity.this, navigationModels);

        recyclerView.setAdapter(navigationAdapter);

        navigationAdapter.setOnNavigationReceiveDataSuccess(new NavigationAdapter.OnNavigationReceiveDataSuccess() {
            @Override
            public void navigationReceiveDataSuccess(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        email = jsonObject.getString("email");
                        password = jsonObject.getString("password");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                txtEmail.setText(email + "/" + password);

                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }
}
