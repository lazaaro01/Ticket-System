package com.lazaro.ticket_system.service;

import com.lazaro.ticket_system.model.Event;
import com.lazaro.ticket_system.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event create(Event event) {
        return eventRepository.save(event);
    }
    public List<Event> list() {
        return eventRepository.findAll();
    }
}