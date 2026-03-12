package com.lazaro.ticket_system.controller;

import com.lazaro.ticket_system.model.Event;
import com.lazaro.ticket_system.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }
    @GetMapping
    public List<Event> list() {
        return eventService.list();
    }
}