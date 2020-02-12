package com.example.ticketing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ticketing.Fragment.FragmentChair;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class ChairSelectionActivity extends AppCompatActivity {

    ImageView imgBack;
    StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_selection);

        setupViews();
    }

    private void setupViews() {

        imgBack = (ImageView) findViewById(R.id.img_chairSelection_back);
        stepView = (StepView) findViewById(R.id.stp_chairSelection_stepView);

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frm_chairSelection_frame, new FragmentChair());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
