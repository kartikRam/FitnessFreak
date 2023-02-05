package com.example.fitnessfreakv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getSupportActionBar().hide();
    }
}