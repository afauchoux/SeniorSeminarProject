package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {

    public Button createEventButton;
    public Button createRewardButton;
    public Button createQRCodeButton;
    public Button editEventButton;
    public Button editRewardButton;
    public Button editUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        this.createEventButton = (Button)this.findViewById(R.id.createEventButton);
        this.createRewardButton = (Button)this.findViewById(R.id.createRewardButton);
        this.createQRCodeButton = (Button)this.findViewById(R.id.createQRCodeButton);
        this.editEventButton = (Button)this.findViewById(R.id.editEventButton);
        this.editRewardButton = (Button)this.findViewById(R.id.editRewardButton);
        this.editUserButton = (Button)this.findViewById(R.id.editUserButton);
    }

    public void createEventButtonPressed(View v){
        Intent i = new Intent(this, AdminCreateEvent.class);
        this.startActivity(i);
    }

    public void createRewardButtonPressed(View v){
        Intent i = new Intent(this, AdminCreateReward.class);
        this.startActivity(i);
    }

    public void createQRCodeButtonPressed(View v){
        Intent i = new Intent(this, AdminCreateQRCode.class);
        this.startActivity(i);
    }

    public void editEventButtonPressed(View v){
        Intent i = new Intent(this, AdminViewAllEvents.class);
        this.startActivity(i);
    }

    public void editRewardButtonPressed(View v){
        Intent i = new Intent(this, AdminViewAllRewards.class);
        this.startActivity(i);
    }

    public void editUserButtonPressed(View v){
        Intent i = new Intent(this, AdminViewAllUsers.class);
        this.startActivity(i);
    }
}
