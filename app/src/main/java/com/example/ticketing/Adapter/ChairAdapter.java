package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.ChairModel;
import com.example.ticketing.R;

import java.util.ArrayList;
import java.util.List;

public class ChairAdapter extends RecyclerView.Adapter<ChairAdapter.ChairViewHolder> {

    Context context;
    List<ChairModel> chairModels;
    int chairNumber = 0;
    int index = 0;
    List<String> chairNumbers;

    OnChairSelect onChairSelect;

    public ChairAdapter(Context context, List<ChairModel> chairModels) {
        this.context = context;
        this.chairModels = chairModels;

        chairNumbers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chair_item, parent, false);

        return new ChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChairViewHolder holder, int position) {


        if (chairNumber <= getItemCount() * 3) {

            holder.txtLeft.setText(String.valueOf(chairNumber + 1));
            holder.txtCenter.setText(String.valueOf(chairNumber + 2));
            holder.txtRight.setText(String.valueOf(chairNumber + 3));

            chairNumber += 3;

            ChairModel leftModel = chairModels.get(index++);
            if (leftModel.getStatus().equals("0")) {
                holder.imgLeft.setImageResource(R.drawable.ic_event_seat_black_24dp);
            } else {
                holder.imgLeft.setImageResource(R.drawable.ic_event_seat_blue_24dp);
                holder.leftParent.setClickable(false);
            }

            ChairModel centerModel = chairModels.get(index++);
            if (centerModel.getStatus().equals("0")) {
                holder.imgCenter.setImageResource(R.drawable.ic_event_seat_black_24dp);
            } else {
                holder.imgCenter.setImageResource(R.drawable.ic_event_seat_blue_24dp);
                holder.centerParent.setClickable(false);
            }

            ChairModel rightModel = chairModels.get(index++);
            if (rightModel.getStatus().equals("0")) {
                holder.imgRight.setImageResource(R.drawable.ic_event_seat_black_24dp);
            } else {
                holder.imgRight.setImageResource(R.drawable.ic_event_seat_blue_24dp);
                holder.rightParent.setClickable(false);
            }
        }

        holder.leftParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = holder.txtLeft.getText().toString();

                if (holder.leftParent.getContentDescription().equals("disable")) {
                    holder.leftParent.setContentDescription("enable");
                    holder.leftParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorStar));
                    holder.imgLeft.setImageResource(R.drawable.ic_event_seat_red_24dp);
                    holder.leftParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_red_style));

                    chairNumbers.add(number);

                } else {
                    holder.leftParent.setContentDescription("disable");
                    holder.leftParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
                    holder.imgLeft.setImageResource(R.drawable.ic_event_seat_black_24dp);
                    holder.leftParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_blue_style));

                    if (chairNumbers.contains(number)) {
                        chairNumbers.remove(number);
                    }
                }

                onChairSelect.onChairSelected(chairNumbers);
            }
        });

        holder.centerParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = holder.txtCenter.getText().toString();

                if (holder.centerParent.getContentDescription().equals("disable")) {
                    holder.centerParent.setContentDescription("enable");
                    holder.imgCenter.setImageResource(R.drawable.ic_event_seat_red_24dp);
                    holder.centerParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_red_style));

                    chairNumbers.add(number);

                } else {
                    holder.centerParent.setContentDescription("disable");
                    holder.imgCenter.setImageResource(R.drawable.ic_event_seat_black_24dp);
                    holder.centerParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_blue_style));

                    if (chairNumbers.contains(number)) {
                        chairNumbers.remove(number);
                    }
                }

                onChairSelect.onChairSelected(chairNumbers);
            }
        });

        holder.rightParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = holder.txtRight.getText().toString();

                if (holder.rightParent.getContentDescription().equals("disable")) {
                    holder.rightParent.setContentDescription("enable");
                    holder.imgRight.setImageResource(R.drawable.ic_event_seat_red_24dp);
                    holder.rightParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_red_style));

                    chairNumbers.add(number);

                } else {
                    holder.rightParent.setContentDescription("disable");
                    holder.imgRight.setImageResource(R.drawable.ic_event_seat_black_24dp);
                    holder.rightParent.setBackground(ContextCompat.getDrawable(context, R.drawable.chair_blue_style));

                    if (chairNumbers.contains(number)) {
                        chairNumbers.remove(number);
                    }
                }

                onChairSelect.onChairSelected(chairNumbers);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chairModels.size() / 3;
    }

    public class ChairViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftParent;
        LinearLayout centerParent;
        LinearLayout rightParent;

        ImageView imgLeft;
        ImageView imgCenter;
        ImageView imgRight;

        TextView txtLeft;
        TextView txtCenter;
        TextView txtRight;

        public ChairViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLeft = (ImageView) itemView.findViewById(R.id.img_chairItem_left);
            imgCenter = (ImageView) itemView.findViewById(R.id.img_chairItem_center);
            imgRight = (ImageView) itemView.findViewById(R.id.img_chairItem_right);

            txtLeft = (TextView) itemView.findViewById(R.id.txt_chairItem_leftNumber);
            txtCenter = (TextView) itemView.findViewById(R.id.txt_chairItem_centerNumber);
            txtRight = (TextView) itemView.findViewById(R.id.txt_chairItem_rightNumber);


            leftParent = (LinearLayout) itemView.findViewById(R.id.ll_chairItem_left);
            centerParent = (LinearLayout) itemView.findViewById(R.id.ll_chairItem_center);
            rightParent = (LinearLayout) itemView.findViewById(R.id.ll_chairItem_right);
        }
    }

    public interface OnChairSelect {
        void onChairSelected(List<String> chairNumbers);
    }

    public void setOnChairSelect(OnChairSelect onChairSelect) {
        this.onChairSelect = onChairSelect;
    }
}
