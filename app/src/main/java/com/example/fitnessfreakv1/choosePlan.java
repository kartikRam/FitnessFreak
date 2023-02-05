package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choosePlan extends AppCompatActivity {

    CardView veg,nonveg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_plan);
        getSupportActionBar().hide();
        veg=(CardView) findViewById(R.id.veg);
        nonveg=(CardView)findViewById(R.id.nonveg);
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VegPlan.class);
                startActivity(intent);
            }
        });

        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),NonVegPlan.class);
                startActivity(intent);
            }
        });
    }
}