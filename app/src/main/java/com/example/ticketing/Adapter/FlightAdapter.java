package com.example.ticketing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.FlightInformationActivity;
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

        FlightModel model = models.get(position);

        holder.txtName.setText(model.getCompany());
        holder.txtFirstKind.setText(model.getCompany());
        holder.txtSecondKind.setText(model.getCompany());
        holder.txtCapacity.setText(model.getCompany());
        holder.txtPrice.setText(model.getCompany());
        holder.txtTime.setText(model.getCompany());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, FlightInformationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("model", model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtFirstKind;
        TextView txtSecondKind;
        TextView txtTime;
        TextView txtCapacity;
        TextView txtPrice;
        CardView parent;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = (CardView) itemView.findViewById(R.id.cv_flightItem_parent);
            txtName = (TextView) itemView.findViewById(R.id.txt_flightItem_name);
            txtFirstKind = (TextView) itemView.findViewById(R.id.txt_flightItem_firstKind);
            txtSecondKind = (TextView) itemView.findViewById(R.id.txt_flightItem_secondKind);
            txtTime = (TextView) itemView.findViewById(R.id.txt_flightItem_time);
            txtCapacity = (TextView) itemView.findViewById(R.id.txt_flightItem_capacity);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_flightItem_price);
        }
    }
}
