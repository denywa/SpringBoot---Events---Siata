package com.events.siata.dto;

import java.util.Objects;

public class EventDTO {
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventTime;
    private String location;
    private String eventImg; // Store image as Base64 string

    // Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = Objects.requireNonNull(eventName, "Event name cannot be null");
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = Objects.requireNonNull(eventDescription, "Event description cannot be null");
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = Objects.requireNonNull(eventDate, "Event date cannot be null");
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = Objects.requireNonNull(eventTime, "Event time cannot be null");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = Objects.requireNonNull(location, "Location cannot be null");
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = Objects.requireNonNull(eventImg, "Event image cannot be null");
    }
}