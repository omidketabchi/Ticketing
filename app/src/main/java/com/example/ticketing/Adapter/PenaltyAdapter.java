package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.PenaltyModel;
import com.example.ticketing.R;

import java.util.List;

public class PenaltyAdapter extends RecyclerView.Adapter<PenaltyAdapter.PenaltyViewHolder> {

    Context context;
    List<PenaltyModel> models;

    public PenaltyAdapter(Context context, List<PenaltyModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public PenaltyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.penalty_item, parent, false);

        return new PenaltyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenaltyViewHolder holder, int position) {

        PenaltyModel model = models.get(position);

        holder.txtRule.setText(model.getRule());
        holder.txtPercent.setText(model.getPercent());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class PenaltyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRule;
        TextView txtPercent;

        public PenaltyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRule = (TextView) itemView.findViewById(R.id.txt_penaltyItem_rule);
            txtPercent = (TextView) itemView.findViewById(R.id.txt_penaltyItem_percent);
        }
    }
}
