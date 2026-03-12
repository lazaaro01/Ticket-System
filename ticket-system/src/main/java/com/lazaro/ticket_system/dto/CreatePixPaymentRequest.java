package com.lazaro.ticket_system.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePixPaymentRequest {

    private UUID eventId;

    private String customerName;

    private String customerEmail;
}