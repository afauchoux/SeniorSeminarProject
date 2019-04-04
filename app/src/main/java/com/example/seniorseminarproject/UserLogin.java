package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogin extends AppCompatActivity {

    public Button userLoginButton;
    public TextView userCreateAccountTextPressed;
    public TextView userForgotPasswordTextPressed;
    public TextView adminLoginTextPressed;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        this.userLoginButton = (Button)this.findViewById(R.id.userLoginButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void userCreateAccountTextPressed(View v){
        Intent i = new Intent(this, UserCreateAccount.class);
        this.startActivity(i);
    }

    public void userLoginButtonPressed(View v){
        Intent i = new Intent(this, UserMainActivity.class);
        this.startActivity(i);
    }

    public void userForgotPasswordTextPressed(View v){
        Intent i = new Intent(this, UserForgotPassword.class);
        this.startActivity(i);
    }

    public void adminLoginTextPressed(View v){
        Intent i = new Intent(this, AdminLogin.class);
        this.startActivity(i);
    }
}
