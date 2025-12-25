package com.example.vvs.BookingService.service;

import com.example.vvs.BookingService.client.BillingClient;
import com.example.vvs.BookingService.client.InventoryClient;
import com.example.vvs.BookingService.client.PaymentsClient;
import com.example.vvs.BookingService.dto.billing.CalculatePriceRequest;
import com.example.vvs.BookingService.dto.booking.ReservationRequest;
import com.example.vvs.BookingService.dto.booking.ReservationResponse;
import com.example.vvs.BookingService.dto.booking.ReservationUnitResponse;
import com.example.vvs.BookingService.dto.payment.CreatePaymentRequest;
import com.example.vvs.BookingService.dto.payment.CreatePaymentResponse;
import com.example.vvs.BookingService.dto.payment.PaymentUpdateRequest;
import com.example.vvs.BookingService.exception.ReservationNotFoundException;
import com.example.vvs.BookingService.exception.UnitsNotAvailableForBookingException;
import com.example.vvs.BookingService.mapper.ReservationUnitMapper;
import com.example.vvs.BookingService.model.LockStatus;
import com.example.vvs.BookingService.model.Payment.PaymentStatus;
import com.example.vvs.BookingService.model.Reservation;
import com.example.vvs.BookingService.model.ReservationStatus;
import com.example.vvs.BookingService.model.ReservationUnit;
import com.example.vvs.BookingService.repository.ReservationRepository;
import com.example.vvs.BookingService.repository.ReservationUnitRepository;
import com.example.vvs.events.BookingEvent;
import com.example.vvs.events.BookingEventType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final InventoryClient inventoryClient;
    private final PaymentsClient paymentClient;
    private final BillingClient billingClient;

    private final ReservationRepository reservationRepository;
    private final ReservationUnitRepository reservationUnitRepository;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;


    @Override
    public ReservationResponse createReservation(ReservationRequest request) {
        //Step 1
        //Check availability
        List<Long> UnavailableUnits = checkAvailability(request);
        if(!UnavailableUnits.isEmpty()){
            String message = UnavailableUnits.stream().map(String::valueOf).collect(Collectors.joining(", "));
            throw new UnitsNotAvailableForBookingException("Units "+message+" are not available");
        }

        //Step2
        //Create reservation with status PENDING
        Reservation reservation = new Reservation.Builder()
                .userId(request.getUserId())
                .resortId(request.getResortId())
                .accoTypeId(request.getAccoTypeId())
                .arrivalDate(request.getArrivalDate())
                .departureDate(request.getDepartureDate())
                .status(ReservationStatus.PENDING)
                //fetching price
                .totalAmount(calculatePrice(request))
                .build();
        reservationRepository.save(reservation);

        //Step3
        //for every Unit create a reservation Unit add a SOFT_LOCK  with expiry time
        List<ReservationUnit> reservationUnits = new ArrayList<>();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(2);
        for(Long UnitId : request.getUnitIds()){
            reservationUnits.add(new ReservationUnit(reservation,
                    UnitId,
                    request.getArrivalDate(),
                    request.getDepartureDate(),
                    LockStatus.SOFT_LOCKED,
                    expirationTime
            ));
        }

        reservationUnitRepository.saveAll(reservationUnits);

        //Call Payment Service and get the Payment Order/Intent
        CreatePaymentResponse createPaymentResponse =  initiatePayment(reservation,expirationTime);

        //return reservation with PendingStatus along with Payment Intent/Order
        return ReservationResponse.builder()
                        .reservationId(reservation.getId())
                        .userId(reservation.getUserId())
                        .totalAmount(reservation.getTotalAmount())
                        .currency("INR")
                        .expiresAt(createPaymentResponse.getExpiresAt())
                        .paymentOrderId(createPaymentResponse.getPaymentOrderId())
                        .paymentStatus(createPaymentResponse.getPaymentStatus())
                        .build();
    }



    public Double calculatePrice(ReservationRequest request){
        CalculatePriceRequest priceRequest = CalculatePriceRequest.builder()
                .resortId(request.getResortId())
                .accoTypeId(request.getAccoTypeId())
                .unitIds(request.getUnitIds())
                .arrivalDate(request.getArrivalDate())
                .departureDate(request.getDepartureDate())
                .resortId(request.getUserId())
                .build();
        return billingClient.calculatePrice(priceRequest);
    }

    @Transactional
    public CreatePaymentResponse initiatePayment(Reservation reservation, LocalDateTime expirationTime) {
        CreatePaymentRequest paymentRequest = CreatePaymentRequest.builder()
                .userId(reservation.getUserId())
                .reservationId(reservation.getId())
                .amount(reservation.getTotalAmount())
                .currency("INR")
                .expiresAt(expirationTime)
                .build();
       return paymentClient.createPayment(paymentRequest);
    }


    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(() ->  new ReservationNotFoundException("Reservation with id :"+id+"does not exists..!") );
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Long reservationId, ReservationRequest request) {
        return null;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    @Override
    public List<ReservationUnitResponse> getAllReservationUnits() {
        List<ReservationUnit> units = reservationUnitRepository.findAll();
        return units.stream().map(ReservationUnitMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public void processPaymentUpdate(PaymentUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Idempotency check
        if (reservation.getStatus() == ReservationStatus.CANCELLED
                || reservation.getStatus() == ReservationStatus.CONFIRMED) {
            return;
        }

        System.out.println(reservation.getId());
        System.out.println(request.getPaymentStatus());
        if (request.getPaymentStatus() == PaymentStatus.FAILED) {

            reservation.setStatus(ReservationStatus.CANCELLED);

            reservation.getReservationUnits().forEach(unit -> {
                unit.setStatus(LockStatus.RELEASED);
                unit.setExpiresAt(null);
            });

            reservationRepository.save(reservation); // cascades units
            System.out.println("Payment FAILED");
        }

        if (request.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
            reservation.setStatus(ReservationStatus.CONFIRMED);
            reservation.getReservationUnits().forEach(unit -> {
                unit.setStatus(LockStatus.CONFIRMED);
                unit.setExpiresAt(reservation.getDepartureDate());// hard lock
            });
            reservationRepository.save(reservation);
            BookingEvent event = BookingEvent.builder().reservationId(reservation.getId()).eventType(BookingEventType.PAYMENT_SUCCESS).build();
            kafkaTemplate.send("booking-events", event);
            System.out.println("Payment SUCCESS");
        }
    }


    private List<Long> checkAvailability(ReservationRequest request) {
        List<Long> inactiveUnits = getInactiveUnits(request.getUnitIds());
        List<Long> unavailableUnits = getUnavailableUnits(request.getUnitIds(), request.getArrivalDate(), request.getDepartureDate());
        List<Long> badUnits = new ArrayList<>();
        if(!inactiveUnits.isEmpty()) badUnits.addAll(inactiveUnits);
        if(!unavailableUnits.isEmpty()) badUnits.addAll(unavailableUnits);
        System.out.println("InactiveUnits : "+inactiveUnits.size());
        System.out.println("unavailableUnits : "+unavailableUnits.size());
        return badUnits;
    }

    private List<Long> getInactiveUnits(List<Long> UnitIds){
        return inventoryClient.getInactiveUnits(UnitIds);
    }

    private List<Long> getUnavailableUnits(List<Long> UnitIds, LocalDateTime arrivalDate, LocalDateTime departureDate){
        //get all the units that are booked in the given arrival and departure dates
        return reservationUnitRepository.findBookedUnitsInDateRange(UnitIds, arrivalDate, departureDate);
    }

}
