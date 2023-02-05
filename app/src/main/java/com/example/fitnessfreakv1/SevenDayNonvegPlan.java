package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class SevenDayNonvegPlan extends AppCompatActivity {

    TextView current_day;
    LinearLayout monday,tuesday,wednesday;
    Float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_nonveg_plan);
        getSupportActionBar().hide();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


        monday=(LinearLayout)findViewById(R.id.monday);
        tuesday=(LinearLayout)findViewById(R.id.tuesday);
        wednesday=(LinearLayout)findViewById(R.id.wednesday);
        current_day=findViewById(R.id.current_day);

        if(dayOfWeek==2){
            current_day.setText("Monday");

            monday.setVisibility(View.VISIBLE);
            tuesday.setVisibility(View.GONE);

        }else if(dayOfWeek==3){
            current_day.setText("Tuesday");
            tuesday.setVisibility(View.VISIBLE);
            monday.setVisibility(View.GONE);

        }else if(dayOfWeek==4){
            current_day.setText("Wednesday");
            wednesday.setVisibility(View.VISIBLE);
            tuesday.setVisibility(View.GONE);

        }else if(dayOfWeek==5){
            current_day.setText("Thursday");
            monday.setVisibility(View.VISIBLE);
            wednesday.setVisibility(View.GONE);

        }else if(dayOfWeek==6){
            current_day.setText("Friday");
            tuesday.setVisibility(View.VISIBLE);
            monday.setVisibility(View.GONE);

        }else if(dayOfWeek==7){
            current_day.setText("Saturday");
            wednesday.setVisibility(View.VISIBLE);
            tuesday.setVisibility(View.GONE);

        }else if(dayOfWeek==1){
            current_day.setText("Sunday");
            tuesday.setVisibility(View.VISIBLE);
            wednesday.setVisibility(View.GONE);

        }

    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1  < x2){
                    Toast.makeText(getApplicationContext(),"Nothing There",Toast.LENGTH_LONG).show();
                }else if(x1 > x2){
                    if(current_day.getText().equals("Monday")){
                        current_day.setText("Tuesday");
                        tuesday.setVisibility(View.VISIBLE);
                        monday.setVisibility(View.GONE);
                    }else if(current_day.getText().equals("Tuesday")){
                        current_day.setText("Wednesday");

                        wednesday.setVisibility(View.VISIBLE);
                        tuesday.setVisibility(View.GONE);
                    }else if(current_day.getText().equals("Wednesday")){
                        current_day.setText("Thursday");

                        monday.setVisibility(View.VISIBLE);
                        wednesday.setVisibility(View.GONE);
                    }else if(current_day.getText().equals("Thursday")){
                        current_day.setText("Friday");

                        tuesday.setVisibility(View.VISIBLE);
                        monday.setVisibility(View.GONE);
                    }else if(current_day.getText().equals("Friday")){
                        current_day.setText("Saturday");

                        wednesday.setVisibility(View.VISIBLE);
                        tuesday.setVisibility(View.GONE);
                    }else if(current_day.getText().equals("Saturday")){
                        current_day.setText("Sunday");

                        tuesday.setVisibility(View.VISIBLE);
                        wednesday.setVisibility(View.GONE);
                    }else{
                        current_day.setText("Monday");

                        monday.setVisibility(View.VISIBLE);
                        tuesday.setVisibility(View.GONE);

                    }


                }
                break;
        }
        return false;
    }
}