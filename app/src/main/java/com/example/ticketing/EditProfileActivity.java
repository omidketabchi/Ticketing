package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ticketing.Model.ProfileModel;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText edtName;
    EditText edtFamily;
    EditText edtCode;
    EditText edtPhone;
    TextView txtBirthDateTitle;
    TextView txtBirthDateValue;
    Button btnSubmit;
    AppCompatSpinner spinner;
    List<ProfileModel> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupViews();

    }

    private void setupViews() {

        profiles = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_editProfile_back);
        edtName = (EditText) findViewById(R.id.edt_editProfile_name);
        edtFamily = (EditText) findViewById(R.id.edt_editProfile_family);
        edtCode = (EditText) findViewById(R.id.edt_editProfile_code);
        edtPhone = (EditText) findViewById(R.id.edt_editProfile_phone);
        txtBirthDateTitle = (TextView) findViewById(R.id.txt_editProfile_birthDateTitle);
        txtBirthDateValue = (TextView) findViewById(R.id.txt_editProfile_birthDateValue);
        btnSubmit = (Button) findViewById(R.id.btn_editProfile_submit);
        spinner = (AppCompatSpinner) findViewById(R.id.sp_editProfile_manOrWoman);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putString("name", edtName.getText().toString());
//                editor.putString("family", edtFamily.getText().toString());
//                editor.putString("manOrWoman", spinner.getSelectedItem().toString());
//                editor.putString("birthDate", txtBirthDateValue.getText().toString());
//                editor.putString("code", edtCode.getText().toString());
//                editor.putString("phone", edtPhone.getText().toString());

                Intent intent = new Intent();

                ProfileModel phoneEntity = new ProfileModel();
                phoneEntity.setTitle("شماره همراه:");
                phoneEntity.setValue(edtPhone.getText().toString());
                profiles.add(phoneEntity);

                ProfileModel manOrWomanEntity = new ProfileModel();
                manOrWomanEntity.setTitle("جنسیت:");
                manOrWomanEntity.setValue(spinner.getSelectedItem().toString());
                profiles.add(manOrWomanEntity);

                ProfileModel codeEntity = new ProfileModel();
                codeEntity.setTitle("کد ملی:");
                codeEntity.setValue(edtCode.getText().toString());
                profiles.add(codeEntity);

                ProfileModel birthDateEntity = new ProfileModel();
                birthDateEntity.setTitle("تاریخ تولد:");
                birthDateEntity.setValue(txtBirthDateValue.getText().toString());
                profiles.add(birthDateEntity);

                intent.putParcelableArrayListExtra("info", (ArrayList<? extends Parcelable>) profiles);

                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
