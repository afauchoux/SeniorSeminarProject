package com.example.seniorseminarproject;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceEvents;
    private List<Event> events = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<Event> events, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public EventDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceEvents = mDatabase.getReference("events");
    }

    public void readEvents(final DataStatus dataStatus){
        mReferenceEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Event event = keyNode.getValue(Event.class);
                    events.add(event);
                }
                dataStatus.DataLoaded(events, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
