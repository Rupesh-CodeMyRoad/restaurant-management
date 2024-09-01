package com.rupjava.resturantmanagement.controller;


import com.rupjava.resturantmanagement.exceptionHandler.ResourceNotFoundException;
import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Create a new reservation.
     *
     * @param reservation Reservation details.
     * @return Created Reservation.
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    /**
     * Release a table after reservation ends.
     *
     * @param tableId ID of the table to release.
     * @return Response message.
     */
    @PutMapping("/release/{tableId}")
    public ResponseEntity<String> releaseTable(@PathVariable Long tableId) {
        reservationService.releaseTable(tableId);
        return new ResponseEntity<>("Table released successfully.", HttpStatus.OK);
    }

    /**
     * Get all reservations.
     *
     * @return List of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    /**
     * Get reservation by ID.
     *
     * @param id Reservation ID.
     * @return Reservation details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
