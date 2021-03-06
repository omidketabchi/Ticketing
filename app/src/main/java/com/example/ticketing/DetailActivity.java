package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.ticketing.Adapter.BusAdapter;
import com.example.ticketing.Adapter.FlightAdapter;
import com.example.ticketing.Adapter.TrainAdapter;
import com.example.ticketing.Model.BusModel;
import com.example.ticketing.Model.ChairModel;
import com.example.ticketing.Model.FlightModel;
import com.example.ticketing.Model.HotelModel;
import com.example.ticketing.Model.TrainModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView imgBack;
    ImageView imgIcon;
    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;
    RecyclerView recyclerView;

    List<FlightModel> flightModels;
    List<TrainModel> trainModels;
    List<BusModel> busModels;
    List<HotelModel> hotelModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

        getVehicleInfo();
    }

    private void setupViews() {

        flightModels = new ArrayList<>();
        trainModels = new ArrayList<>();
        busModels = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_detail_back);
        imgIcon = (ImageView) findViewById(R.id.img_detail_icon);
        txtSource = (TextView) findViewById(R.id.txt_detail_source);
        txtDestination = (TextView) findViewById(R.id.txt_detail_destination);
        txtDate = (TextView) findViewById(R.id.txt_detail_date);
        recyclerView = (RecyclerView) findViewById(R.id.rv_detail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        String source = bundle.getString("source");
        String destination = bundle.getString("destination");
        String date = bundle.getString("date");

        txtSource.setText(source);
        txtDestination.setText(destination);
        txtDate.setText(date);

        if (type.equals("insideFlight")) {
            imgIcon.setImageResource(R.drawable.ic_airplane_white_24dp);
            getAllInsideFlightTickets(source, destination, date);
        } else if (type.equals("outsideFlight")) {
            getAllOutsideFlightTickets(source, destination, date);
        } else if (type.equals("train")) {
            imgIcon.setImageResource(R.drawable.ic_train_white_24dp);
            getAllTrainTickets(source, destination, date);

        } else if (type.equals("bus")) {
            imgIcon.setImageResource(R.drawable.ic_directions_bus_white_24dp);
            getAllBusTickets(source, destination, date);
        }
    }

    private void getAllInsideFlightTickets(String source, String destination, String date) {

        String url = "https://b6f0fe80-c7b8-467c-a508-1de494258d2a.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseFlightResponse(response);
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

    private void getAllOutsideFlightTickets(String source, String destination, String date) {

        String url = "https://f1f1aa4d-f6cd-42fa-b9d8-dabe45cf7c7b.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseFlightResponse(response);
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
        String url = "https://ff6b20d9-f63c-4c39-9cdc-7e678c46fa48.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseBusTicketResponse(response);
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

    private void getAllTrainTickets(String source, String destination, String date) {

        String url = "https://1fc12221-ab17-4437-a934-a40d76961f01.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                parseTrainTicketResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void parseFlightResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {

            try {

                String firstKind = "";
                String secondKind = "";

                FlightModel flightModel = new FlightModel();

                JSONObject jsonObject = response.getJSONObject(i);

                flightModel.setId(jsonObject.getString("id"));
                flightModel.setSource(jsonObject.getString("source"));
                flightModel.setDestination(jsonObject.getString("destination"));
                flightModel.setSourceAirport(jsonObject.getString("source_airport"));
                flightModel.setDestinationAirport(jsonObject.getString("destination_airport"));
                flightModel.setDate(jsonObject.getString("date"));
                flightModel.setType(jsonObject.getString("type"));

                String[] kind = jsonObject.getString("kind").split("/");
                flightModel.setFirstKind(kind[0]);
                flightModel.setSecondKind(kind[1]);

                flightModel.setCompany(jsonObject.getString("company"));
                flightModel.setFlightTime(jsonObject.getString("flight_time"));
                flightModel.setLandTime(jsonObject.getString("land_time"));
                flightModel.setCapacity(jsonObject.getString("capacity"));
                flightModel.setFlightId(jsonObject.getString("flight_id"));
                flightModel.setPriceYoung(jsonObject.getString("price_young"));
                flightModel.setPriceChild(jsonObject.getString("price_child"));
                flightModel.setPriceBaby(jsonObject.getString("price_baby"));

                flightModels.add(flightModel);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(new FlightAdapter(DetailActivity.this, flightModels));
    }

    private void parseTrainTicketResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {
            try {

                TrainModel model = new TrainModel();

                JSONObject jsonObject = response.getJSONObject(i);

                model.setId(jsonObject.getString("id"));
                model.setTrainId(jsonObject.getString("train_id"));
                model.setSource(jsonObject.getString("source"));
                model.setDestination(jsonObject.getString("destination"));
                model.setStartTime(jsonObject.getString("start_time"));
                model.setEndTime(jsonObject.getString("end_time"));
                model.setDate(jsonObject.getString("date"));
                model.setType(jsonObject.getString("type"));
                model.setCapacity(jsonObject.getString("capacity"));
                model.setCoupeCapacity(jsonObject.getString("coupe_capacity"));
                model.setPrice(jsonObject.getString("price"));

                trainModels.add(model);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(new TrainAdapter(DetailActivity.this, trainModels));
    }

    private void parseBusTicketResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {

            try {

                JSONObject jsonObject = response.getJSONObject(i);

                BusModel busModel = new BusModel();

                busModel.setId(jsonObject.getString("id"));
                busModel.setTicketId(jsonObject.getString("ticket_id"));
                busModel.setSource(jsonObject.getString("source"));
                busModel.setDestination(jsonObject.getString("destination"));
                busModel.setSourceTerminal(jsonObject.getString("sourceTerminal"));
                busModel.setDestinationTerminal(jsonObject.getString("destinationTerminal"));
                busModel.setDate(jsonObject.getString("date"));
                busModel.setTime(jsonObject.getString("time"));
                busModel.setType(jsonObject.getString("type"));
                busModel.setDistance(jsonObject.getString("distance"));
                busModel.setCapacity(jsonObject.getString("capacity"));
                busModel.setPrice(jsonObject.getString("price"));

                JSONArray jsonArray = jsonObject.getJSONArray("chairs");
                List<ChairModel> chairModels = new ArrayList<>();

                for (int j = 0; j < jsonArray.length(); j++) {

                    ChairModel chairModel = new ChairModel();

                    JSONObject chairs = jsonArray.getJSONObject(j);

                    chairModel.setChair(chairs.getString("chair"));
                    chairModel.setStatus(chairs.getString("status"));

                    chairModels.add(chairModel);
                }

                busModel.setChairModel(chairModels);
                busModels.add(busModel);
                int k = 10;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(new BusAdapter(DetailActivity.this, busModels));
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
                Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
