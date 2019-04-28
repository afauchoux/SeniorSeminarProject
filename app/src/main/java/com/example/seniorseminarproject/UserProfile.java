package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    public ImageButton userEditProfileButton;
    public ImageButton userProfileBackButton;
    public TextView usernameTV;
    public ImageView userProfileImage;

    FirebaseAuth mAuth;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.userEditProfileButton = (ImageButton)this.findViewById(R.id.userEditProfileButton);
        this.userProfileBackButton = (ImageButton)this.findViewById(R.id.userProfileBackButton);
        this.userProfileImage = (ImageView)this.findViewById(R.id.userProfileImage);
        this.usernameTV = (TextView)this.findViewById(R.id.usernameTV);

        mAuth = FirebaseAuth.getInstance();

        loadUserInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadUserInfo();
    }

    public void userEditProfileButtonPressed(View v){
        Intent i = new Intent(this, UserEditProfile.class);
        this.startActivity(i);
    }

    public void userProfileBackButtonPressed(View v){
        Intent i = new Intent(this, UserMainActivity.class);
        this.startActivity(i);
    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(userProfileImage);

            }
            if (user.getDisplayName() != null) {
                usernameTV.setText(user.getDisplayName());
            }

        }


    }
}
