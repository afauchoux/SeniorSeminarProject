package com.example.seniorseminarproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    public TextView userFullNameTV;
    public DatabaseReference mDatabase;
    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.userFullNameTV = (TextView) this.findViewById(R.id.userFullNameTV);
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");

        userFullNameTV.setText(getFullName());

    }

    public String getFullName(){

        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return new String("hello world");
    }

}
