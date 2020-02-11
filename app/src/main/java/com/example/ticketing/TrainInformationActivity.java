package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ticketing.Model.TrainModel;

public class TrainInformationActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtPath;
    TextView txtDate;
    TextView txtTrainNo;
    TextView txtTrainType;
    TextView txtCoupeCapacity;
    TextView txtPrice;
    Button btnBuyTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_information);

        setupViews();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_trainInfo_back);
        txtPath = (TextView) findViewById(R.id.txt_trainInfo_source);
        txtDate = (TextView) findViewById(R.id.txt_trainInfo_date);
        txtTrainNo = (TextView) findViewById(R.id.txt_trainInfo_trainNoValue);
        txtTrainType = (TextView) findViewById(R.id.txt_trainInfo_trainTypeValue);
        txtCoupeCapacity = (TextView) findViewById(R.id.txt_trainInfo_trainCoupeCapacityValue);
        txtPrice = (TextView) findViewById(R.id.txt_trainInfo_trainPriceValue);
        btnBuyTicket = (Button) findViewById(R.id.btn_trainInfo_buyTicket);

        TrainModel model = getIntent().getParcelableExtra("model");

        txtPath.setText(model.getSource() + " - " + model.getDestination());
        txtDate.setText(model.getDate());
        txtCoupeCapacity.setText(model.getCoupeCapacity());
        txtTrainNo.setText(model.getTrainId());
        txtTrainType.setText(model.getType());
        txtPrice.setText(model.getPrice());

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
