package com.example.seniorseminarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PublicKey;

public class AdminLogin extends AppCompatActivity {

    private Toolbar toolbarAdminLogin;

    public EditText adminEmailET;
    public EditText adminPasswordET;
    public Button adminLoginButton;

    public DatabaseReference mDatabase;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        toolbarAdminLogin = (Toolbar)this.findViewById(R.id.toolbarAdminLogin);
        toolbarAdminLogin.setTitle("Admin Login");
        setSupportActionBar(toolbarAdminLogin);

        this.adminEmailET = (EditText)this.findViewById(R.id.adminEmailET);
        this.adminPasswordET = (EditText)this.findViewById(R.id.adminPasswordET);
        this.adminLoginButton = (Button) this.findViewById(R.id.adminLoginButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void adminLoginButtonPressed(View v){
        adminLogin();
    }

    public void adminLogin(){
        String email = adminEmailET.getText().toString().trim();
        String password = adminPasswordET.getText().toString().trim();

        if(email.isEmpty()){
            adminEmailET.setError("Email is required");
            adminEmailET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            adminEmailET.setError("Please enter a valid email");
            adminEmailET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            adminPasswordET.setError("Please Enter Password");
            adminPasswordET.requestFocus();
            return;
        }


        //progressBarLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressBarLogin.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    Intent i = new Intent(getApplicationContext(), AdminMainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Logged In As Admin", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
