package com.lazaro.ticket_system.controller;

import com.lazaro.ticket_system.dto.CreatePixPaymentRequest;
import com.lazaro.ticket_system.model.Order;
import com.lazaro.ticket_system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pix")
    public Order createPixPayment(@RequestBody CreatePixPaymentRequest request) {
        return paymentService.createPixPaymentRequest(request);
    }
}