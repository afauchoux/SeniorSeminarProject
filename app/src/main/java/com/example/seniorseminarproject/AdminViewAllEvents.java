package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class AdminViewAllEvents extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_events);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewAllEvents);
        new AdminEventDatabaseHelper().readEvents(new AdminEventDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<Event> events, List<String> keys) {
                new AdminEventRecyclerViewConfig().setConfig(mRecyclerView, AdminViewAllEvents.this, events, keys);
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

//    public void adminEditEventButtonPressed(View v){
//        Intent i = new Intent(this, AdminEditEvent.class);
//        this.startActivity(i);
//    }
}
