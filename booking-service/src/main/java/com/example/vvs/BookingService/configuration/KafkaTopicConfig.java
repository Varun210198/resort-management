package com.example.vvs.BookingService.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("booking-events", 3, (short) 1);
    }
}