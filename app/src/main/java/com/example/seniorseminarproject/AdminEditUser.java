package com.example.seniorseminarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AdminEditUser extends AppCompatActivity {

    private EditText editUsername;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editTotalPoints;
    private Button updateUserButton;
    private Button deleteUserButton;

    private String key;
    private String username;
    private String firstName;
    private String lastName;
    private String totalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);

        key = getIntent().getStringExtra("key");
        username = getIntent().getStringExtra("username");
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        totalPoints = getIntent().getStringExtra("totalPoints");

        this.editUsername = (EditText)this.findViewById(R.id.editUsernameET);
        editUsername.setText(username);
        this.editFirstName = (EditText)this.findViewById(R.id.editFirstNameET);
        editFirstName.setText(firstName);
        this.editLastName = (EditText)this.findViewById(R.id.editLastNameET);
        editLastName.setText(lastName);
        this.editTotalPoints = (EditText)this.findViewById(R.id.editTotalPointsET);
        editTotalPoints.setText(totalPoints);
        this.updateUserButton = (Button)this.findViewById(R.id.updateUserButton);
        this.deleteUserButton = (Button)this.findViewById(R.id.deleteUserButton);

        updateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(editUsername.getText().toString());
                user.setFirstName(editFirstName.getText().toString());
                user.setLastName(editLastName.getText().toString());
                user.setPoints(editTotalPoints.getText().toString());

                new AdminUserDatabaseHelper().updateUser(key, user, new AdminUserDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<User> users, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AdminEditUser.this, "User Has Been Updated", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AdminUserDatabaseHelper().deleteUser(key, new AdminUserDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<User> users, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AdminEditUser.this, "User Was Deleted", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });
            }
        });
    }
}
