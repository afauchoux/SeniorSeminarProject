package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMainActivity extends AppCompatActivity {

    public Button userProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        this.userProfileButton = (Button)this.findViewById(R.id.userProfileButton);
    }

    public void userProfileButtonPressed(View v){
        Intent i = new Intent(this, UserProfile.class);
        this.startActivity(i);
    }
}
