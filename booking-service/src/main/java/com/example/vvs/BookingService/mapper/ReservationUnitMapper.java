package com.example.vvs.BookingService.mapper;

import com.example.vvs.BookingService.model.ReservationUnit;
import com.example.vvs.BookingService.dto.booking.ReservationUnitResponse;

public class ReservationUnitMapper {

    private ReservationUnitMapper() {
        // utility class
    }

    public static ReservationUnitResponse toResponse(ReservationUnit entity) {
        if (entity == null) {
            return null;
        }

        ReservationUnitResponse response = new ReservationUnitResponse();
        response.setReservationId(entity.getReservation().getId());
        response.setUnitId(entity.getUnitId());
        response.setArrivalDate(entity.getStartDate());
        response.setDepartureDate(entity.getEndDate());
        response.setLockStatus(entity.getStatus());
        response.setExpiresAt(entity.getExpiresAt());

        return response;
    }
}

