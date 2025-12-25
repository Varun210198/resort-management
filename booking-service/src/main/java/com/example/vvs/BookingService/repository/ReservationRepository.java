package com.example.vvs.BookingService.repository;

import com.example.vvs.BookingService.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
