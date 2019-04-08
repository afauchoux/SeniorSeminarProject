package com.example.seniorseminarproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLogin extends AppCompatActivity {

    public Button userLoginButton;
    public TextView userCreateAccountTextPressed;
    public TextView userForgotPasswordTextPressed;
    public TextView adminLoginTextPressed;
    public EditText userUsernameET;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        this.userLoginButton = (Button)this.findViewById(R.id.userLoginButton);
        this.userUsernameET = (EditText)this.findViewById(R.id.userUsernameET);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void userCreateAccountTextPressed(View v){
        Intent i = new Intent(this, UserCreateAccount.class);
        this.startActivity(i);
    }

    public void userLoginButtonPressed(View v){
        String username = this.userUsernameET.getText().toString().trim();

            Toast.makeText(this, "Successful Login", Toast.LENGTH_SHORT).show();
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
