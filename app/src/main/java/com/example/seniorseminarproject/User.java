package com.example.seniorseminarproject;

public class User {

    String userId;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Boolean isAdmin;

    public User () {

    }

    public  User(String userId, String username, String password, String firstName, String lastName, String email, Boolean isAdmin){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getUserId() {
        return  userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
}
