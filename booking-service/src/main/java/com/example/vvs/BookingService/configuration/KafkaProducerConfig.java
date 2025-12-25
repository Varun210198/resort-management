package com.example.vvs.BookingService.configuration;

import com.example.vvs.events.BookingEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaTemplate<String, BookingEvent> kafkaTemplate(
            ProducerFactory<String, BookingEvent> factory) {
        return new KafkaTemplate<>(factory);
    }
}
