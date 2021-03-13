package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class areyounew extends AppCompatActivity {

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areyounew);
    }

    public void Login (View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void Signup (View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
}