package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AdminEditReward extends AppCompatActivity {

    private Toolbar toolbarAdminEditReward;

    private EditText editRewardName;
    private EditText editRewardCost;
    private EditText editRewardDescription;
    private Button updateRewardButton;
    private Button deleteRewardButton;

    private String key;
    private String rewardName;
    private String rewardCost;
    private String rewardDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_reward);

        toolbarAdminEditReward = (Toolbar)this.findViewById(R.id.toolbarAdminEditReward);
        toolbarAdminEditReward.setTitle("Edit Reward");
        setSupportActionBar(toolbarAdminEditReward);

        key = getIntent().getStringExtra("key");
        rewardName = getIntent().getStringExtra("rewardName");
        rewardCost = getIntent().getStringExtra("rewardCost");
        rewardDescription = getIntent().getStringExtra("rewardDescription");

        this.editRewardName = (EditText)this.findViewById(R.id.editRewardNameET);
        editRewardName.setText(rewardName);
        this.editRewardCost = (EditText)this.findViewById(R.id.editRewardCostET);
        editRewardCost.setText(rewardCost);
        this.editRewardDescription = (EditText)this.findViewById(R.id.editRewardDescriptionET);
        editRewardDescription.setText(rewardDescription);
        this.updateRewardButton = (Button)this.findViewById(R.id.updateRewardButton);
        this.deleteRewardButton = (Button)this.findViewById(R.id.deleteRewardButton);

        updateRewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Reward reward = new Reward();
            reward.setRewardName(editRewardName.getText().toString());
            reward.setRewardCost(editRewardCost.getText().toString());
            reward.setRewardDescription(editRewardDescription.getText().toString());

            new AdminRewardDatabaseHelper().updateReward(key, reward, new AdminRewardDatabaseHelper.DataStatus() {
                @Override
                public void DataLoaded(List<Reward> rewards, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {
                    Toast.makeText(AdminEditReward.this, "Reward Has Been Updated", Toast.LENGTH_LONG).show();
                }

                @Override
                public void DataIsDeleted() {

                }
            });
            returnToMain();
            }
        });

        deleteRewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new AdminRewardDatabaseHelper().deleteReward(key, new AdminRewardDatabaseHelper.DataStatus() {
                @Override
                public void DataLoaded(List<Reward> rewards, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {

                }

                @Override
                public void DataIsDeleted() {
                    Toast.makeText(AdminEditReward.this, "Reward Was Deleted", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
            });
            returnToMain();
            }
        });
    }

    private void returnToMain(){
        Intent i = new Intent(this, AdminMainActivity.class);
        this.startActivity(i);
    }
}
