package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class AdminViewAllRewards extends AppCompatActivity {

    private Toolbar toolbarAdminRewards;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_rewards);

        toolbarAdminRewards = (Toolbar)this.findViewById(R.id.toolbarAdminRewards);
        toolbarAdminRewards.setTitle("Edit Rewards");
        setSupportActionBar(toolbarAdminRewards);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewAllRewards);
        new AdminRewardDatabaseHelper().readRewards(new AdminRewardDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<Reward> rewards, List<String> keys) {
                new AdminRewardRecyclerViewConfig().setConfig(mRecyclerView, AdminViewAllRewards.this, rewards, keys);
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

//    public void adminEditRewardButtonPressed(View v){
//        Intent i = new Intent(this, AdminEditReward.class);
//        this.startActivity(i);
//    }
}
