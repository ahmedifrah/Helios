package com.example.eegreading;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    EditText mPasswordNew, mPasswordConfirm;
    Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPasswordNew = findViewById(R.id.EditNewPassword);
        mPasswordConfirm = findViewById(R.id.EditNewConfirmPassword);
        mConfirmButton = findViewById(R.id.Confirm);
        fAuth = FirebaseAuth.getInstance();


        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = mPasswordNew.getText().toString().trim();
                String confirmPassword = mPasswordConfirm.getText().toString().trim();
                FirebaseUser user = fAuth.getCurrentUser();

                if (!(newPassword.isEmpty()) && newPassword.equals(confirmPassword)) {
                    user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SettingsActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();//successfully change pass
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SettingsActivity.this, "Password not changed", Toast.LENGTH_SHORT).show();//failed to change pass
                        }
                    });
                }
            }
        });
    }

    public void Back (View view){
        onBackPressed();
    }
}