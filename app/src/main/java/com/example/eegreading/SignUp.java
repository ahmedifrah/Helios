package com.example.eegreading;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mConfirmPassword;
    Button mRegister;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Dialog mDialog;
    String userID;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDialog = new Dialog(this);
        mFullName = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.ConfirmPass);
        mRegister = findViewById(R.id.Register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);


        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                final String name = mFullName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmpassword =mConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Empty!");
                    return;
                }

                if (TextUtils.isEmpty(name)){
                    mFullName.setError("Name is Empty!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Empty!");
                    return;
                }

                if (password.length() < 6){
                    mPassword.setError("Password must be >= 6 Characters");
                }

                if (!password.equals(confirmpassword)){
                    mConfirmPassword.setError("Confirm password not equal to Password!");
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser fuser =  fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {  //sends email verification
                                    Toast.makeText(SignUp.this, "Verification Email has been sent.", Toast.LENGTH_SHORT).show(); //if successfully sent
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "OnFailure : Email not sent" + e.getMessage()); //if can't sent
                                }
                            });

                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            final DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created for"+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: "+ e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Signupdone.class)); //make the page

                        }else{
                            Toast.makeText(SignUp.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });
    }


    public void Login (View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void Popup (View view){
        mDialog.setContentView(R.layout.popup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

}