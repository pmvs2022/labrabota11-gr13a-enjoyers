package com.example.cpptest;

import android.app.Fragment;
import android.app.FragmentContainer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.FloatRange;
import androidx.appcompat.widget.AppCompatButton;

public class ButtonSecond extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.button_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        AppCompatButton button = (AppCompatButton)getView().findViewById(R.id.button);
        button.setText("Second option");
    }
}
