package com.app.android.june.alcmeetup20.java;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.june.alcmeetup20.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main4Activity extends AppCompatActivity {
    TextView nameText, phoneText, emailText;

    String name, phone, email;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main2);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        emailText = findViewById(R.id.emailText);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        getDetails();
    }

    private void getDetails() {
        try {
            mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user details = dataSnapshot.getValue(user.class);
                    name = details.getName();
                    phone = details.getPhone();
                    email = details.getEmail();

                    nameText.setText("Name: " + name);
                    phoneText.setText("Phone: " + phone);
                    emailText.setText("Email: " + email);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(Main4Activity.this, "Error occured" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
