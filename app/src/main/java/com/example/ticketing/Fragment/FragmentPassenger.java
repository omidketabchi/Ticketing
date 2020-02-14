package com.example.ticketing.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ticketing.R;

public class FragmentPassenger extends Fragment {

    View view;
    EditText edtName;
    EditText edtCode;
    Button btnPassengerSelection;

    OnSubmitLevelTwo onSubmitLevelTwo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.fragment_passengers, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        edtName = (EditText) view.findViewById(R.id.edt_fragmentPassenger_name);
        edtCode = (EditText) view.findViewById(R.id.edt_fragmentPassenger_code);
        btnPassengerSelection = (Button) view.findViewById(R.id.btn_fragmentPassenger_passengerSelect);

        btnPassengerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String code = edtCode.getText().toString();

                if (name.isEmpty() || code.isEmpty()) {
                    Toast.makeText(getContext(), "لطفا فیلدهای خالی را پر کنید", Toast.LENGTH_SHORT).show();
                } else {
                    onSubmitLevelTwo.submitLevelTwo(name, code);
                }
            }
        });
    }

    public interface OnSubmitLevelTwo {
        void submitLevelTwo(String name, String code);
    }

    public void setOnSubmitLevelTwo(OnSubmitLevelTwo onSubmitLevelTwo) {
        this.onSubmitLevelTwo = onSubmitLevelTwo;
    }
}
