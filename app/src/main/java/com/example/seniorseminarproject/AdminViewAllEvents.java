package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class AdminViewAllEvents extends AppCompatActivity {

    private Toolbar toolbarAdminEvents;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_events);

        toolbarAdminEvents = (Toolbar)this.findViewById(R.id.toolbarAdminEvents);
        toolbarAdminEvents.setTitle("Edit Events");
        setSupportActionBar(toolbarAdminEvents);

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
