package com.example.seniorseminarproject;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminRewardDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceRewards;
    private List<Reward> rewards = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<Reward> rewards, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public AdminRewardDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceRewards = mDatabase.getReference("rewards");
    }

    public void readRewards(final DataStatus dataStatus){
        mReferenceRewards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rewards.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Reward reward = keyNode.getValue(Reward.class);
                    rewards.add(reward);
                }
                dataStatus.DataLoaded(rewards, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateReward(String key, Reward reward, final DataStatus dataStatus){
        mReferenceRewards.child(key).setValue(reward).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteReward(String key, final DataStatus dataStatus){
        mReferenceRewards.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
