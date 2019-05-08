package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserForgotPassword extends AppCompatActivity {

    private Toolbar toolbarForgotPassword;

    public EditText userEmailET;
    public Button userForgotPasswordButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot_password);

        toolbarForgotPassword = (Toolbar)this.findViewById(R.id.toolbarForgotPassword);
        toolbarForgotPassword.setTitle("Forgot Password");
        setSupportActionBar(toolbarForgotPassword);

        this.userEmailET = (EditText)this.findViewById(R.id.userEmailET);
        this.userForgotPasswordButton = (Button)this.findViewById(R.id.userForgotPasswordButton);

        mAuth = FirebaseAuth.getInstance();
    }

    public void userForgotPasswordButtonPressed(View v){
        mAuth.sendPasswordResetEmail(userEmailET.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserForgotPassword.this, "Password Reset Sent To Email", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UserForgotPassword.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
