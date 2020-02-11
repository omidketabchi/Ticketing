package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ticketing.Adapter.PenaltyAdapter;
import com.example.ticketing.Model.FlightModel;
import com.example.ticketing.Model.PenaltyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    List<PenaltyModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_information);

        setupViews();

        getPenaltyRules();
    }

    private void setupViews() {

        models = new ArrayList<>();

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FlightModel model = getIntent().getParcelableExtra("model");

        txtDate.setText(model.getDate());
        txtTime.setText(model.getFlightTime());
        txtDestination.setText(model.getDestinationAirport() + "-" + model.getDestination());
        txtSource.setText(model.getSourceAirport() + "-" + model.getSource());
        txtFlightNo.setText(model.getFlightId());
        txtType.setText(model.getType());
        txtClass.setText(model.getSecondKind());
        txtValidLoad.setText("نامشخص");
        txtYoungPrice.setText(model.getPriceYoung());
        txtChildPrice.setText(model.getPriceChild());
        txtBabyPrice.setText(model.getPriceBaby());
    }

    private void getPenaltyRules() {

        String url = "https://api.myjson.com/bins/d9gp6";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        PenaltyModel model = new PenaltyModel();

                        model.setRule(jsonObject.getString("rule_title"));
                        model.setPercent(jsonObject.getString("penalty_percentage"));

                        models.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setAdapter(new PenaltyAdapter(FlightInformationActivity.this, models));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FlightInformationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES
                , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(FlightInformationActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}
