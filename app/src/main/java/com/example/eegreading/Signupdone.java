package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Signupdone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupdone);
    }

    public void Login (View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}