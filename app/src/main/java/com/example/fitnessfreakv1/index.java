package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class index extends AppCompatActivity implements   AdapterView.OnItemSelectedListener {
    String[] country = {"Select Weight", "1kg to 5kg", "5kg to 10kg", "10kg to 20kg", "20kg to 30kg", "30kg to 40kg"};
    String[] type_select = {"Exercise", "Diet Plan"};

    Spinner spin,type;
    Button button;
    String kg,type_selected;
    TextView method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_index);
        button = findViewById(R.id.button);
        button.setVisibility(View.GONE);
        spin = (Spinner) findViewById(R.id.spinner);

        type = (Spinner) findViewById(R.id.type);

        spin.setOnItemSelectedListener(this);
        type.setOnItemSelectedListener(this);
        method = findViewById(R.id.method);

        method.setVisibility(View.GONE);
        type.setVisibility(View.GONE);
        ArrayAdapter ab = new ArrayAdapter(this, android.R.layout.simple_spinner_item, type_select);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        type.setAdapter(ab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_selected.equals("Diet Plan")) {
                    Intent intent = new Intent(getApplicationContext(), choosePlan.class);
                    intent.putExtra("kg", kg);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), Exercise.class);
                    startActivity(intent);

                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if(method.getVisibility()==View.GONE) {
            kg = country[position];
        }
        if(!(kg.equals("Select Weight"))){
            if(method.getVisibility()==View.GONE) {
                method.setVisibility(View.VISIBLE);
                type.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }else{
            if(method.getVisibility()==View.VISIBLE) {
                method.setVisibility(View.GONE);
                type.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }

        }
        if(position<2) {
            type_selected = type_select[position];
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}