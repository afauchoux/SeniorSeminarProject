package com.example.seniorseminarproject;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class AdminCreateQRCode extends AppCompatActivity {

    public EditText generatePointsET;
    public Button generateQRCodeButton;
    public ImageView qrCodeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_qrcode);

        this.generatePointsET = (EditText)this.findViewById(R.id.generatePointsET);
        this.generateQRCodeButton = (Button)this.findViewById(R.id.generateQRCodeButton);
        this.qrCodeImage = (ImageView)this.findViewById(R.id.qrCodeImage);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String points = generatePointsET.getText().toString();

                MultiFormatWriter mfw = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = mfw.encode(points, BarcodeFormat.QR_CODE, 200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCodeImage.setImageBitmap(bitmap);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
