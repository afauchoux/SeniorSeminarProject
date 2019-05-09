package com.example.seniorseminarproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;

public class AdminCreateQRCode extends AppCompatActivity {

    private Toolbar toolbarAdminCreateQRCode;

    public EditText generatePointsET;
    public Button generateQRCodeButton;
    public Button saveQRCodeButton;
    public ImageView qrCodeImage;

    private static final int CHOOSE_QRCODE = 101;
    public Uri qrCode;
    public String qrCodeUrl;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_qrcode);

        toolbarAdminCreateQRCode = (Toolbar)this.findViewById(R.id.toolbarAdminCreateQRCode);
        toolbarAdminCreateQRCode.setTitle("Create QR Code");
        setSupportActionBar(toolbarAdminCreateQRCode);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        this.generatePointsET = (EditText)this.findViewById(R.id.generatePointsET);
        this.generateQRCodeButton = (Button)this.findViewById(R.id.generateQRCodeButton);
        this.saveQRCodeButton = (Button)this.findViewById(R.id.saveQRCodeButton);
        this.qrCodeImage = (ImageView) this.findViewById(R.id.qrCodeImage);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String points = generatePointsET.getText().toString();

                MultiFormatWriter mfw = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = mfw.encode(points, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCodeImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveQRCodeButtonPressed(View v){
        uploadQRCodeToFirebaseStorage();
        returnToMain();
    }

    private void uploadQRCodeToFirebaseStorage() {

    }

    private void returnToMain(){
        Intent i = new Intent(this, AdminMainActivity.class);
        this.startActivity(i);
    }
}
