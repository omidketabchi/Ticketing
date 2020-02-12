package com.example.ticketing.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.R;

public class FragmentChair extends Fragment {

    View view;
    Button btnSubmit;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_chair, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        btnSubmit = (Button) view.findViewById(R.id.btn_fragmentChair_submit);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentChair_chairList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
