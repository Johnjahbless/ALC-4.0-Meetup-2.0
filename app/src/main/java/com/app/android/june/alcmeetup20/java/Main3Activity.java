package com.app.android.june.alcmeetup20.java;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.android.june.alcmeetup20.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {
    private EditText editName, editPhone, editEmail;

    private String Name, Phone, Email;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.name);
        editPhone = findViewById(R.id.phone);
        editEmail = findViewById(R.id.email);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void submitData(View view){
        Name = editName.getText().toString();
        Phone = editPhone.getText().toString();
        Email = editEmail.getText().toString();

        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else {
            sendToDatabase(Name, Phone, Email);
        }

    }

    private void sendToDatabase(String Name, String Phone, String Email) {
        user details = new user(Name, Phone, Email);
        mDatabase.child("users").setValue(details).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        displayDialog();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Main3Activity.this, "An error has occured" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void displayDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main3Activity.this);
        alertDialogBuilder.setTitle("Data sent successfully");
        alertDialogBuilder
                .setCancelable(false)
                .setIcon(R.drawable.ic_done)
                .setMessage("You have sent your details to firebase database")
                .setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                editName.setText(" ");
                                editPhone.setText(" ");
                                editEmail.setText(" ");
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void goToNextActivity(View view) {
        Intent intent =  new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
}
