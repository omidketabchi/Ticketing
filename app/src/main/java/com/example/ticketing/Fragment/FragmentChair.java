package com.example.ticketing.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.Adapter.ChairAdapter;
import com.example.ticketing.Model.ChairModel;
import com.example.ticketing.R;

import java.util.List;

public class FragmentChair extends Fragment {

    View view;
    Button btnSubmit;
    RecyclerView recyclerView;
    TextView txtPrice;
    List<String> chairList;

    OnChairListSelect onChairListSelect;

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
        txtPrice = (TextView) view.findViewById(R.id.txt_fragmentChair_priceValue);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentChair_chairList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChairListSelect.chairSelected(chairList);
            }
        });

        List<ChairModel> chairModels = getArguments().getParcelableArrayList("chair");
        String price = getArguments().getString("price");

        txtPrice.setText(price);

        ChairAdapter chairAdapter = new ChairAdapter(getContext(), chairModels);

        recyclerView.setAdapter(chairAdapter);

        chairAdapter.setOnChairSelect(new ChairAdapter.OnChairSelect() {
            @Override
            public void onChairSelected(List<String> chairNumbers) {

                String temp = "";
                for (String item : chairNumbers) {
                    temp += item + ",";
                }

                Toast.makeText(getContext(), temp, Toast.LENGTH_SHORT).show();

                if (chairNumbers.size() > 0) {
                    btnSubmit.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_blue_style));
                    btnSubmit.setClickable(true);
                    chairList = chairNumbers;
                } else {
                    btnSubmit.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_disable_style));
                    btnSubmit.setClickable(false);
                }
            }
        });
    }

    public interface OnChairListSelect {
        void chairSelected(List<String> chairList);
    }

    public void setOnChairListSelect(OnChairListSelect onChairListSelect) {
        this.onChairListSelect = onChairListSelect;
    }
}
