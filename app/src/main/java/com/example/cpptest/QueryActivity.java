package com.example.cpptest;

import static com.example.cpptest.MainActivity.databaseQuery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView gvQueryResult;
    private Context context;
    SimpleAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.q_most_sold);
        gvQueryResult = (GridView) findViewById(R.id.gv_query_result2);
        intent = getIntent();
        context = getApplicationContext();
        ButtonFragment.addTouchDelegate(findViewById(R.id.b_execute_query).findViewById(R.id.button),this);
        ((AppCompatButton) findViewById(R.id.b_execute_query).findViewById(R.id.button)).setText(getResources().getText(R.string.execute_query));
    }

    @Override
    public void onClick(View view) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        int option = intent.getIntExtra("option", 2);
        String[] columnNames = intent.getStringArrayExtra("column_names");
        Object[] queryResult = databaseQuery(option);
        for (int i = 0; i < queryResult.length; ++i) {
            String[] entry = (String[]) queryResult[i];
            Map<String, String> tab = new HashMap<String, String>();
            switch (option) {
                case 2:
                    tab.put(columnNames[0], entry[3]);
                    tab.put(columnNames[1], entry[1]);
                    tab.put(columnNames[2], entry[5]);
                    break;
                case 4:
                    tab.put(columnNames[0], entry[1]);
                    tab.put(columnNames[1], entry[2]);
                    tab.put(columnNames[2], entry[3]);
                    break;
            }
            data.add(tab);

        }
        int[] into = {R.id.column1, R.id.column2, R.id.column3};
        adapter = new SimpleAdapter(QueryActivity.this, data, R.layout.gridview_query_result_layout, columnNames, into);
        gvQueryResult.setAdapter(adapter);
    }


}
