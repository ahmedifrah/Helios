package com.example.eegreading;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class eeg4 extends AppCompatActivity {
    //a constant to track the file chooser intent
    public static final String TAG = "TAG";
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int PICK_IMAGE_REQUEST = 234;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID, headset;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText othertext;

    Button buttonUpload, buttonChoose, buttonSubmit;
    TextView tv_output;
    static TextView displayresult;
    private Uri filePath;
    private StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("files");
    String selectedheadset = "Neurosky MindWave Mobile";

    Dialog mDialog, fDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeg4);

        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        //getting views from layout
        mDialog = new Dialog(this);
        fDialog = new Dialog(this);

        mDialog.setContentView(R.layout.popup2);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }*/
        buttonUpload = (Button) findViewById(R.id.buttonUpload2);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);


        //attaching listener
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                retrieveAndStoreToken();
                showoptionsDialog();
                /*fDialog.setContentView(R.layout.popup3);
                fDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                fDialog.show();
                buttonSubmit = (Button)findViewById(R.id.submit2);
                buttonSubmit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        radioButton = findViewById(radioId);
                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String,Object> fuser = new HashMap<>();
                        fuser.put("Headset",radioButton.getText());
                        documentReference.set(fuser, SetOptions.merge());
                    }
                });*/
            }
        });


    }

    //select file from the storage
    private void performFileSearch() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }


    private void showoptionsDialog() {
        final String[] headset = {"Neurosky MindWave Mobile", "OpenBCI", "Muse", "Emotiv"};
        AlertDialog.Builder builder = new AlertDialog.Builder(eeg4.this);
        builder.setTitle("Which commercial EEG headset you used to extract the EEG data?");
        builder.setSingleChoiceItems(headset, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedheadset = headset[which];
            }
        });
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> fuser = new HashMap<>();
                fuser.put("Headset", selectedheadset);
                documentReference.set(fuser, SetOptions.merge());
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // read content of the file
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            final StorageReference riversRef = storageReference.child("file");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Model model = new Model(uri.toString());
                                    String modelID = myRef.push().getKey();
                                    myRef.child(modelID).setValue(model);
                                    userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);


                                    Map<String, Object> fuser = new HashMap<>();
                                    fuser.put("FileUrl", model);
                                    documentReference.set(fuser, SetOptions.merge());
                                    Toast.makeText(eeg4.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }
    }


    private void retrieveAndStoreToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String, Object> fuser = new HashMap<>();
                        fuser.put("FCM Key", token);
                        documentReference.set(fuser, SetOptions.merge());

                    }
                });
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

