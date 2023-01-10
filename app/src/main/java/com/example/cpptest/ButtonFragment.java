package com.example.cpptest;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;

public class ButtonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.button_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        addTouchDelegate(getActivity().findViewById(R.id.button));
    }

    private void addTouchDelegate(View button){
        final View parent = (View) button.getParent();
        parent.post(new Runnable() {
            @Override
            public void run() {
                final Rect r = new Rect();
                button.getHitRect(r);
                r.bottom +=50;
                r.top -=50;
                r.left -=50;
                r.right +=50;
                parent.setTouchDelegate(new TouchDelegate(r,button));
            }
        });

    }
}
