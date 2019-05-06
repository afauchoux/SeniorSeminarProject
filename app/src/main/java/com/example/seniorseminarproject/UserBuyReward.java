package com.example.seniorseminarproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserBuyReward extends AppCompatActivity {

    private TextView mRewardBuyName;
    private TextView mRewardBuyDescription;
    private TextView mRewardBuyCost;
    private TextView mRewardBuyYourPoints;
    private Button mRewardFinalBuyButton;

    private DatabaseReference mRef;
    private DatabaseReference mPoints;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    private String key;
    private String rewardBuyName;
    private String rewardBuyDescription;
    private String rewardBuyCost;
    private String rewardBuyYourPoints;

    private String cost;
    private String payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_buy_reward);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        mRef = FirebaseDatabase.getInstance().getReference("users");
        mPoints = mRef.child(userId).child("points");

        key = getIntent().getStringExtra("key");
        rewardBuyName = getIntent().getStringExtra("rewardName");
        rewardBuyDescription = getIntent().getStringExtra("rewardDescription");
        rewardBuyCost = getIntent().getStringExtra("rewardCost");

        this.mRewardBuyName = (TextView)this.findViewById(R.id.rewardBuyNameTV);
        mRewardBuyName.setText(rewardBuyName);
        this.mRewardBuyDescription = (TextView)this.findViewById(R.id.rewardBuyDescriptionTV);
        mRewardBuyDescription.setText(rewardBuyDescription);
        this.mRewardBuyCost = (TextView)this.findViewById(R.id.rewardBuyCostTV);
        mRewardBuyCost.setText(rewardBuyCost);
        this.mRewardBuyYourPoints = (TextView)this.findViewById(R.id.rewardBuyYourPointsTV);
        this.mRewardFinalBuyButton = (Button)this.findViewById(R.id.rewardFinalBuyButton);

        mPoints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String points = dataSnapshot.getValue(String.class);
                mRewardBuyYourPoints.setText(points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUserPoints(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User user = new User();
            user.setPoints(ds.child(userId).getValue(User.class).getPoints());
        }
    }

    public void rewardFinalBuyButtonPressed(View v){
        buyReward(cost, payment);
    }

    private void buyReward(String cost, String payment){
        cost = mRewardBuyCost.getText().toString();
        payment = mRewardBuyYourPoints.getText().toString();
        int costInt = Integer.parseInt(cost);
        int paymentInt = Integer.parseInt(payment);

        if(paymentInt >= costInt){
            int afterPaymentInt = paymentInt - costInt;
            String afterPayment = Integer.toString(afterPaymentInt);
            mPoints.setValue(afterPayment);
        }
        else{
            Toast.makeText(this, "You Do Not Have Enough Points For This Reward", Toast.LENGTH_LONG).show();
        }
    }


}
