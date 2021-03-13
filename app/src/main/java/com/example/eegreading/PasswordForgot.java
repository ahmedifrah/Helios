package com.example.eegreading;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordForgot extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText mEmail;
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forgot);

        mEmail = findViewById(R.id.EmailForgot);
        mSubmit = findViewById(R.id.submit);
        fAuth = FirebaseAuth.getInstance();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(PasswordForgot.this, "Reset Link has been sent to your email.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PasswordForgot.this, "Error! Reset Link is not Sent." + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void Back (View view){
        onBackPressed();
    }
}