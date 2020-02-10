package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.CityModel;
import com.example.ticketing.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    Context context;
    List<CityModel> models;
    OnCityItemClicked onCityItemClicked;

    public CityAdapter(Context context, List<CityModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.city_item, viewGroup, false);

        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {

        CityModel model = models.get(position);

        holder.txtCity.setText(model.getCity());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCityItemClicked.onCityClicked(model.getCity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        TextView txtCity;
        CardView parent;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCity = (TextView) itemView.findViewById(R.id.img_city_item_cityTitle);
            parent = (CardView) itemView.findViewById(R.id.cv_city_item_parent);
        }
    }

    public interface OnCityItemClicked {
        void onCityClicked(String city);
    }

    public void setOnCityItemClicked(OnCityItemClicked onCityItemClicked) {
        this.onCityItemClicked = onCityItemClicked;
    }
}
