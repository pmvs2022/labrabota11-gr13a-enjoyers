package com.example.cpptest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class FindShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.q_most_sold_for_the_period);
        setListeners();
        setButtonLabels();

        // TODO: 30.12.2022 remove when done 
        //((TextView)findViewById(R.id.tv_result_edit)).setText("Enter your results here");
    }

    private void setButtonLabels() {
        ((AppCompatButton)findViewById(R.id.b_submit_period).findViewById(R.id.button)).setText("Choose");
    }

    private void setListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 30.12.2022 change tv_resilt_edit TextView to show a results by pressing the button
             //   ((TextView)findViewById(R.id.tv_result_edit)).setText("Enter your results here");
            }
        };

        findViewById(R.id.b_submit_period).findViewById(R.id.button).setOnClickListener(listener);
    }
}
