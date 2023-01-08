package com.example.cpptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cpptest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("cpptest");
    }

    private ActivityMainBinding binding;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_CppTest);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        context = getApplicationContext();
        ConstraintLayout root = binding.getRoot();
        setContentView(root);
        setButtonSettings();
        Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


    public static native boolean authorizeUser(int option, String email, String password);
    public static native String[][] databaseQuery(int option, String beginDate, String endDate);
    public static String[][] databaseQuery(int option) {
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

    private void setButtonSettings() {
        findViewById(R.id.login_button).findViewById(R.id.button).setOnClickListener(this);
        ((AppCompatButton)findViewById(R.id.login_button).findViewById(R.id.button)).setText("Log in/Register");
    }
}
