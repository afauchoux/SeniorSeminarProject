package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class AdminViewAllUsers extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_users);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewAllUsers);
        new AdminUserDatabaseHelper().readUsers(new AdminUserDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<User> users, List<String> keys) {
                new AdminUserRecyclerViewConfig().setConfig(mRecyclerView, AdminViewAllUsers.this, users, keys);
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

//    public void adminEditUserButtonPressed(View v){
//        Intent i = new Intent(this, AdminEditUser.class);
//        this.startActivity(i);
//    }
}
