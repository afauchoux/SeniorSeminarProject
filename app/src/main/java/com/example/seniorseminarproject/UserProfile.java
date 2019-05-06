package com.example.seniorseminarproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_rewards);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewProfile);
        new UserProfileDatabaseHelper().readUsers(new UserProfileDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<User> users, List<String> keys) {
                new UserProfileRecyclerViewConfig().setConfig(mRecyclerView, UserProfile.this, users, keys);
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
