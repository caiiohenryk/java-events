package com.eqsunipe.events.services;

import com.eqsunipe.events.dtos.EventCreateDTO;
import com.eqsunipe.events.entities.Event;
import com.eqsunipe.events.exceptions.NotFoundException;
import com.eqsunipe.events.repositories.EventRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Event createEvent(EventCreateDTO dto) {
        Event newEvent = new Event();
        newEvent.setName(dto.getName());
        newEvent.setDate(dto.getDate());
        newEvent.setLocal(dto.getLocal());
        newEvent.setCity(dto.getCity());
        newEvent.setUf(dto.getUf());
        return eventRepository.save(newEvent);
    }
    @Transactional
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
    @Transactional
    public Event updateEvent(EventCreateDTO dto, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Evento não encontrado."));
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setLocal(dto.getLocal());
        event.setCity(dto.getCity());
        event.setUf(dto.getUf());
        return eventRepository.save(event);
    }
    @Transactional
    public Event changeEventName(String eventName, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Evento não encontrado."));
        event.changeName(eventName);
        return eventRepository.save(event);
    }
    public Page<Event> findByCityAndUf(String city, String uf, Pageable pageable) {
        return eventRepository.findByCityAndUf(city, uf, pageable);
    }
    public Page<Event> findByCity(String city, Pageable pageable) {
        return eventRepository.findByCity(city, pageable);
    }
    public Page<Event> findByUf(String uf, Pageable pageable) {
        return eventRepository.findByUf(uf, pageable);
    }
    public void deleteEvent(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()->new NotFoundException("Evento não encontrado."));
        eventRepository.delete(event);
    }
}
