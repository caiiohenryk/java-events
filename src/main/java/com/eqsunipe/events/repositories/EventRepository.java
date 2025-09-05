package com.eqsunipe.events.repositories;

import com.eqsunipe.events.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
  Page<Event> findByCityAndUf(String city, String uf, Pageable pageable);
  Page<Event> findByCity(String city, Pageable pageable);
  Page<Event> findByUf(String uf, Pageable pageable);
}
