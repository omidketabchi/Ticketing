package com.example.ticketing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.BusInformationActivity;
import com.example.ticketing.Model.BusModel;
import com.example.ticketing.R;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {

    Context context;
    List<BusModel> models;

    public BusAdapter(Context context, List<BusModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.bus_item, viewGroup, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {

        BusModel model = models.get(position);

        holder.txtCompany.setText(model.getType());
        holder.txtCapacity.setText(model.getCapacity());
        holder.txtDestinationTerminal.setText(model.getDestinationTerminal());
        holder.txtSourceTerminal.setText(model.getSourceTerminal());
        holder.txtPrice.setText(model.getPrice());
        holder.txtSource.setText(model.getSource());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BusInformationActivity.class);

                intent.putExtra("model", model);
                intent.putParcelableArrayListExtra("chair", (ArrayList<? extends Parcelable>) model.getChairModel());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        TextView txtSource;
        TextView txtCompany;
        TextView txtCapacity;
        TextView txtPrice;
        TextView txtSourceTerminal;
        TextView txtDestinationTerminal;
        CardView parent;

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSource = (TextView) itemView.findViewById(R.id.txt_busItem_source);
            txtCompany = (TextView) itemView.findViewById(R.id.txt_busItem_company);
            txtCapacity = (TextView) itemView.findViewById(R.id.txt_busItem_capacityValue);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_busItem_price);
            txtSourceTerminal = (TextView) itemView.findViewById(R.id.txt_busItem_sourceTerminal);
            txtDestinationTerminal = (TextView) itemView.findViewById(R.id.txt_busItem_destinationTerminal);
            parent = (CardView) itemView.findViewById(R.id.cv_busItem_parent);
        }
    }
}
