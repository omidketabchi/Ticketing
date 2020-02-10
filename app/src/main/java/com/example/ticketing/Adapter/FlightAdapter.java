package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.FlightModel;
import com.example.ticketing.R;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    Context context;
    List<FlightModel> models;

    public FlightAdapter(Context context, List<FlightModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.flight_item, viewGroup, false);

        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder {

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
