package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class EEGReading extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_e_g_reading);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
                    public void run(){
                Intent intent = new Intent(EEGReading.this, ProcessingData.class);
                startActivity(intent);
                finish();
            }
        }, 90000);
    }
}