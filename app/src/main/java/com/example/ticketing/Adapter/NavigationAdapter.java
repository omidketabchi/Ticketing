package com.example.ticketing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Fragment.FragmentDialogLogin;
import com.example.ticketing.Model.NavigationModel;
import com.example.ticketing.ProfileActivity;
import com.example.ticketing.R;

import org.json.JSONArray;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder> {

    Context context;
    List<NavigationModel> navigationModels;
    private OnNavigationReceiveDataSuccess onNavigationReceiveDataSuccess;

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

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                String email = sharedPreferences.getString("email", "");

                if (model.getTitle().equals("پروفایل کاربری")) {

                    if (email.equals("")) {
                        FragmentDialogLogin fragmentLogin = new FragmentDialogLogin();

                        fragmentLogin.show(((AppCompatActivity) context).getSupportFragmentManager(), null);

                        fragmentLogin.setOnLoginSignupSuccess(new FragmentDialogLogin.OnLoginSignupSuccess() {
                            @Override
                            public void onSuccess(JSONArray response) {
                                onNavigationReceiveDataSuccess.navigationReceiveDataSuccess(response);
                            }
                        });
                    } else {
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                } else if (model.getTitle().equals("لیست مسافران")) {

                } else if (model.getTitle().equals("سوابق تراکنش")) {

                } else if (model.getTitle().equals("خرید های من")) {

                } else if (model.getTitle().equals("خروج از حساب کاربری")) {

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return navigationModels.size();
    }

    public class NavigationViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtTitle;
        CardView parent;

        public NavigationViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.img_navigation_menuItem_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_navigation_menuItem_title);
            parent = (CardView) itemView.findViewById(R.id.cv_navigation_menuItem_parent);
        }
    }

    public interface OnNavigationReceiveDataSuccess {
        void navigationReceiveDataSuccess(JSONArray response);
    }

    public void setOnNavigationReceiveDataSuccess(OnNavigationReceiveDataSuccess onNavigationReceiveDataSuccess) {
        this.onNavigationReceiveDataSuccess = onNavigationReceiveDataSuccess;
    }
}
