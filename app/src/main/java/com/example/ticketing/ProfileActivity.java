package com.example.ticketing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ticketing.Adapter.ProfileAdapter;
import com.example.ticketing.Model.ProfileModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 758;

    ImageView imgBack;
    ImageView imgExit;
    Button btnCredit;
    Button btnChangePin;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<ProfileModel> profiles;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupViews();
    }

    private void setupViews() {

        profiles = new ArrayList<>();
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        imgBack = (ImageView) findViewById(R.id.img_profile_back);
        imgExit = (ImageView) findViewById(R.id.img_profile_exit);
        btnChangePin = (Button) findViewById(R.id.btn_profile_changePin);
        btnCredit = (Button) findViewById(R.id.btn_profile_credit);
        fab = (FloatingActionButton) findViewById(R.id.fab_profile_fab);

        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", "");
                editor.apply();

                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivityForResult(intent, REQUEST_EDIT_PROFILE);
            }
        });

        createList();
    }

    private void createList() {

        String balance = sharedPreferences.getString("balance", "");
        String email = sharedPreferences.getString("email", "");
        String phoneNo = sharedPreferences.getString("phoneNo", "");
        String manOrWoman = sharedPreferences.getString("manOrWoman", "");
        String code = sharedPreferences.getString("code", "");
        String birthDate = sharedPreferences.getString("birthDate", "");

        ProfileModel balanceEntity = new ProfileModel();
        balanceEntity.setTitle("موجودی:");
        balanceEntity.setValue(balance + " ریال");
        profiles.add(balanceEntity);

        ProfileModel emailEntity = new ProfileModel();
        emailEntity.setTitle("ایمیل:");
        emailEntity.setValue(email);
        profiles.add(emailEntity);

        ProfileModel phoneEntity = new ProfileModel();
        phoneEntity.setTitle("شماره همراه:");
        phoneEntity.setValue(phoneNo);
        profiles.add(phoneEntity);

        ProfileModel manOrWomanEntity = new ProfileModel();
        manOrWomanEntity.setTitle("جنسیت:");
        manOrWomanEntity.setValue(manOrWoman);
        profiles.add(manOrWomanEntity);

        ProfileModel codeEntity = new ProfileModel();
        codeEntity.setTitle("کد ملی:");
        codeEntity.setValue(code);
        profiles.add(codeEntity);

        ProfileModel birthDateEntity = new ProfileModel();
        birthDateEntity.setTitle("تاریخ تولد:");
        birthDateEntity.setValue(birthDate);
        profiles.add(birthDateEntity);

        recyclerView.setAdapter(new ProfileAdapter(ProfileActivity.this, profiles));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {

            profiles = data.getParcelableArrayListExtra("info");
        }
    }
}
