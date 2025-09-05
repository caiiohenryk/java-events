package com.eqsunipe.events.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eqsunipe.events.dtos.EventCreateDTO;
import com.eqsunipe.events.entities.Event;
import com.eqsunipe.events.services.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("events")
public class EventController {
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }
  private final EventService eventService;

  @PostMapping
  public ResponseEntity<Event> create(@Valid @RequestBody EventCreateDTO dto) {
    return ResponseEntity.ok(eventService.createEvent(dto));
  }
  @GetMapping
  public ResponseEntity<Page<Event>> getAllEvents(Pageable pageable) {
    Page<Event> events = eventService.findAll(pageable);
    return ResponseEntity.ok(events);
  }
}
