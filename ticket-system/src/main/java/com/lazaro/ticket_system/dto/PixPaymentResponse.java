package com.lazaro.ticket_system.dto;

import lombok.Data;

@Data
public class PixPaymentResponse {

    private String paymentId;

    private String qrCode;

    private String copyPaste;
}