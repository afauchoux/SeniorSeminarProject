package com.example.seniorseminarproject;

public class Newsfeed {

    String newsfeedId;
    String newsfeedEvent;
    String newsfeedTime;

    public Newsfeed() {

    }

    public Newsfeed(String newsfeedId, String newsfeedEvent, String newsfeedTime) {
        this.newsfeedId = newsfeedId;
        this.newsfeedEvent = newsfeedEvent;
        this.newsfeedTime = newsfeedTime;
    }

    public String getNewsfeedId() {
        return newsfeedId;
    }

    public void setNewsfeedId(String newsfeedId) {
        this.newsfeedId = newsfeedId;
    }

    public String getNewsfeedEvent() {
        return newsfeedEvent;
    }

    public void setNewsfeedEvent(String newsfeedEvent) {
        this.newsfeedEvent = newsfeedEvent;
    }

    public String getNewsfeedTime() {
        return newsfeedTime;
    }

    public void setNewsfeedTime(String newsfeedTime) {
        this.newsfeedTime = newsfeedTime;
    }
}
