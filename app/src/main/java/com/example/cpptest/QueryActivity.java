package com.example.cpptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SecondQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.q_most_sold);
    }
}
