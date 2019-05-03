package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCreateReward extends AppCompatActivity {

    public EditText rewardNameET;
    public EditText rewardCostET;
    public EditText rewardDescriptionET;
    public Button saveRewardButton;

    DatabaseReference databaseRewards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_reward);

        databaseRewards = FirebaseDatabase.getInstance().getReference("rewards");

        this.rewardNameET = (EditText)this.findViewById(R.id.rewardNameET);
        this.rewardCostET = (EditText)this.findViewById(R.id.rewardCostET);
        this.rewardDescriptionET = (EditText)this.findViewById(R.id.rewardDescriptionET);
        this.saveRewardButton = (Button)this.findViewById(R.id.saveRewardButton);
    }

    public void saveRewardButtonPressed(View v){
        addReward();
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
}
