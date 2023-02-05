package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NonVegPlan extends AppCompatActivity {

    CardView nonveg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_veg_plan);
        getSupportActionBar().hide();
        nonveg=(CardView)findViewById(R.id.sevendaynonvegplan);
        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SevenDayNonvegPlan.class);
                startActivity(intent);
            }
        });
    }
}