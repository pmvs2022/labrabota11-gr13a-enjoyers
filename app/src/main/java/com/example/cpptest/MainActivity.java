package com.example.cpptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
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
        Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
        MainActivity.this.startActivity(myIntent);
        requestNecessaryPermissions();
    }


    public static native boolean authorizeUser(int option, String email, String password);

    public static native Object[] databaseQuery(int option, String beginDate, String endDate);

    public static Object[] databaseQuery(int option) {
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
        ButtonFragment.addTouchDelegate(findViewById(R.id.login_button).findViewById(R.id.button),this);
        ButtonFragment.addTouchDelegate(findViewById(R.id.signup_button).findViewById(R.id.button),this);
        ((AppCompatButton) findViewById(R.id.login_button).findViewById(R.id.button)).setText("Log in");
        ((AppCompatButton) findViewById(R.id.signup_button).findViewById(R.id.button)).setText("Sign up");
    }

    private void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {permission}, 1);
        }
    }

    private void requestNecessaryPermissions() {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
