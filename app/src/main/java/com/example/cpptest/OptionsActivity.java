package com.example.cpptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_options);
        setListeners();
        setButtonLabels();
    }

    private void setButtonLabels() {
        ((AppCompatButton)findViewById(R.id.button_first).findViewById(R.id.button)).setText(R.string.option_first);
        ((AppCompatButton)findViewById(R.id.button_second).findViewById(R.id.button)).setText(R.string.option_second);
        ((AppCompatButton)findViewById(R.id.button_third).findViewById(R.id.button)).setText("Third option");
    }

    private void setListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class nextLayoutClass;
                switch (((View)view.getParent()).getId()) {
                    case R.id.button_first:
                        nextLayoutClass = OptionFirst.class;
                        break;
                    case R.id.button_second:
                        nextLayoutClass = OptionSecond.class;
                        break;
                    case R.id.button_third:
                        nextLayoutClass = OptionThird.class;
                        break;
                    default:
                        nextLayoutClass = this.getClass();
                }
                Intent myIntent = new Intent(OptionsActivity.this, nextLayoutClass);
                startActivity(myIntent);
            }
        };

        findViewById(R.id.button_first).findViewById(R.id.button).setOnClickListener(listener);
        findViewById(R.id.button_second).findViewById(R.id.button).setOnClickListener(listener);
        findViewById(R.id.button_third).findViewById(R.id.button).setOnClickListener(listener);
    }
}
