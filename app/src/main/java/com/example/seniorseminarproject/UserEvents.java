package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class UserEvents extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewRewards);
        new EventDatabaseHelper().readEvents(new EventDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<Event> events, List<String> keys) {
                new EventRecyclerViewConfig().setConfig(mRecyclerView, UserEvents.this, events, keys);
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
