package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AdminCreateEvent extends AppCompatActivity {

    private Toolbar toolbarAdminCreateEvent;

    public EditText eventNameET;
    public EditText eventDateET;
    public EditText eventTimeET;
    public EditText eventLocationET;
    public EditText eventDescriptionET;
    public EditText eventPointsET;
    public Button saveEventButton;

    private DatabaseReference databaseEvents;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseNewsfeed;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_event);

        toolbarAdminCreateEvent = (Toolbar) this.findViewById(R.id.toolbarAdminCreateEvent);
        toolbarAdminCreateEvent.setTitle("Create Event");
        setSupportActionBar(toolbarAdminCreateEvent);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        mAuth = FirebaseAuth.getInstance();
        databaseNewsfeed = FirebaseDatabase.getInstance().getReference("newsfeed");
        user = mAuth.getCurrentUser();

        this.eventNameET = (EditText)this.findViewById(R.id.eventNameET);
        this.eventDateET = (EditText)this.findViewById(R.id.eventDateET);
        this.eventTimeET = (EditText)this.findViewById(R.id.eventTimeET);
        this.eventLocationET = (EditText)this.findViewById(R.id.eventLocationET);
        this.eventDescriptionET = (EditText)this.findViewById(R.id.eventDescriptionET);
        this.eventPointsET = (EditText)this.findViewById(R.id.eventPointsET);
        this.saveEventButton = (Button)this.findViewById(R.id.saveEventButton);
    }

    public void saveEventButtonPressed(View v){
        addEvent();
        writeToNewsfeed();
    }

    private void addEvent(){
        String eventName = eventNameET.getText().toString().trim();
        String eventDate = eventDateET.getText().toString().trim();
        String eventTime = eventTimeET.getText().toString().trim();
        String eventLocation = eventLocationET.getText().toString().trim();
        String eventDescription = eventDescriptionET.getText().toString().trim();
        String eventPoints = eventPointsET.getText().toString().trim();
        //int pointsAwardedInt = Integer.parseInt(pointsAwarded);

        if(!TextUtils.isEmpty(eventName) || !TextUtils.isEmpty(eventDate) || !TextUtils.isEmpty(eventTime) || !TextUtils.isEmpty(eventLocation) || !TextUtils.isEmpty(eventDescription) || !TextUtils.isEmpty(eventPoints)){
            String eventId = databaseEvents.push().getKey();

            Event event = new Event(eventId, eventName, eventDate, eventTime, eventLocation, eventDescription, eventPoints);

            databaseEvents.child(eventId).setValue(event);

            Toast.makeText(this, "Event Successfully Created", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please Enter All Information", Toast.LENGTH_LONG).show();
        }
    }

    private void writeToNewsfeed(){
        userId = user.getUid();
        String username = user.getDisplayName();
        String event = eventNameET.getText().toString();
        String newsfeedId = databaseNewsfeed.push().getKey();
        String newsfeedEvent = username + " created the event: " + event + ".";
        Date currTime = Calendar.getInstance().getTime();
        String newsfeedTime = currTime.toString();

        Newsfeed newsfeed = new Newsfeed(newsfeedId, newsfeedEvent, newsfeedTime);

        databaseNewsfeed.child(newsfeedId).setValue(newsfeed);
    }
}
