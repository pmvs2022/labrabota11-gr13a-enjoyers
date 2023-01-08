package com.example.cpptest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cpptest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class FirstQueryActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private EditText etPeriodStart;
    private EditText etPeriodEnd;
    private String periodStart;
    private String periodEnd;
    private GridView gvQueryResult;
    private ArrayList<ArrayList<String>> queryResult;
    private Pattern pattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        ConstraintLayout root = binding.getRoot();
        etPeriodStart = (EditText) root.findViewById(R.id.et_period_start);
        etPeriodEnd = (EditText) root.findViewById(R.id.et_period_end);
        gvQueryResult = (GridView) root.findViewById(R.id.gv_query_result);
        pattern = Pattern.compile(String.valueOf(R.string.date_regex));
        setContentView(R.layout.q_most_sold_for_the_period);
    }

    private void setButtonSettings() {
        findViewById(R.id.b_submit_period).findViewById(R.id.button).setOnClickListener(this);
        ((AppCompatButton) findViewById(R.id.b_submit_period).findViewById(R.id.button)).setText("Submit period");
    }

    @Override
    public void onClick(View view) {
        periodStart = etPeriodStart.getText().toString();
        periodEnd = etPeriodEnd.getText().toString();
        if (pattern.matcher(periodStart).matches() && pattern.matcher(periodEnd).matches()) {
            queryResult = databaseQuery();
        }

    }
}
