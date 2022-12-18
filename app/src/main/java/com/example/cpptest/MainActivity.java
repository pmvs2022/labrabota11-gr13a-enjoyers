package com.example.cpptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cpptest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("cpptest");
    }

    private ActivityMainBinding binding;
    private Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        context = getApplicationContext();
        ConstraintLayout root = binding.getRoot();
        setContentView(root);
        root.findViewById(R.id.login_button).findViewById(R.id.button).setOnClickListener(this);
    }

    public native boolean authorizeUser(int option, String email, String password);
    public native Object[] databaseQuery(int option, String beginDate, String endDate);
    public Object[] databaseQuery(int option) {
        return databaseQuery(option, "", "");
    }


    @Override
    public void onClick(View view) {
        switch (((View)view.getParent()).getId()){
            case R.id.login_button:
                Toast.makeText( context, "This is login" + "\n" + R.id.login_button,Toast.LENGTH_LONG).show();

                boolean registerConfirmed = true;
                if(registerConfirmed){
                    Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
                    myIntent.putExtra("key", "bruh"); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                }
                break;
        }
    }
}