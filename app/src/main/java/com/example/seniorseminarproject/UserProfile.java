package com.example.seniorseminarproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    private Toolbar toolbarUserProfile;

    public TextView userUsername;
    public TextView userTotalPoints;
    public ImageButton editProfileButton;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private String userId;
    private DatabaseReference mPoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbarUserProfile = (Toolbar)this.findViewById(R.id.toolbarUserProfile);
        toolbarUserProfile.setTitle("Profile");
        setSupportActionBar(toolbarUserProfile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        mRef = FirebaseDatabase.getInstance().getReference("users");
        mPoints = mRef.child(userId).child("points");

        this.userUsername = (TextView)this.findViewById(R.id.userUsernameTV);
        this.userTotalPoints = (TextView)this.findViewById(R.id.userTotalPointsTV);
        this.editProfileButton = (ImageButton)this.findViewById(R.id.editProfileButton);

        mPoints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String points = dataSnapshot.getValue(String.class);
                userTotalPoints.setText(points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        loadUserInfo();
    }

    public void editProfileButtonPressed(View v){
        Intent i = new Intent(this, UserEditProfile.class);
        this.startActivity(i);
    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            if (user.getDisplayName() != null) {
                userUsername.setText(user.getDisplayName());
            }
        }
    }

}
