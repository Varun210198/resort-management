package com.example.vvs.BookingService.repository;

import com.example.vvs.BookingService.model.LockStatus;
import com.example.vvs.BookingService.model.ReservationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationUnitRepository extends JpaRepository<com.example.vvs.BookingService.model.ReservationUnit, Long> {
    @Query("""
        SELECT distinct ru.unitId
        FROM ReservationUnit ru
        WHERE ru.unitId IN :unitIds
        AND ru.status <> 'RELEASED'
        AND ru.startDate < :departureDate
        AND ru.endDate > :arrivalDate
        """)
    List<Long> findBookedUnitsInDateRange(
            @Param("unitIds") List<Long> unitIds,
            @Param("arrivalDate") LocalDateTime arrivalDate,
            @Param("departureDate") LocalDateTime departureDate
    );
    List<ReservationUnit> findByStatusAndExpiresAtBefore(
            LockStatus status,
            LocalDateTime time
    );

    @Query("""
            SELECT ru
            FROM ReservationUnit ru
            WHERE ru.status = 'SOFT_LOCKED'
            AND ru.expiresAt < now
            """)
    List<ReservationUnit> findExpiredSoftLocks(
            @Param("now") LocalDateTime now);
}
