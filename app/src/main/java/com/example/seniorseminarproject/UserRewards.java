package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserRewards extends AppCompatActivity {

    private Toolbar toolbarUserRewards;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rewards);

        toolbarUserRewards = (Toolbar)this.findViewById(R.id.toolbarUserRewards);
        toolbarUserRewards.setTitle("Rewards");
        setSupportActionBar(toolbarUserRewards);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewRewards);
        new RewardDatabaseHelper().readRewards(new RewardDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<Reward> rewards, List<String> keys) {
                new RewardRecyclerViewConfig().setConfig(mRecyclerView, UserRewards.this, rewards, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

//    public void rewardBuyButtonPressed(View v){
//        Intent i = new Intent(this, UserBuyReward.class);
//        this.startActivity(i);
//    }
}
