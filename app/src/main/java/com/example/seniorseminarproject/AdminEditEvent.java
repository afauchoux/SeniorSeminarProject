package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AdminEditEvent extends AppCompatActivity {

    private EditText editEventName;
    private EditText editEventDate;
    private EditText editEventTime;
    private EditText editEventLocation;
    private EditText editEventDescription;
    private EditText editEventPoints;
    private Button updateEventButton;
    private Button deleteEventButton;

    private String key;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private String eventPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_event);

        key = getIntent().getStringExtra("key");
        eventName = getIntent().getStringExtra("eventName");
        eventDate = getIntent().getStringExtra("eventDate");
        eventTime = getIntent().getStringExtra("eventTime");
        eventLocation = getIntent().getStringExtra("eventLocation");
        eventDescription = getIntent().getStringExtra("eventDescription");
        eventPoints = getIntent().getStringExtra("eventPoints");

        this.editEventName = (EditText)this.findViewById(R.id.editEventNameET);
        editEventName.setText(eventName);
        this.editEventDate = (EditText)this.findViewById(R.id.editEventDateET);
        editEventDate.setText(eventDate);
        this.editEventTime = (EditText)this.findViewById(R.id.editEventTimeET);
        editEventTime.setText(eventTime);
        this.editEventLocation = (EditText)this.findViewById(R.id.editEventLocationET);
        editEventLocation.setText(eventLocation);
        this.editEventDescription = (EditText)this.findViewById(R.id.editEventDescriptionET);
        editEventDescription.setText(eventDescription);
        this.editEventPoints = (EditText)this.findViewById(R.id.editEventPointsET);
        editEventPoints.setText(eventPoints);
        this.updateEventButton = (Button)this.findViewById(R.id.updateEventButton);
        this.deleteEventButton = (Button)this.findViewById(R.id.deleteEventButton);

        updateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.setEventName(editEventName.getText().toString());
                event.setEventDate(editEventDate.getText().toString());
                event.setEventTime(editEventTime.getText().toString());
                event.setEventLocation(editEventLocation.getText().toString());
                event.setEventDescription(editEventDescription.getText().toString());
                event.setEventPoints(editEventPoints.getText().toString());

                new AdminEventDatabaseHelper().updateEvent(key, event, new AdminEventDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<Event> events, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AdminEditEvent.this, "Event Has Been Updated", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        deleteEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AdminEventDatabaseHelper().deleteEvent(key, new AdminEventDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<Event> events, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AdminEditEvent.this, "Event Was Deleted", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });
            }
        });
    }
}
