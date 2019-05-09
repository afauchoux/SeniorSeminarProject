package com.example.seniorseminarproject;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

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

        rewardName = getIntent().getStringExtra("rewardName");

        this.userClaimRewardThankYou = (TextView)this.findViewById(R.id.userClaimRewardThankYouTV);
        userClaimRewardThankYou.setText("Thank you for purchasing " + rewardName + ". Click the GENERATE button to generate a QR Code and claim your reward now. If you want to claim your reward later click the EMAIL button and an email will be sent to your account with the QR Code.");
        this.userClaimRewardQRCodeImage = (ImageView)this.findViewById(R.id.userClaimRewardQRCodeImage);
        this.userClaimRewardEmailButton = (Button)this.findViewById(R.id.userClaimRewardEmailButton);
        this.userClaimRewardQRCodeButton = (Button)this.findViewById(R.id.userClaimRewardQRCodeButton);
        userClaimRewardQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reward = rewardName;
                MultiFormatWriter mfw = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = mfw.encode(reward, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    userClaimRewardQRCodeImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        rewardName = getIntent().getStringExtra("rewardName");
    }
}
