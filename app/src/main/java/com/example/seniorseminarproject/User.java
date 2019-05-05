package com.example.seniorseminarproject;

public class User {

    String userId;
    String username;
    String firstName;
    String lastName;
    String points;

    public User () {

    }

    public  User(String userId, String username, String firstName, String lastName){
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
