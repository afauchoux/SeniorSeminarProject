package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserClaimReward extends AppCompatActivity {

    private Toolbar toolbarUserClaimReward;
    private TextView userClaimRewardThankYouTV;
    private ImageView claimRewardQRCodeImage;
    public Button userClaimRewardQRCodeButton;
    public Button userClaimRewardEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_claim_reward);

        toolbarUserClaimReward = (Toolbar)this.findViewById(R.id.toolbarUserClaimReward);
        toolbarUserClaimReward.setTitle("Claim Reward");
    }
}
