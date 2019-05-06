package com.example.seniorseminarproject;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UserMainActivity extends AppCompatActivity {

    public TextView userUsername;
    public TextView userTotalPoints;
    public Button userProfileButton;
    public Button userScanQRCodeButton;
    public Button userEventsButton;
    public Button userRewardsButton;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private String userId;
    private DatabaseReference mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        mRef = FirebaseDatabase.getInstance().getReference("users");
        mPoints = mRef.child(userId).child("points");

        this.userUsername = (TextView)this.findViewById(R.id.userUsernameTV);
        this.userTotalPoints = (TextView)this.findViewById(R.id.userTotalPointsTV);
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

        mPoints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String points = dataSnapshot.getValue(String.class);
                userTotalPoints.setText(points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        loadUserInfo();
    }

    public void testButtonPressed(View v){
        Intent i = new Intent(this, UserEditProfile.class);
        this.startActivity(i);
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
            if(result.getContents() == null){
                Toast.makeText(this, "You Cancelled The Scan", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            if (user.getDisplayName() != null) {
                userUsername.setText(user.getDisplayName());
            }
        }
    }
}
