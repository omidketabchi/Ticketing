package com.example.ticketing.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ticketing.R;

public class FragmentDialogPassenger extends DialogFragment implements View.OnClickListener {

    View view;
    TextView txtNormalPassengers;
    TextView txtBrotherPassengers;
    TextView txtSisterPassengers;
    ImageView imgAddNormalPassengers;
    ImageView imgRemoveNormalPassengers;
    ImageView imgAddBrotherPassengers;
    ImageView imgRemoveBrotherPassengers;
    ImageView imgAddSisterPassengers;
    ImageView imgRemoveSisterPassengers;
    Button btnSubmit;

    private OnDialogPassengerSubmitClick onDialogPassengerSubmitClick;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        view = LayoutInflater.from(getContext()).inflate(R.layout.passenger_dialog, null);

        builder.setView(view);

        setupViews();

        return builder.create();
    }

    private void setupViews() {

        txtNormalPassengers = (TextView) view.findViewById(R.id.txt_passengerDialog_count);
        txtBrotherPassengers = (TextView) view.findViewById(R.id.txt_passengerDialog_countB);
        txtSisterPassengers = (TextView) view.findViewById(R.id.txt_passengerDialog_countS);

        imgAddNormalPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_add);
        imgRemoveNormalPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_remove);
        imgAddBrotherPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_addB);
        imgRemoveBrotherPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_removeB);
        imgAddSisterPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_addS);
        imgRemoveSisterPassengers = (ImageView) view.findViewById(R.id.img_passengerDialog_removeS);

        btnSubmit = (Button) view.findViewById(R.id.btn_passengerDialog_submit);

        imgAddNormalPassengers.setOnClickListener(this::onClick);
        imgRemoveNormalPassengers.setOnClickListener(this::onClick);
        imgAddBrotherPassengers.setOnClickListener(this::onClick);
        imgRemoveBrotherPassengers.setOnClickListener(this::onClick);
        imgAddSisterPassengers.setOnClickListener(this::onClick);
        imgRemoveSisterPassengers.setOnClickListener(this::onClick);
        btnSubmit.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_passengerDialog_add:
                addPassenger(txtNormalPassengers);
                break;
            case R.id.img_passengerDialog_addB:
                addPassenger(txtBrotherPassengers);
                break;
            case R.id.img_passengerDialog_addS:
                addPassenger(txtSisterPassengers);
                break;
            case R.id.img_passengerDialog_remove:
                removePassenger(txtNormalPassengers);
                break;
            case R.id.img_passengerDialog_removeB:
                removePassenger(txtBrotherPassengers);
                break;
            case R.id.img_passengerDialog_removeS:
                removePassenger(txtSisterPassengers);
                break;
            case R.id.btn_passengerDialog_submit:
                submit();
                break;
        }
    }

    private void removePassenger(TextView txtNumber) {

        int number = Integer.parseInt(txtNumber.getText().toString());

        number = (number == 0) ? 0 : number - 1;

        txtNumber.setText(String.valueOf(number));
    }

    private void addPassenger(TextView txtNumber) {

        int number = Integer.parseInt(txtNumber.getText().toString());

        txtNumber.setText(String.valueOf(number + 1));
    }

    private void submit() {

        onDialogPassengerSubmitClick.onSubmitClick(txtNormalPassengers.getText().toString(),
                txtBrotherPassengers.getText().toString(),
                txtSisterPassengers.getText().toString());

        dismiss();
    }

    public interface OnDialogPassengerSubmitClick {

        void onSubmitClick(String numberOfNormalPassengers,
                           String numberOfBrotherPassengers,
                           String numberOfSisterPassengers);
    }

    public void setOnDialogPassengerSubmitClick(OnDialogPassengerSubmitClick onDialogPassengerSubmitClick) {
        this.onDialogPassengerSubmitClick = onDialogPassengerSubmitClick;
    }
}
