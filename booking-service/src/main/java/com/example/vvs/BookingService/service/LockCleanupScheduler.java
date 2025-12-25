package com.example.vvs.BookingService.service;

import com.example.vvs.BookingService.model.LockStatus;
import com.example.vvs.BookingService.model.Reservation;
import com.example.vvs.BookingService.model.ReservationStatus;
import com.example.vvs.BookingService.model.ReservationUnit;
import com.example.vvs.BookingService.repository.ReservationRepository;
import com.example.vvs.BookingService.repository.ReservationUnitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LockCleanupScheduler {

    private final ReservationUnitRepository reservationUnitRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    @Scheduled(fixedDelayString = "${lock.expiry.scheduler.delay}")
    public void releaseExpiredLocks() {

        LocalDateTime now = LocalDateTime.now();
        List<ReservationUnit> expiredUnits =
                reservationUnitRepository.findExpiredSoftLocks(now);

        if (expiredUnits.isEmpty()) {
            return;
        }

        log.info("Releasing {} expired reservation units", expiredUnits.size());

        for (ReservationUnit unit : expiredUnits) {
            unit.setStatus(LockStatus.RELEASED);

            Reservation reservation = unit.getReservation();

            // Cancel reservation if all units are released
            boolean allReleased = reservation.getReservationUnits()
                    .stream()
                    .allMatch(u -> u.getStatus() == LockStatus.RELEASED);

            if (allReleased) {
                reservation.setStatus(ReservationStatus.CANCELLED);
                reservationRepository.save(reservation);
            }
        }

        reservationUnitRepository.saveAll(expiredUnits);
    }
}

