package com.example.cpptest;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.TouchDelegate;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_options);
        setButtonListeners();
        setButtonLabels();
    }

    private void setButtonLabels() {
        ((AppCompatButton)findViewById(R.id.b_find_shop).findViewById(R.id.button)).setText(R.string.find_shop);
        ((AppCompatButton)findViewById(R.id.b_query_most_sold_p).findViewById(R.id.button)).setText(R.string.q_most_sold_for_the_period);
        ((AppCompatButton)findViewById(R.id.b_query_most_sold).findViewById(R.id.button)).setText(R.string.q_most_sold);
        ((AppCompatButton)findViewById(R.id.b_query_sales_info).findViewById(R.id.button)).setText(R.string.q_sales_info_by_author);
    }

    private void setButtonListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class nextLayoutClass;
                String[] columnNames = new String[0];
                int option = -1;
                switch (((View)view.getParent()).getId()) {
                    case R.id.b_find_shop:
                        nextLayoutClass = FindShopActivity.class;
                        break;
                    case R.id.b_query_most_sold_p:
                        nextLayoutClass = QueryForPeriodActivity.class;
                        columnNames = new String[]{"Compact code", "Copies sold", "Total cost"};
                        option = 1;
                        break;
                    case R.id.b_query_most_sold:
                        nextLayoutClass = QueryActivity.class;
                        columnNames = new String[]{"Compact code", "Copies sold", "Total cost"};
                        option = 2;
                        break;
                    case R.id.b_query_sales_info:
                        nextLayoutClass = QueryActivity.class;
                        columnNames = new String[]{"Author name", "Copies sold", "Total cost"};
                        option = 4;
                        break;
                    default:
                        nextLayoutClass = this.getClass();
                }
                Intent myIntent = new Intent(OptionsActivity.this, nextLayoutClass);
                myIntent.putExtra("option", option);
                myIntent.putExtra("column_names", columnNames);
                startActivity(myIntent);
            }
        };

        ButtonFragment.addTouchDelegate(findViewById(R.id.b_find_shop).findViewById(R.id.button),listener);
        ButtonFragment.addTouchDelegate(findViewById(R.id.b_query_most_sold_p).findViewById(R.id.button),listener);
        ButtonFragment.addTouchDelegate(findViewById(R.id.b_query_most_sold).findViewById(R.id.button),listener);
        ButtonFragment.addTouchDelegate(findViewById(R.id.b_query_sales_info).findViewById(R.id.button),listener);
    }

}
