package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ticketing.Model.BusModel;
import com.example.ticketing.Model.ChairModel;

import java.util.ArrayList;
import java.util.List;

public class BusInformationActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtTitle;
    TextView txtDate;
    TextView txtSourceTerminal;
    TextView txtType;
    TextView txtTime;
    TextView txtPrice;
    TextView txtCityDestinationTerminal;
    TextView txtCitySourceTerminal;
    TextView txtDistance;
    RecyclerView recyclerView;
    Button btnChairSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_information);

        setupViews();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_busInfo_back);
        txtTitle = (TextView) findViewById(R.id.txt_busInfo_source);
        txtDate = (TextView) findViewById(R.id.txt_busInfo_date);
        txtSourceTerminal = (TextView) findViewById(R.id.txt_busInfo_sourceTerminal);
        txtType = (TextView) findViewById(R.id.txt_busInfo_typeValue);
        txtTime = (TextView) findViewById(R.id.txt_busInfo_timeValue);
        txtPrice = (TextView) findViewById(R.id.txt_busInfo_priceValue);
        txtCityDestinationTerminal = (TextView) findViewById(R.id.txt_busInfo_cityDestinationTerminal);
        txtCitySourceTerminal = (TextView) findViewById(R.id.txt_busInfo_citySourceTerminal);
        txtDistance = (TextView) findViewById(R.id.txt_busInfo_distance);
        recyclerView = (RecyclerView) findViewById(R.id.rv_busInfo_penaltyList);
        btnChairSelection = (Button) findViewById(R.id.btn_busInfo_buyTicket);

        BusModel model = getIntent().getExtras().getParcelable("model");
        List<ChairModel> chairModels = getIntent().getParcelableArrayListExtra("chair");

        txtTitle.setText(model.getSource() + " - " + model.getDestination());
        txtDate.setText(model.getDate());
        txtSourceTerminal.setText(model.getSourceTerminal());
        txtType.setText(model.getType());
        txtTime.setText(model.getTime());
        txtPrice.setText(model.getPrice());
        txtCityDestinationTerminal.setText(model.getDestinationTerminal() + " - " + model.getDestination());
        txtCitySourceTerminal.setText(model.getSourceTerminal() + " - " + model.getSource());
        txtDistance.setText(model.getDistance());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChairSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BusInformationActivity.this, ChairSelectionActivity.class);
                intent.putParcelableArrayListExtra("chair", (ArrayList<? extends Parcelable>) chairModels);
                intent.putExtra("price", model.getPrice());
                startActivity(intent);
            }
        });
    }
}
