package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class EEG extends AppCompatActivity implements ViewTreeObserver.OnScrollChangedListener {

    ScrollView sv_eeg;
    Button buttonpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_e_g);

        sv_eeg = (ScrollView) findViewById(R.id.sv_eeg);
        buttonpage = (Button) findViewById(R.id.buttonpage);
        sv_eeg.getViewTreeObserver().addOnScrollChangedListener(this);

    }

    @Override
            public void onScrollChanged() {
        View view = (View) sv_eeg.getChildAt(sv_eeg.getChildCount() - 1);
        int bottomDetector = view.getBottom() - (sv_eeg.getHeight() + sv_eeg.getScrollY());

        if (bottomDetector <= 0) {
            buttonpage.setVisibility(View.VISIBLE);
        }
        else {
            buttonpage.setVisibility(View.GONE);
        }
    }

    public void Home (View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
    }

    public void EEGRead (View view){
        Intent intent = new Intent(this, eeg2.class);
        startActivity(intent);
    }

}