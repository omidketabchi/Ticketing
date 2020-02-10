package com.example.ticketing.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ticketing.CitiesActivity;
import com.example.ticketing.DetailActivity;
import com.example.ticketing.FlightInformationActivity;
import com.example.ticketing.R;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class FragmentInsideFlight extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private static final int REQUEST_ADD_SOURCE_CITY = 516;
    private static final int REQUEST_ADD_DESTINATION_CITY = 517;

    View view;
    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;
    Button btnSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_inside_flight, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        txtSource = (TextView) view.findViewById(R.id.txt_fragmentInsideFlight_source);
        txtDestination = (TextView) view.findViewById(R.id.txt_fragmentInsideFlight_destination);
        txtDate = (TextView) view.findViewById(R.id.txt_fragmentInsideFlight_date);
        btnSearch = (Button) view.findViewById(R.id.btn_fragmentInsideFlight_search);

        txtSource.setOnClickListener(this::onClick);
        txtDestination.setOnClickListener(this::onClick);
        txtDate.setOnClickListener(this::onClick);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        txtDate.setText(String.format("%04d", year) + "/" +
                String.format("%02d", month + 1) + "/" +
                String.format("%02d", dayOfMonth));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txt_fragmentInsideFlight_source:
                getCities(REQUEST_ADD_SOURCE_CITY);
                break;
            case R.id.txt_fragmentInsideFlight_destination:
                getCities(REQUEST_ADD_DESTINATION_CITY);
                break;
            case R.id.txt_fragmentInsideFlight_date:
                getDate();
                break;
            case R.id.btn_fragmentInsideFlight_search:
                showAllCities();
                break;
        }
    }

    private void showAllCities() {

        Intent intent = new Intent(getContext(), DetailActivity.class);

        intent.putExtra("type", "flight");
        intent.putExtra("source", txtSource.getText().toString());
        intent.putExtra("destination", txtDestination.getText().toString());
        intent.putExtra("date", txtDate.getText().toString());

        getContext().startActivity(intent);
    }

    private void getCities(int requestCode) {

        Intent intent = new Intent(getContext(), CitiesActivity.class);
        startActivityForResult(intent, requestCode);
    }

    private void getDate() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                this::onDateSet,
                year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ADD_SOURCE_CITY && resultCode == RESULT_OK && data != null) {
            txtSource.setText(data.getExtras().getString("city"));
        } else if (requestCode == REQUEST_ADD_DESTINATION_CITY && resultCode == RESULT_OK && data != null) {
            txtDestination.setText(data.getExtras().getString("city"));
        }
    }
}
