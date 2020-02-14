package com.example.ticketing.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ticketing.R;

import java.util.List;

public class FragmentTicketConfirmation extends Fragment {

    View view;

    TextView txtEmail;
    TextView txtApplyDiscount;
    TextView txtPrice;
    EditText edtDiscount;
    Button btnSubmit;

    OnTicketInformationSubmit onTicketInformationSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_ticket_confirmation, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        String price = getArguments().getString("price");
        List<String> chairList = (List<String>) getArguments().getSerializable("chairList");

        txtEmail = (TextView) view.findViewById(R.id.txt_fragmentTicketConfirmation_email);
        txtApplyDiscount = (TextView) view.findViewById(R.id.txt_fragmentTicketConfirmation_applyDiscount);
        txtPrice = (TextView) view.findViewById(R.id.txt_fragmentTicketConfirmation_priceValue);
        edtDiscount = (EditText) view.findViewById(R.id.edt_fragmentTicketConfirmation_discount);
        btnSubmit = (Button) view.findViewById(R.id.btn_fragmentTicketConfirmation_submit);

        txtPrice.setText(price + "/" + chairList.toString());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTicketInformationSubmit.ticketInformationSubmit();
            }
        });
    }

    public interface OnTicketInformationSubmit{

        void ticketInformationSubmit();
    }

    public void setOnTicketInformationSubmit(OnTicketInformationSubmit onTicketInformationSubmit) {
        this.onTicketInformationSubmit = onTicketInformationSubmit;
    }
}
