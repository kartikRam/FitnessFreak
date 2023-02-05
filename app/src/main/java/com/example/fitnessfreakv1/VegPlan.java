package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VegPlan extends AppCompatActivity {


    VideoView veg_image;
    CardView sevendayvegplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_plan);
        getSupportActionBar().hide();
        sevendayvegplan=(CardView)findViewById(R.id.sevendayvegplan);
        sevendayvegplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SevenDayVegDiet.class);
                startActivity(intent);
            }
        });
        /* veg_image = (VideoView) findViewById(R.id.veg_image);
        MediaController mc = new MediaController(this);
        veg_image.setVideoPath("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.veg_plan);
        veg_image.setMediaController(mc);
        veg_image.start();
        VideoLoop.OnPrep(mc,veg_image);
*/
    }
}