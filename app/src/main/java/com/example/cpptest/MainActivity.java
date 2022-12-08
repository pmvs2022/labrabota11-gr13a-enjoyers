package com.example.cpptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cpptest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("cpptest");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView tv = binding.sampleText;
        tv.setText("Hello world");
    }

    public native boolean authorizeUser(int option, String email, String password);
    public native Object[] databaseQuery(int option, String beginDate, String endDate);

    public Object[] databaseQuery(int option) {
        return databaseQuery(option, "", "");
    }
}