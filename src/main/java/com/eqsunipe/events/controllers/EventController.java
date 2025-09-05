package com.eqsunipe.events.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eqsunipe.events.dtos.EventCreateDTO;
import com.eqsunipe.events.dtos.EventRenameDTO;
import com.eqsunipe.events.entities.Event;
import com.eqsunipe.events.exceptions.NotFoundException;
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
  /* Por convenções de nomeação de URI, decidi tratar 3 funções do sistema em 1 endpoint só. */ 
  @GetMapping
  public ResponseEntity<Page<Event>> getAllEvents(
    Pageable pageable,
    @RequestParam(name = "uf", required = false) String uf,
    @RequestParam(name = "city", required = false) String city) {
      if (uf != null && city != null) {
          return ResponseEntity.ok(eventService.findByCityAndUf(city, uf, pageable));
        }
        if (uf != null) {
          return ResponseEntity.ok(eventService.findByUf(uf, pageable));
        }
        if (city != null) {
          return ResponseEntity.ok(eventService.findByCity(city, pageable));
 }
        return ResponseEntity.ok(eventService.findAll(pageable));
    }
  
  @PutMapping("/{eventId}")
  public ResponseEntity<Event> update(@Valid @RequestBody EventCreateDTO dto, @PathVariable("eventId") String eventId) {
    return ResponseEntity.ok(eventService.updateEvent(dto, eventId));
  }
  @PatchMapping("/{eventId}/change-name")
  public ResponseEntity<Event> changeName(@RequestBody EventRenameDTO dto, @PathVariable("eventId") String eventId ) {
    return ResponseEntity.ok(eventService.changeEventName(dto.getName(), eventId));
  }
  @DeleteMapping("/{eventId}")
  public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") String eventId) {
    try {
      eventService.deleteEvent(eventId);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }
}
