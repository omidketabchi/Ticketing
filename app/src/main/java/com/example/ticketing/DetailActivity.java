package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONArray;

public class DetailActivity extends AppCompatActivity {

    ImageView imgBack;
    ImageView imgIcon;
    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

        getVehicleInfo();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_detail_back);
        imgIcon = (ImageView) findViewById(R.id.img_detail_icon);
        txtSource = (TextView) findViewById(R.id.txt_detail_source);
        txtDestination = (TextView) findViewById(R.id.txt_detail_destination);
        txtDate = (TextView) findViewById(R.id.txt_detail_date);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getVehicleInfo() {

        Bundle bundle = getIntent().getExtras();

        String type = bundle.getString("type");
        String sodurce = bundle.getString("source");
        String destination = bundle.getString("destination");
        String date = bundle.getString("date");

        if (type.equals("flight")) {
            getAllFlightTickets(sodurce, destination, date);
        } else if (type.equals("train")) {
            getAllTrainTickets(sodurce, destination, date);

        } else if (type.equals("bus")) {
            getAllBusTickets(sodurce, destination, date);
        }
    }

    private void getAllFlightTickets(String source, String destination, String date) {

        String url = "https://43aa1bff-3adc-4b2a-b908-617a7afb71b9.mock.pstmn.io/stuff/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void getAllBusTickets(String source, String destination, String date) {

    }

    private void getAllTrainTickets(String source, String destination, String date) {

    }
}
