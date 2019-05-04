package com.example.seniorseminarproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
    public EditText userEmailET;
    public EditText userPasswordET;
    public ProgressBar progressBarLogin;

    public DatabaseReference mDatabase;
    public FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        this.userLoginButton = (Button)this.findViewById(R.id.userLoginButton);
        this.userEmailET = (EditText)this.findViewById(R.id.userEmailET);
        this.userPasswordET = (EditText)this.findViewById(R.id.userPasswordET);
        this.progressBarLogin = (ProgressBar)this.findViewById(R.id.progressBarLogin);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void userCreateAccountTextPressed(View v){
        Intent i = new Intent(this, UserCreateAccount.class);
        this.startActivity(i);
    }

    public void userLoginButtonPressed(View v){
        userLogin();
    }

    public void userForgotPasswordTextPressed(View v){
        Intent i = new Intent(this, UserForgotPassword.class);
        this.startActivity(i);
    }

    public void adminLoginTextPressed(View v){
        Intent i = new Intent(this, AdminLogin.class);
        this.startActivity(i);
    }

    public void userLogin(){
        String email = userEmailET.getText().toString().trim();
        String password = userPasswordET.getText().toString().trim();

        if(email.isEmpty()){
            userEmailET.setError("Email is required");
            userEmailET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmailET.setError("Please enter a valid email");
            userEmailET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            userPasswordET.setError("Please Enter Password");
            userPasswordET.requestFocus();
            return;
        }

        progressBarLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarLogin.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    Intent i = new Intent(getApplicationContext(), UserMainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//
//        if(mAuth.getCurrentUser() != null){
//            finish();
//            Intent i = new Intent(this, UserMainActivity.class);
//            startActivity(i);
//        }
//    }

}
