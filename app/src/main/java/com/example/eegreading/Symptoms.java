package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Symptoms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
    }

    public void goBack (View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        finish();
    }
}