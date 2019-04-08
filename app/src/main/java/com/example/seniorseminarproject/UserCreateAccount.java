package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreateAccount extends AppCompatActivity {

    public String userId;
    public EditText userCreateFirstNameET;
    public EditText userCreateLastNameET;
    public EditText userCreateUsernameET;
    public EditText userCreateEmailET;
    public EditText userCreatePasswordET;
    public EditText userCreatePasswordET2;
    public Button userCreateAccountButton;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_account);

        this.userCreateFirstNameET = (EditText) this.findViewById(R.id.userCreateFirstNameET);
        this.userCreateLastNameET = (EditText) this.findViewById(R.id.userCreateLastNameET);
        this.userCreateUsernameET = (EditText) this.findViewById(R.id.userCreateUsernameET);
        this.userCreateEmailET = (EditText) this.findViewById(R.id.userCreateEmailET);
        this.userCreatePasswordET = (EditText) this.findViewById(R.id.userCreatePasswordET);
        this.userCreatePasswordET2 = (EditText) this.findViewById(R.id.userRetypePasswordET);
        this.userCreateAccountButton = (Button) this.findViewById(R.id.userCreateAccountButton);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
    }

    public void userCreateAccountButtonPressed(View v){

        createNewUser();
    }

    private void createNewUser(){
        String username = this.userCreateUsernameET.getText().toString().trim();
        String password = this.userCreatePasswordET.getText().toString().trim();
        String firstName = this.userCreateFirstNameET.getText().toString().trim();
        String lastName = this.userCreateLastNameET.getText().toString().trim();
        String email = this.userCreateEmailET.getText().toString().trim();
        //Boolean isAdmin = false;

        if((!TextUtils.isEmpty(username)) && (!TextUtils.isEmpty(password))){

            String userId = mDatabase.push().getKey();

            User user = new User(userId, username, password, firstName, lastName, email, false);

            mDatabase.child(userId).setValue(user);

            Toast.makeText(this, "User Created Successfully", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, UserLogin.class);
            this.startActivity(i);
        }
        else{
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_LONG).show();
        }
    }
}
