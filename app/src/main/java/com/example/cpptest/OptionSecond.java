package com.example.cpptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OptionSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.option_second);
    }
}
