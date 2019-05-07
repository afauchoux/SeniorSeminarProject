package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AdminCreateReward extends AppCompatActivity {

    private Toolbar toolbarAdminCreateReward;

    public EditText rewardNameET;
    public EditText rewardCostET;
    public EditText rewardDescriptionET;
    public Button saveRewardButton;

    DatabaseReference databaseRewards;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseNewsfeed;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_reward);

        toolbarAdminCreateReward = (Toolbar)this.findViewById(R.id.toolbarAdminCreateReward);
        toolbarAdminCreateReward.setTitle("Create Reward");
        setSupportActionBar(toolbarAdminCreateReward);

        databaseRewards = FirebaseDatabase.getInstance().getReference("rewards");
        mAuth = FirebaseAuth.getInstance();
        databaseNewsfeed = FirebaseDatabase.getInstance().getReference("newsfeed");
        user = mAuth.getCurrentUser();

        this.rewardNameET = (EditText)this.findViewById(R.id.rewardNameET);
        this.rewardCostET = (EditText)this.findViewById(R.id.rewardCostET);
        this.rewardDescriptionET = (EditText)this.findViewById(R.id.rewardDescriptionET);
        this.saveRewardButton = (Button)this.findViewById(R.id.saveRewardButton);
    }

    public void saveRewardButtonPressed(View v){
        addReward();
        writeToNewsfeed();
    }

    private void addReward(){
        String rewardName = rewardNameET.getText().toString().trim();
        String rewardCost = rewardCostET.getText().toString().trim();
        String rewardDescription = rewardDescriptionET.getText().toString().trim();

        if(!TextUtils.isEmpty(rewardName) || !TextUtils.isEmpty(rewardCost) || !TextUtils.isEmpty(rewardDescription)){
            String rewardId = databaseRewards.push().getKey();

            Reward reward = new Reward(rewardId, rewardName, rewardCost, rewardDescription);

            databaseRewards.child(rewardId).setValue(reward);

            Toast.makeText(this, "Reward Successfully Created", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please Enter All Information", Toast.LENGTH_LONG).show();
        }
    }

    private void writeToNewsfeed(){
        userId = user.getUid();
        String username = user.getDisplayName();
        String reward = rewardNameET.getText().toString();
        String newsfeedId = databaseNewsfeed.push().getKey();
        String newsfeedEvent = username + " created the reward: " + reward + ".";
        Date currTime = Calendar.getInstance().getTime();
        String newsfeedTime = currTime.toString();

        Newsfeed newsfeed = new Newsfeed(newsfeedId, newsfeedEvent, newsfeedTime);

        databaseNewsfeed.child(newsfeedId).setValue(newsfeed);
    }
}
