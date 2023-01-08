package com.example.cpptest;

import static com.example.cpptest.MainActivity.databaseQuery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cpptest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class QueryForPeriodActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private EditText etPeriodStart;
    private EditText etPeriodEnd;
    private String periodStart;
    private String periodEnd;
    private GridView gvQueryResult;
    private Object[] queryResult;
    private String[][] queryResultStr;
    private Pattern pattern;
    SimpleAdapter adapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_most_sold_for_the_period);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //ConstraintLayout root = binding.getRoot();
        etPeriodStart = (EditText) findViewById(R.id.et_period_start);
        etPeriodEnd = (EditText) findViewById(R.id.et_period_end);
        gvQueryResult = (GridView) findViewById(R.id.gv_query_result);
        pattern = Pattern.compile(getResources().getString(R.string.date_regex));
        intent = getIntent();
        findViewById(R.id.b_submit_period).findViewById(R.id.button).setOnClickListener(this);
        ((AppCompatButton) findViewById(R.id.b_submit_period).findViewById(R.id.button)).setText("Submit period");
    }

    private void setButtonSettings() {

    }

    @Override
    public void onClick(View view) {
        System.out.println();
        periodStart = etPeriodStart.getText().toString();
        periodEnd = etPeriodEnd.getText().toString();
        if (pattern.matcher(periodStart).matches() && pattern.matcher(periodEnd).matches()) {
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            int option = intent.getIntExtra("option", 1);
            String[] columnNames = intent.getStringArrayExtra("column_names");
            queryResult = databaseQuery(option, periodStart, periodEnd);
            System.out.println("Done!");
            for(int i = 0; i < queryResult.length; ++i) {
                Map<String, String> tab = new HashMap<String, String>();
                for(int j = 0; j < columnNames.length; ++j) {
                    //tab.put(columnNames[j], )
                }
            }

            //gvQueryResult.
            //adapter = new SimpleAdapter(QueryForPeriodActivity.this, )
        }
        else {
            System.out.println("Doesn't match!");
        }
    }
}
