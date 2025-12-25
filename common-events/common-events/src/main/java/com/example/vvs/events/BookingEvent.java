package com.example.vvs.events;
import java.time.LocalDateTime;
public class BookingEvent {

    private Long reservationId;
    private Long userId;
    private BookingEventType eventType;
    private LocalDateTime eventTime;

    // ✅ REQUIRED by Jackson
    public BookingEvent() {
    }

    // ✅ All-args constructor
    public BookingEvent(Long reservationId,
                        Long userId,
                        BookingEventType eventType,
                        LocalDateTime eventTime) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    // ✅ Private constructor for Builder
    private BookingEvent(Builder builder) {
        this.reservationId = builder.reservationId;
        this.userId = builder.userId;
        this.eventType = builder.eventType;
        this.eventTime = builder.eventTime;
    }

    // ✅ Builder entry point
    public static Builder builder() {
        return new Builder();
    }

    // ✅ Getters
    public Long getReservationId() {
        return reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public BookingEventType getEventType() {
        return eventType;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    // ✅ Setters (IMPORTANT for Jackson)
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEventType(BookingEventType eventType) {
        this.eventType = eventType;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    // ✅ Builder class
    public static class Builder {
        private Long reservationId;
        private Long userId;
        private BookingEventType eventType;
        private LocalDateTime eventTime;

        public Builder reservationId(Long reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder eventType(BookingEventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder eventTime(LocalDateTime eventTime) {
            this.eventTime = eventTime;
            return this;
        }

        public BookingEvent build() {
            return new BookingEvent(this);
        }
    }
}












