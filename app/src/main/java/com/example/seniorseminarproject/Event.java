package com.example.seniorseminarproject;

public class Event {

    String eventId;
    String eventName;
    String eventTime;
    String eventDate;
    String eventDescription;
    String  eventPoints;

    public Event(){

    }

    public Event(String eventId, String eventName, String eventDate, String eventTime, String eventDescription, String eventPoints) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventPoints = eventPoints;
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

    public String getEventPoints() {
        return eventPoints;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }

    public void setEventPoints(String eventPoints) {
        this.eventPoints = eventPoints;
    }
}
