package com.example.seniorseminarproject;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {

    private Toolbar toolbarUserMainActivity;

    public Button userProfileButton;
    public Button userScanQRCodeButton;
    public Button userEventsButton;
    public Button userRewardsButton;
    private String scannedData;

    private DatabaseReference databaseNewsfeed;
    private DatabaseReference mRef;
    private DatabaseReference mPoints;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;
    private String points;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        toolbarUserMainActivity = (Toolbar)this.findViewById(R.id.toolbarMainActivity);
        toolbarUserMainActivity.setTitle("Incentivate");
        setSupportActionBar(toolbarUserMainActivity);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewNewsfeed);
        new NewsfeedDatabaseHelper().readNewsfeeds(new NewsfeedDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<Newsfeed> newsfeeds, List<String> keys) {
                new NewsfeedRecyclerViewConfig().setConfig(mRecyclerView, UserMainActivity.this, newsfeeds, keys);
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

        this.userProfileButton = (Button)this.findViewById(R.id.userProfileButton);
        this.userEventsButton = (Button)this.findViewById(R.id.userEventsButton);
        this.userRewardsButton = (Button)this.findViewById(R.id.userRewardsButton);
        userScanQRCodeButton = (Button)findViewById(R.id.userScanQRCodeButton);
        final Activity activity = this;
        userScanQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan QR Code");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        databaseNewsfeed = FirebaseDatabase.getInstance().getReference("newsfeed");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        mRef = FirebaseDatabase.getInstance().getReference("users");
        mPoints = mRef.child(userId).child("points");
        mPoints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                points = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void userRewardsButtonPressed(View v){
        Intent i = new Intent(this, UserRewards.class);
        this.startActivity(i);
    }

    public void userProfileButtonPressed(View v){
        Intent i = new Intent(this, UserProfile.class);
        this.startActivity(i);
    }

    public void userEventsButtonPressed(View v){
        Intent i = new Intent(this, UserEvents.class);
        this.startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                scannedData = result.getContents();
                writeToNewsfeed();
                updateUserPoints(scannedData);
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "You Cancelled The Scan", Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getUserPoints(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User user = new User();
            user.setPoints(ds.child(userId).getValue(User.class).getPoints());
        }
    }

    private void updateUserPoints(String scannedData){
        int scannedPointsInt = Integer.parseInt(scannedData);
        int pointsInt = Integer.parseInt(points);
        int newPointTotalInt = pointsInt + scannedPointsInt;
        String newPointTotal = Integer.toString(newPointTotalInt);

        mPoints.setValue(newPointTotal);
    }

    private void writeToNewsfeed(){
        userId = user.getUid();
        String username = user.getDisplayName();
        String pointsAdded = scannedData;
        String newsfeedId = databaseNewsfeed.push().getKey();
        String newsfeedEvent = username + " claimed " + pointsAdded + " points.";
        Date currTime = Calendar.getInstance().getTime();
        String newsfeedTime = currTime.toString();

        Newsfeed newsfeed = new Newsfeed(newsfeedId, newsfeedEvent, newsfeedTime);

        databaseNewsfeed.child(newsfeedId).setValue(newsfeed);
    }
}
