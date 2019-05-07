package com.example.seniorseminarproject;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceNewsfeeds;
    private List<Newsfeed> newsfeeds = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<Newsfeed> newsfeeds, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public NewsfeedDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceNewsfeeds = mDatabase.getReference("newsfeed");
    }

    public void readNewsfeeds(final NewsfeedDatabaseHelper.DataStatus dataStatus){
        mReferenceNewsfeeds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsfeeds.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Newsfeed newsfeed = keyNode.getValue(Newsfeed.class);
                    newsfeeds.add(newsfeed);
                }
                dataStatus.DataLoaded(newsfeeds, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
