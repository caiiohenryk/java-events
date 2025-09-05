package com.eqsunipe.events.services;

import com.eqsunipe.events.dtos.EventCreateDTO;
import com.eqsunipe.events.entities.Event;
import com.eqsunipe.events.repositories.EventRepository;
import jakarta.transaction.Transactional;
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
}
