package com.example.seniorseminarproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserEditProfile extends AppCompatActivity {

    public ImageView userProfileImage;
    public EditText userEditUsername;
    public EditText userEditFirstName;
    public EditText userEditLastName;
    public Button userSaveButton;
    public ProgressBar progressBarProfile;

    private static final int CHOOSE_IMAGE = 101;
    public Uri uriProfileImage;
    public String profileImageUrl;

    DatabaseReference databaseUsers;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        this.userProfileImage = (ImageView)this.findViewById(R.id.userProfileImage);
        this.userEditUsername = (EditText)this.findViewById(R.id.userEditUsername);
        this.userEditFirstName = (EditText)this.findViewById(R.id.userEditFirstName);
        this.userEditLastName = (EditText)this.findViewById(R.id.userEditLastName);
        this.userSaveButton = (Button)this.findViewById(R.id.userSaveButton);
        this.progressBarProfile = (ProgressBar)this.findViewById(R.id.progressBarProfile);

        mAuth = FirebaseAuth.getInstance();

        loadUserInfo();
    }

    public void userProfileImagePressed(View v){
        showImageChooser();
    }

    public void userSaveButtonPressed(View v){
        addUser();
        saveUsernameInfo();
        finish();
        Intent i = new Intent(getApplicationContext(), UserProfile.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void addUser(){
        String username = userEditUsername.getText().toString();
        String firstName = userEditFirstName.getText().toString();
        String lastName = userEditLastName.getText().toString();

        if(!TextUtils.isEmpty(username) || !TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(lastName)){
            String userId = databaseUsers.push().getKey();

            User user = new User(userId, username, firstName, lastName);

            databaseUsers.child(userId).setValue(user);

            Toast.makeText(this, "User Data Saved", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Please Enter All Data", Toast.LENGTH_LONG).show();
        }
    }

    private void saveUsernameInfo(){
        String username = userEditUsername.getText().toString();

        if(username.isEmpty()){
            userEditUsername.setError("Name required");
            userEditUsername.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null && profileImageUrl != null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserEditProfile.this, "Profile updated", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    private void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(userProfileImage);
            }

            if (user.getDisplayName() != null) {
                userEditUsername.setText(user.getDisplayName());
            }
        }
    }

    private void showImageChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Choose Profile Picture"), CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriProfileImage = data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                userProfileImage.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage(){
        StorageReference profilePicRef = FirebaseStorage.getInstance().getReference("profilePics/" + System.currentTimeMillis() + ".jpg");
        if(uriProfileImage != null){
            progressBarProfile.setVisibility(View.VISIBLE);
            profilePicRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBarProfile.setVisibility(View.GONE);

                    profileImageUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBarProfile.setVisibility(View.GONE);
                    Toast.makeText(UserEditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            Intent i = new Intent(this, UserLogin.class);
            startActivity(i);
        }
    }
}
