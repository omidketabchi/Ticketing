package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FlightInformationActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtDate;
    TextView txtTime;
    TextView txtSource;
    TextView txtDestination;
    TextView txtFlightNo;
    TextView txtType;
    TextView txtClass;
    TextView txtValidLoad;
    TextView txtYoungPrice;
    TextView txtChildPrice;
    TextView txtBabyPrice;
    Button btnTicketSelect;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_information);

        setupViews();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_flightInformation_back);
        txtDate = (TextView) findViewById(R.id.txt_flightInformation_date);
        txtTime = (TextView) findViewById(R.id.txt_flightInformation_time);
        txtSource = (TextView) findViewById(R.id.txt_flightInformation_source);
        txtDestination = (TextView) findViewById(R.id.txt_flightInformation_destination);
        txtFlightNo = (TextView) findViewById(R.id.txt_flightInformation_flightNoValue);
        txtType = (TextView) findViewById(R.id.txt_flightInformation_typeValue);
        txtClass = (TextView) findViewById(R.id.txt_flightInformation_classValue);
        txtValidLoad = (TextView) findViewById(R.id.txt_flightInformation_loadValue);
        txtYoungPrice = (TextView) findViewById(R.id.txt_flightInformation_youngValue);
        txtChildPrice = (TextView) findViewById(R.id.txt_flightInformation_childValue);
        txtBabyPrice = (TextView) findViewById(R.id.txt_flightInformation_babyValue);
        btnTicketSelect = (Button) findViewById(R.id.btn_flightInformation_choose_ticket);
        recyclerView = (RecyclerView) findViewById(R.id.rv_flightInformation_penaltyList);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
