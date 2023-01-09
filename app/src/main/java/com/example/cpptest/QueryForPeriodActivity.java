package com.example.cpptest;

import static com.example.cpptest.MainActivity.databaseQuery;

import android.content.Context;
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
    private EditText etPeriodStart;
    private EditText etPeriodEnd;
    private String periodStart;
    private String periodEnd;
    private GridView gvQueryResult;
    private Pattern pattern;
    private Context context;
    SimpleAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_most_sold_for_the_period);
        getSupportActionBar().hide();
        //ConstraintLayout root = binding.getRoot();
        etPeriodStart = (EditText) findViewById(R.id.et_period_start);
        etPeriodEnd = (EditText) findViewById(R.id.et_period_end);
        gvQueryResult = (GridView) findViewById(R.id.gv_query_result);
        pattern = Pattern.compile(getResources().getString(R.string.date_regex));
        intent = getIntent();
        context = getApplicationContext();
        etPeriodStart.setText("01-01-2022");
        etPeriodEnd.setText("01-06-2022");
        findViewById(R.id.b_submit_period).findViewById(R.id.button).setOnClickListener(this);
        ((AppCompatButton) findViewById(R.id.b_submit_period).findViewById(R.id.button)).setText(getResources().getText(R.string.submit_period));
    }

    @Override
    public void onClick(View view) {
        periodStart = etPeriodStart.getText().toString();
        periodEnd = etPeriodEnd.getText().toString();
        if (pattern.matcher(periodStart).matches() && pattern.matcher(periodEnd).matches()) {
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            int option = intent.getIntExtra("option", 1);
            String[] columnNames = intent.getStringArrayExtra("column_names");
            Object[] queryResult = databaseQuery(option, periodStart, periodEnd);

            for (int i = 0; i < queryResult.length; ++i) {
                String[] entry = (String[]) queryResult[i];
                Map<String, String> tab = new HashMap<String, String>();
                switch (option) {
                    case 1:
                        for (int j = 0; j < entry.length; ++j) {
                            tab.put(columnNames[j], entry[j]);
                        }
                        break;
                }
                data.add(tab);

            }
            int[] into = {R.id.column1, R.id.column2, R.id.column3};
            adapter = new SimpleAdapter(QueryForPeriodActivity.this, data, R.layout.gridview_query_result_layout, columnNames, into);
            gvQueryResult.setAdapter(adapter);
        } else {
            Toast.makeText( context, "Wrong date period format (use dd-mm-yyyy)",Toast.LENGTH_LONG).show();
        }
    }
}
