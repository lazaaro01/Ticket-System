package com.lazaro.ticket_system.repository;

import com.lazaro.ticket_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}