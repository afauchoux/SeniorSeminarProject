package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class UserRewards extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rewards);
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
}
