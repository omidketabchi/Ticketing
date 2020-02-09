package com.example.ticketing.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Model.ProfileModel;
import com.example.ticketing.R;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    Context context;
    List<ProfileModel> profiles;

    public ProfileAdapter(Context context, List<ProfileModel> profiles) {

        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.profile_item, viewGroup, false);

        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

        ProfileModel model = profiles.get(position);

        holder.txtTitle.setText(model.getTitle());
        holder.txtValue.setText(model.getValue());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtValue;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_profileItem_title);
            txtValue = (TextView) itemView.findViewById(R.id.txt_profileItem_value);
        }
    }
}
