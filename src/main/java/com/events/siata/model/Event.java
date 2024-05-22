package com.events.siata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_name", columnDefinition = "VARCHAR(255)")
    private String eventName;

    @Column(name = "event_description", columnDefinition = "TEXT")
    private String eventDescription;

    @Column(name = "event_date", columnDefinition = "DATE")
    private String eventDate;

    @Column(name = "event_time", columnDefinition = "TIME")
    private String eventTime;

    @Column(name = "location", columnDefinition = "VARCHAR(255)")
    private String location;

    @Lob
    @Column(name = "event_img", columnDefinition = "MEDIUMBLOB")
    private byte[] eventImg;  // Changed type to byte[] to properly store BLOB data

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getEventImg() {
        return eventImg;
    }

    public void setEventImg(byte[] eventImg) {
        this.eventImg = eventImg;
    }
}