package com.example.vvs.BookingService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(name = "unit_id", nullable = false)
    private Long unitId;   // ID from Inventory Service

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    // Soft-lock expiry time
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LockStatus status; // SOFT_LOCKED / CONFIRMED / RELEASED


    public ReservationUnit(Reservation reservation , Long unitId, LocalDateTime arrivalDate,
                           LocalDateTime departureDate, LockStatus status, LocalDateTime expiresAt) {
        this.reservation = reservation;
        this.unitId = unitId;
        this.startDate = arrivalDate;
        this.endDate = departureDate;
        this.status = status;
        this.expiresAt = expiresAt;
    }
}
