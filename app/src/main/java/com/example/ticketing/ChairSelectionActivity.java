package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticketing.Fragment.FragmentChair;
import com.example.ticketing.Fragment.FragmentPassenger;
import com.example.ticketing.Fragment.FragmentPayment;
import com.example.ticketing.Fragment.FragmentTicketConfirmation;
import com.example.ticketing.Model.ChairModel;
import com.shuhart.stepview.StepView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChairSelectionActivity extends AppCompatActivity {

    TextView txtTitle;
    ImageView imgBack;
    StepView stepView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_selection);

        setupViews();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_chairSelection_back);
        stepView = (StepView) findViewById(R.id.stp_chairSelection_stepView);
        txtTitle = (TextView) findViewById(R.id.txt_chairSelection_title);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        stepView.getState()
                .steps(new ArrayList<String>() {{
                    add("انتخاب صندلی");
                    add("افزودن مسافر");
                    add("تایید اطلاعات بلیط");
                    add("پرداخت");
                }})

                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
                .typeface(ResourcesCompat.getFont(ChairSelectionActivity.this, R.font.font))
                .commit();

        showFragments();
    }

    private void showFragments() {

        List<ChairModel> chairModels = getIntent().getParcelableArrayListExtra("chair");
        String price = getIntent().getExtras().getString("price");

        txtTitle.setText("انتخاب صندلی");

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("chair", (ArrayList<? extends Parcelable>) chairModels);
        bundle.putString("price", price);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        FragmentChair fragmentChair = new FragmentChair();

        fragmentChair.setArguments(bundle);
        transaction.replace(R.id.frm_chairSelection_frame, fragmentChair);
//        transaction.addToBackStack(null);
        transaction.commit();

        fragmentChair.setOnChairListSelect(new FragmentChair.OnChairListSelect() {
            @Override
            public void chairSelected(List<String> chairList) {

                FragmentPassenger fragmentPassenger = new FragmentPassenger();
                FragmentTransaction tr = fragmentManager.beginTransaction();
                tr.replace(R.id.frm_chairSelection_frame, fragmentPassenger);
                tr.addToBackStack(null);
                tr.commit();

                stepView.go(1, true);
                Toast.makeText(ChairSelectionActivity.this, chairList.toString(), Toast.LENGTH_SHORT).show();

                fragmentPassenger.setOnSubmitLevelTwo(new FragmentPassenger.OnSubmitLevelTwo() {
                    @Override
                    public void submitLevelTwo(String name, String code) {

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("chairList", (Serializable) chairList);
                        bundle.putString("price", price);

                        FragmentTicketConfirmation fragmentTicketConfirmation = new FragmentTicketConfirmation();
                        FragmentTransaction confirmationTransaction = fragmentManager.beginTransaction();
                        fragmentTicketConfirmation.setArguments(bundle);
                        confirmationTransaction.replace(R.id.frm_chairSelection_frame, fragmentTicketConfirmation);
                        confirmationTransaction.addToBackStack(null);
                        confirmationTransaction.commit();

                        stepView.go(2, true);

                        fragmentTicketConfirmation.setOnTicketInformationSubmit(new FragmentTicketConfirmation.OnTicketInformationSubmit() {
                            @Override
                            public void ticketInformationSubmit() {


                                Bundle bundle = new Bundle();
                                bundle.putString("price", price);

                                FragmentPayment fragmentPayment = new FragmentPayment();
                                FragmentTransaction paymentTransaction = fragmentManager.beginTransaction();
                                fragmentPayment.setArguments(bundle);
                                paymentTransaction.replace(R.id.frm_chairSelection_frame, fragmentPayment);
                                paymentTransaction.addToBackStack(null);
                                paymentTransaction.commit();

                                stepView.go(3, true);
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment = fragmentManager.findFragmentById(R.id.frm_chairSelection_frame);

        if (fragment instanceof FragmentChair) {
            Toast.makeText(this, "FragmentChair", Toast.LENGTH_SHORT).show();
            stepView.go(0, true);
        } else if (fragment instanceof FragmentPassenger) {
            Toast.makeText(this, "FragmentPassenger", Toast.LENGTH_SHORT).show();
            stepView.go(1, true);
        } else if (fragment instanceof FragmentTicketConfirmation) {
            Toast.makeText(this, "FragmentTicketConfirmation", Toast.LENGTH_SHORT).show();
            stepView.go(2, true);
        } else if (fragment instanceof FragmentPayment) {
            Toast.makeText(this, "FragmentPayment", Toast.LENGTH_SHORT).show();
            stepView.go(3, true);
        }
    }
}
