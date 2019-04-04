package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.enterButton = (Button)this.findViewById(R.id.enterButton);
    }

    public void enterButtonPressed(View v){
        Intent i = new Intent(this, UserLogin.class);
        this.startActivity(i);
    }
}
