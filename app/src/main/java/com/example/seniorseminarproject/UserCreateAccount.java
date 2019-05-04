package com.example.seniorseminarproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreateAccount extends AppCompatActivity {

    public EditText userCreateEmailET;
    public EditText userCreatePasswordET;
    public Button userCreateAccountButton;
    public DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_account);

        this.userCreateEmailET = (EditText) this.findViewById(R.id.userCreateEmailET);
        this.userCreatePasswordET = (EditText) this.findViewById(R.id.userCreatePasswordET);
        this.userCreateAccountButton = (Button) this.findViewById(R.id.userCreateAccountButton);
        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar2);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void userCreateAccountButtonPressed(View v){

        createNewUser();
    }

    private void createNewUser(){
        String email = userCreateEmailET.getText().toString().trim();
        String password = userCreatePasswordET.getText().toString().trim();

        if(email.isEmpty()){
            userCreateEmailET.setError("Email is required");
            userCreateEmailET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userCreateEmailET.setError("Please enter a valid email");
            userCreateEmailET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            userCreatePasswordET.setError("Password is required");
            userCreatePasswordET.requestFocus();
            return;
        }

        if(password.length() < 6){
            userCreatePasswordET.setError("Minimum length of password is 6 characters");
            userCreatePasswordET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), UserLogin.class);
                    getApplicationContext().startActivity(i);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Email is already in use", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
