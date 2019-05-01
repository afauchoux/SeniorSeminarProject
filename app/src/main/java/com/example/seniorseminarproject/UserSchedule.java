package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSchedule extends AppCompatActivity {

    public ImageButton userEventsBackButton;
    public ListView eventsLV;
    List<Event> eventList;

    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        this.userEventsBackButton = (ImageButton)this.findViewById(R.id.userEventsBackButton);
        this.eventsLV = (ListView)this.findViewById(R.id.eventsLV);

        eventList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                eventList.clear();
                for(DataSnapshot eventSnapshot: dataSnapshot.getChildren()){
                    Event event = eventSnapshot.getValue(Event.class);

                    eventList.add(event);
                }

                EventList adapter = new EventList(UserSchedule.this, eventList);
                eventsLV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void userEventsBackButtonPressed(View v){
        Intent i = new Intent(this, UserMainActivity.class);
        this.startActivity(i);
    }
}
