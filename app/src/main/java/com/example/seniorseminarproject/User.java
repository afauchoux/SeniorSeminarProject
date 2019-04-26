package com.example.seniorseminarproject;

public class User {

    String userId;
    String username;
    String firstName;
    String lastName;

    public User () {

    }

    public  User(String userId, String username, String firstName, String lastName){
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
