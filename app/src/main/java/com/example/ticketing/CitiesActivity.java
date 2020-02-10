package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ticketing.Adapter.CityAdapter;
import com.example.ticketing.Model.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    ImageView imgBack;
    RecyclerView recyclerView;
    CityAdapter cityAdapter;
    List<CityModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        setupViews();

        getCityList();
    }

    private void setupViews() {

        models = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_cities_back);
        recyclerView = (RecyclerView) findViewById(R.id.rv_cities_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getCityList() {

        String url = "https://api.myjson.com/bins/193ek2";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        CityModel model = new CityModel();

                        model.setId(jsonObject.getString("id"));
                        model.setCity(jsonObject.getString("city"));

                        models.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                cityAdapter = new CityAdapter(CitiesActivity.this, models);
                recyclerView.setAdapter(cityAdapter);

                cityAdapter.setOnCityItemClicked(new CityAdapter.OnCityItemClicked() {
                    @Override
                    public void onCityClicked(String city) {
                        Intent intent = new Intent();
                        intent.putExtra("city", city);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CitiesActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(CitiesActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}
