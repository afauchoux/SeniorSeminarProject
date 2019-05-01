package com.example.seniorseminarproject;

public class Event {

    String eventId;
    String eventName;
    String eventTime;
    String eventDate;
    String eventDescription;
    int pointsAwarded;

    public Event(){

    }

    public Event(String eventId, String eventName, String eventDate, String eventTime, String eventDescription, int pointsAwarded) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.pointsAwarded = pointsAwarded;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }
}
