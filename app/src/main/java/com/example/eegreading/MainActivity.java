package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity<layout_height, layout_width, text, onClick> extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button resendCode;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resendCode = findViewById(R.id.resendCode);
        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();


    }

    public void goActivity2 (View view){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        finish();
    }

    public void goEEG (View view){
        Intent intent = new Intent(this, eeg4.class);
        startActivity(intent);
        finish();

    }

    public void goQuiz (View view){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void GoSettings (View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void Signout (View view){
        Intent intent = new Intent(this, areyounew.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
        finish();
    }

    public void Hospital (View view){
        Intent intent = new Intent(this, Hospital.class);
        startActivity(intent);
        finish();
    }
}