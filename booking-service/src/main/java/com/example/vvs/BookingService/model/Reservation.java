package com.example.vvs.BookingService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long resortId;
    private Long accoTypeId;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReservationUnit> reservationUnits;

    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Double totalAmount;




    // 🔒 Private constructor
    public Reservation(Builder builder) {
        this.userId = builder.userId;
        this.resortId = builder.resortId;
        this.accoTypeId = builder.accoTypeId;
        this.reservationUnits = builder.reservationUnits;
        this.arrivalDate = builder.arrivalDate;
        this.departureDate = builder.departureDate;
        this.status = builder.status;
        this.totalAmount = builder.totalAmount;
    }



    // ✅ Static Builder
    public static class Builder {
        private Long userId;
        private Long resortId;
        private Long accoTypeId;
        private List<ReservationUnit> reservationUnits;
        private LocalDateTime arrivalDate;
        private LocalDateTime departureDate;
        private ReservationStatus status;
        private Double totalAmount;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder resortId(Long resortId) {
            this.resortId = resortId;
            return this;
        }

        public Builder accoTypeId(Long accoTypeId) {
            this.accoTypeId = accoTypeId;
            return this;
        }

        public Builder reservationUnits(List<ReservationUnit> reservationUnits) {
            this.reservationUnits = reservationUnits;
            return this;
        }

        public Builder arrivalDate(LocalDateTime arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public Builder departureDate(LocalDateTime departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public Builder status(ReservationStatus status) {
            this.status = status;
            return this;
        }

        public Builder totalAmount(Double totalAmount){
            this.totalAmount = totalAmount;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }

    }
}
