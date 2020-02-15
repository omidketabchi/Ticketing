package com.example.ticketing.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Adapter.HotelAdapter;
import com.example.ticketing.HotelMapActivity;
import com.example.ticketing.Model.HotelModel;
import com.example.ticketing.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHotelList extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<HotelModel> hotelModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_hotel_list, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        hotelModels = new ArrayList<>();

        hotelModels = getArguments().getParcelableArrayList("hotels");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentHotelList_hotelLis);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new HotelAdapter(getContext(), hotelModels));
    }
}
