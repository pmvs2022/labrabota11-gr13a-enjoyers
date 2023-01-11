package com.example.cpptest;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
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

    }

    private void setButtonLabels() {
        ((AppCompatButton)findViewById(R.id.b_submit_period).findViewById(R.id.button)).setText(R.string.choose);
    }

    private void setListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };

        ButtonFragment.addTouchDelegate(findViewById(R.id.b_submit_period).findViewById(R.id.button),listener);
    }

}
