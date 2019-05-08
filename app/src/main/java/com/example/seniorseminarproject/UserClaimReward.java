package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserClaimReward extends AppCompatActivity {

    private Toolbar toolbarUserClaimReward;
    private TextView userClaimRewardThankYou;
    private ImageView userClaimRewardQRCodeImage;
    public Button userClaimRewardQRCodeButton;
    public Button userClaimRewardEmailButton;

    private String rewardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_claim_reward);

        toolbarUserClaimReward = (Toolbar)this.findViewById(R.id.toolbarUserClaimReward);
        toolbarUserClaimReward.setTitle("Claim Reward");
        setSupportActionBar(toolbarUserClaimReward);

        this.userClaimRewardThankYou = (TextView)this.findViewById(R.id.userClaimRewardThankYouTV);
        this.userClaimRewardQRCodeImage = (ImageView)this.findViewById(R.id.userClaimRewardQRCodeImage);
        this.userClaimRewardEmailButton = (Button)this.findViewById(R.id.userClaimRewardEmailButton);
        this.userClaimRewardQRCodeButton = (Button)this.findViewById(R.id.userClaimRewardQRCodeButton);

        rewardName = getIntent().getStringExtra("rewardName");
    }
}
