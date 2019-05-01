package com.example.seniorseminarproject;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCreateEvent extends AppCompatActivity {

    public EditText eventNameET;
    public EditText eventDateET;
    public EditText eventTimeET;
    public EditText eventDescriptionET;
    public EditText pointsAwardedET;
    public Button saveEventButton;

    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_event);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        this.eventNameET = (EditText)this.findViewById(R.id.eventNameET);
        this.eventDateET = (EditText)this.findViewById(R.id.eventDateET);
        this.eventTimeET = (EditText)this.findViewById(R.id.eventTimeET);
        this.eventDescriptionET = (EditText)this.findViewById(R.id.eventDescriptionET);
        this.pointsAwardedET = (EditText)this.findViewById(R.id.pointsAwardedET);
        this.saveEventButton = (Button)this.findViewById(R.id.saveEventButton);
    }

    public void saveEventButtonPressed(View v){
        addEvent();
    }

    private void addEvent(){
        String eventName = eventNameET.getText().toString().trim();
        String eventDate = eventDateET.getText().toString().trim();
        String eventTime = eventTimeET.getText().toString().trim();
        String eventDescription = eventDescriptionET.getText().toString().trim();
        String pointsAwarded = pointsAwardedET.getText().toString().trim();
        int pointsAwardedInt = Integer.parseInt(pointsAwarded);

        if(!TextUtils.isEmpty(eventName) || !TextUtils.isEmpty(eventDate) || !TextUtils.isEmpty(eventTime) || !TextUtils.isEmpty(eventDescription) || !TextUtils.isEmpty(pointsAwarded)){
            String eventId = databaseEvents.push().getKey();

            Event event = new Event(eventId, eventName, eventDate, eventTime, eventDescription, pointsAwardedInt);

            databaseEvents.child(eventId).setValue(event);

            Toast.makeText(this, "Event Successfully Created", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please Enter All Information", Toast.LENGTH_LONG).show();
        }
    }
}
