package com.lazaro.ticket_system.repository;

import com.lazaro.ticket_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}