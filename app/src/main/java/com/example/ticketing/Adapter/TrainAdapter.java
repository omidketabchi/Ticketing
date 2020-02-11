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

import com.example.ticketing.Model.TrainModel;
import com.example.ticketing.R;
import com.example.ticketing.TrainInformationActivity;

import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {

    Context context;
    List<TrainModel> models;

    public TrainAdapter(Context context, List<TrainModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.train_item, parent, false);

        return new TrainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {

        TrainModel model = models.get(position);

        holder.txtStartTime.setText(model.getStartTime());
        holder.txtEndTime.setText(model.getEndTime());
        holder.txtTrainName.setText("::" + model.getType());
        holder.txtPrice.setText(model.getPrice());
        holder.txtCapacity.setText(model.getCapacity());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TrainInformationActivity.class);
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

    public class TrainViewHolder extends RecyclerView.ViewHolder {

        TextView txtStartTime;
        TextView txtEndTime;
        TextView txtTrainName;
        TextView txtPrice;
        TextView txtCapacity;
        CardView parent;

        public TrainViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStartTime = (TextView) itemView.findViewById(R.id.txt_trainItem_startTime);
            txtEndTime = (TextView) itemView.findViewById(R.id.txt_trainItem_endTime);
            txtTrainName = (TextView) itemView.findViewById(R.id.txt_trainItem_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_trainItem_price);
            txtCapacity = (TextView) itemView.findViewById(R.id.txt_trainItem_capacity);
            parent = (CardView) itemView.findViewById(R.id.cv_trainItem_parent);
        }
    }
}
