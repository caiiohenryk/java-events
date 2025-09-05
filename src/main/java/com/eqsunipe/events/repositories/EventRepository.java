package com.eqsunipe.events.repositories;

import com.eqsunipe.events.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
