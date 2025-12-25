package com.example.vvs.NotificationService.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long reservationId;

    private String message;

    @Enumerated(EnumType.STRING)
    private Channel channel;   // SMS, EMAIL, APP

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;   // PENDING, SENT, FAILED

    @CreatedDate
    private LocalDateTime createdAt;
}

