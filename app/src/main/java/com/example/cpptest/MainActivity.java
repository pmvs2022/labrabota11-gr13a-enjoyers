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
import android.widget.EditText;
import android.widget.Toast;

import com.example.cpptest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("cpptest");
    }
    private EditText etUsername;
    private EditText etPassword;
    private ActivityMainBinding binding;
    private Context context;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_CppTest);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        context = getApplicationContext();
        ConstraintLayout root = binding.getRoot();
        etUsername = (EditText) root.findViewById(R.id.username);
        etPassword = (EditText) root.findViewById(R.id.password);
        setContentView(root);
        setButtonsSettings();
    }


    public native boolean authorizeUser(int option, String email, String password);
    public native Object[] databaseQuery(int option, String beginDate, String endDate);
    public Object[] databaseQuery(int option) {
        return databaseQuery(option, "", "");
    }


    @Override
    public void onClick(View view) {
        try {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

            switch (((View) view.getParent()).getId()) {
                case R.id.login_button:
                    if (authorizeUser(0, username, password)) {
                        Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
                        Toast.makeText(context, "Logged in successfully!", Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(myIntent);
                    } else {
                        Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.signup_button:
                    if (authorizeUser(1, username, password)) {
                        Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
                        Toast.makeText(context, "Signed up successfully!", Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(myIntent);
                    } else {
                        Toast.makeText(context, "Invalid credentials format", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void setButtonsSettings() {
        findViewById(R.id.login_button).findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.signup_button).findViewById(R.id.button).setOnClickListener(this);
        ((AppCompatButton)findViewById(R.id.login_button).findViewById(R.id.button)).setText("Log in");
        ((AppCompatButton)findViewById(R.id.signup_button).findViewById(R.id.button)).setText("Sign up");
    }
}
