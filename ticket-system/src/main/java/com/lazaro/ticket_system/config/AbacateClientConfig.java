package com.lazaro.ticket_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AbacateClientConfig {

    @Bean
    public RestClient abacateClient() {
        return RestClient.builder()
                .baseUrl("https://api.abacatepay.com/v1")
                .build();
    }
}