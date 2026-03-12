package com.lazaro.ticket_system.service;

import com.lazaro.ticket_system.dto.CreatePixPaymentRequest;
import com.lazaro.ticket_system.model.Event;
import com.lazaro.ticket_system.model.Order;
import com.lazaro.ticket_system.repository.EventRepository;
import com.lazaro.ticket_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final EventRepository eventRepository;
    private final OrderRepository orderRepository;
    private final RestClient abacateClient;
    @Value("${abacate.api-key}")
    private String apiKey;

    public Order createPixPaymentRequest(CreatePixPaymentRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        var response = abacateClient.post()
                .uri("/pix/charges")
                .header("Authorization", "Bearer" + apiKey)
                .body(
                        """
                      {
                        "amount": %s,
                        "description": "Ingresso %s",
                        "customer": {
                          "name": "%s",
                          "email": "%s"
                        }
                      }
                      """.formatted(
                                event.getPrice(),
                                event.getName(),
                                request.getCustomerName(),
                                request.getCustomerEmail()
                        )
                )
                .retrieve()
                .body(String.class);

        System.out.println("ABACATE RESPONSE: " + response);

        Order order = new Order();
        order.setEventId(event.getId());
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }
}