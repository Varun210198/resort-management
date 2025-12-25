package com.example.vvs.NotificationService.service;

import com.example.vvs.events.BookingEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderConsumer {

    @KafkaListener(topics = "booking-events", groupId = "inventory-group")
    public void consumeOrder(BookingEvent evt) {
        System.out.println("received...!");
        System.out.println("Booking Event "+evt.toString());
    }
}

