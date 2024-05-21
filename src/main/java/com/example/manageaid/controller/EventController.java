package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Event;
import com.example.manageaid.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/users/{userId}/events")
    List<Event> getAllEvents(@PathVariable Long userId) {
        return eventRepository.getEventsForUser(userId);
    }

    @GetMapping("/users/{ignoredUserId}/events/{id}")
    Event getEventById(@PathVariable Long id, @PathVariable String ignoredUserId) {
        return eventRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id));
    }

    @PostMapping("/users/{ignoredUserId}/events")
    Event addEvent(@RequestBody Event newEvent, @PathVariable String ignoredUserId) {
        return eventRepository.save(newEvent);
    }

    @PutMapping("/users/{ignoredUserId}/events/{id}")
    Event updateEvent(@RequestBody Event newEvent, @PathVariable Long id, @PathVariable String ignoredUserId) {
        return eventRepository.findById(id)
                .map(entity -> {
                    entity.setName(newEvent.getName());
                    entity.setDetails(newEvent.getDetails());
                    entity.setStartTime(newEvent.getStartTime());
                    entity.setEndTime(newEvent.getEndTime());
                    entity.setEventCreator(newEvent.getEventCreator());
                    return eventRepository.save(entity);
                }).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @DeleteMapping("/users/{ignoredUserId}/events/{id}")
    String deleteEvent(@PathVariable Long id, @PathVariable String ignoredUserId) {
        if(!eventRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        eventRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

}
