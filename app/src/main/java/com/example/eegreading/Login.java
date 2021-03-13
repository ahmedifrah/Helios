package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;
    EditText mEmail, mPassword;
    ProgressBar progressbar;
    Button mLoginBtn, resendCode, btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.EmailText);
        mPassword = findViewById(R.id.passwordtext);
        mLoginBtn = findViewById(R.id.loginbtn);
        progressbar = findViewById(R.id.progressBar2);
        resendCode = findViewById(R.id.resendCode);
        fAuth = FirebaseAuth.getInstance();


        if (fAuth.getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        }


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Empty!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Empty!");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            if (!user.isEmailVerified()) {
                                Toast.makeText(Login.this, "Logged in successfully, But Email not verified!", Toast.LENGTH_SHORT).show();
                                resendCode.setVisibility(View.VISIBLE);
                                resendCode.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FirebaseUser fuser = fAuth.getCurrentUser();
                                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(Login.this, "Verification Email has been sent.", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("tag", "OnFailure : Email not sent" + e.getMessage());
                                            }
                                        });
                                    }
                                });
                            } else {
                                Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }

                        } else {
                            Toast.makeText(Login.this, "Error!\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public void Signup(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }

    public void Forgot(View view) {
        Intent intent = new Intent(this, PasswordForgot.class);
        startActivity(intent);
    }


}
