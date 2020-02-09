package com.example.ticketing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.NavigationModel;
import com.example.ticketing.R;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder> {

    Context context;
    List<NavigationModel> navigationModels;

    public NavigationAdapter(Context context, List<NavigationModel> navigationModels) {

        this.context = context;
        this.navigationModels = navigationModels;
    }

    @NonNull
    @Override
    public NavigationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.navigation_menu_item, viewGroup, false);

        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationViewHolder holder, int position) {

        NavigationModel model = navigationModels.get(position);

        holder.txtTitle.setText(model.getTitle());
        holder.imgIcon.setImageResource(model.getIcon());
    }

    @Override
    public int getItemCount() {
        return navigationModels.size();
    }

    public class NavigationViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtTitle;

        public NavigationViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.img_navigation_menuItem_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_navigation_menuItem_title);
        }
    }
}
