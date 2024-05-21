package com.events.siata.controller;

import com.events.siata.dto.EventDTO;
import com.events.siata.model.Event;
import com.events.siata.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // Added import for Map

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        return eventService.getEventById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        try {
            Event event = new Event();
            event.setEventName(eventDTO.getEventName());
            event.setEventDescription(eventDTO.getEventDescription());
            event.setEventDate(eventDTO.getEventDate());
            event.setEventTime(eventDTO.getEventTime());
            event.setLocation(eventDTO.getLocation());
            event.setEventImg(eventDTO.getEventImg()); // Base64 string
            eventService.saveEvent(event);
            return new ResponseEntity<>(Map.of("message", "Event created successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Event creation failed: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody EventDTO eventDTO) {
        return eventService.getEventById(id)
            .map(event -> {
                event.setEventName(eventDTO.getEventName());
                event.setEventDescription(eventDTO.getEventDescription());
                event.setEventDate(eventDTO.getEventDate());
                event.setEventTime(eventDTO.getEventTime());
                event.setLocation(eventDTO.getLocation());
                event.setEventImg(eventDTO.getEventImg()); // Base64 string
                Event updatedEvent = eventService.saveEvent(event);
                return ResponseEntity.ok(updatedEvent);
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
