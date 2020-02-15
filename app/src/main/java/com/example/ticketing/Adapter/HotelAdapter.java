package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.HotelModel;
import com.example.ticketing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    Context context;
    List<HotelModel> hotelModels;

    public HotelAdapter(Context context, List<HotelModel> urls) {
        this.context = context;
        this.hotelModels = urls;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.hotel_item, viewGroup, false);

        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        HotelModel model = hotelModels.get(position);

        holder.txtName.setText(model.getName());
        holder.txtPrice.setText(model.getPrice());
        holder.ratingBar.setRating(Float.parseFloat(model.getStar()));
        Picasso.with(context).load(model.getImage()).into(holder.imgHotel);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHotel;
        TextView txtName;
        TextView txtPrice;
        CardView parent;
        AppCompatRatingBar ratingBar;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_hotelItem_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_hotelItem_price);
            ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rt_hotelItem_ratingBar);
            imgHotel = (ImageView) itemView.findViewById(R.id.img_hotelItem_image);
            parent = (CardView) itemView.findViewById(R.id.cv_hotelItem_parent);
        }
    }
}
