package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    public ImageButton userEditProfileButton;
    public TextView usernameTV;
    public ImageView profileImage;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.userEditProfileButton = (ImageButton)this.findViewById(R.id.userEditProfileButton);
        this.profileImage = (ImageView)this.findViewById(R.id.profileImage);
        this.usernameTV = (TextView)this.findViewById(R.id.usernameTV);

        mAuth = FirebaseAuth.getInstance();

        loadUserInfo();
    }

    public void userEditProfileButtonPressed(View v){
        Intent i = new Intent(this, UserEditProfile.class);
        this.startActivity(i);
    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(profileImage);

            }

            if (user.getDisplayName() != null) {
                usernameTV.setText(user.getDisplayName());
            }
        }
    }
}
