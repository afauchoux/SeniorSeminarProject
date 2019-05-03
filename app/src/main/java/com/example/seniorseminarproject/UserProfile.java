package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    public ImageButton userEditProfileButton;
    public ImageButton userProfileBackButton;
    public TextView usernameTV;
    public  TextView userFullNameTV;
    public TextView userPointsTV;
    public ImageView userProfileImage;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userId;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.userEditProfileButton = (ImageButton)this.findViewById(R.id.userEditProfileButton);
        this.userProfileBackButton = (ImageButton)this.findViewById(R.id.userProfileBackButton);
        this.userProfileImage = (ImageView)this.findViewById(R.id.userProfileImage);
        this.usernameTV = (TextView)this.findViewById(R.id.usernameTV);
        this.userFullNameTV = (TextView)this.findViewById(R.id.userFullNameTV);
        this.userPointsTV = (TextView)this.findViewById(R.id.userPointsTV);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        loadUserInfo();
    }

    private void showData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User user = new User();
            user.setFirstName(ds.child(userId).getValue(User.class).getFirstName());
            user.setLastName(ds.child(userId).getValue(User.class).getLastName());
            user.setPoints(ds.child(userId).getValue(User.class).getPoints());

            ArrayList<String> array = new ArrayList<>();
            array.add(user.getFirstName());
            array.add(user.getLastName());
            //array.add(user.getPoints());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadUserInfo();
    }

    public void userEditProfileButtonPressed(View v){
        Intent i = new Intent(this, UserEditProfile.class);
        this.startActivity(i);
    }

    public void userProfileBackButtonPressed(View v){
        Intent i = new Intent(this, UserMainActivity.class);
        this.startActivity(i);
    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(userProfileImage);

            }
        }


    }
}
