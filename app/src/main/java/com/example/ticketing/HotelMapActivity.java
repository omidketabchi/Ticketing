package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.FrameLayout;
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
import com.example.ticketing.Fragment.FragmentHotel;
import com.example.ticketing.Fragment.FragmentHotelList;
import com.example.ticketing.Model.HotelModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotelMapActivity extends AppCompatActivity {

    ImageView imgBack;
    FloatingActionButton fab;
    FrameLayout frmForm;
    List<HotelModel> hotelModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_map);

        setupViews();
    }

    private void setupViews() {

        hotelModels = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_fragmentHotel_back);
        frmForm = (FrameLayout) findViewById(R.id.frm_fragmentHotel_frame);
        fab = (FloatingActionButton) findViewById(R.id.fab_fragmentHotel_list);

        String destination = getIntent().getStringExtra("destination");
        String date = getIntent().getStringExtra("date");

        getAllHotelTickets(destination, date);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("hotels", (ArrayList<? extends Parcelable>) hotelModels);

                FragmentHotelList fragmentHotelList = new FragmentHotelList();
                fragmentHotelList.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.add(R.id.frm_fragmentHotel_frame, fragmentHotelList);
                transaction.commit();
            }
        });
    }

    private void getAllHotelTickets(String destination, String date) {

        String url = "https://api.myjson.com/bins/ow3zu";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        HotelModel hotelModel = new HotelModel();

                        hotelModel.setId(jsonObject.getString("id"));
                        hotelModel.setName(jsonObject.getString("name"));
                        hotelModel.setCity(jsonObject.getString("city"));
                        hotelModel.setCity(jsonObject.getString("star"));
                        hotelModel.setCity(jsonObject.getString("bed_count"));
                        hotelModel.setCity(jsonObject.getString("image"));
                        hotelModel.setCity(jsonObject.getString("lat"));
                        hotelModel.setCity(jsonObject.getString("lang"));
                        hotelModel.setCity(jsonObject.getString("price"));

                        hotelModels.add(hotelModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HotelMapActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(HotelMapActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}
