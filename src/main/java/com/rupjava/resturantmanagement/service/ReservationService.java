package com.rupjava.resturantmanagement.service;

import com.rupjava.resturantmanagement.exceptionHandler.NoAvailableTableException;
import com.rupjava.resturantmanagement.exceptionHandler.ResourceNotFoundException;
import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.model.ReservationLog;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.repository.ReservationLogRepository;
import com.rupjava.resturantmanagement.repository.ReservationRepository;
import com.rupjava.resturantmanagement.repository.RestaurantTableRepository;
import com.rupjava.resturantmanagement.utils.TableAssignmentHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;
    private final ReservationLogRepository reservationLogRepository;
    private final TableAssignmentHelper tableAssignmentHelper;

    /**
     * Create a new reservation.
     *
     * @param reservation Reservation details.
     * @return Created Reservation.
     */
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        // Set end time based on 2-hour turnover
        reservation.setEndTime(reservation.getStartTime().plusHours(2));

        // Attempt to find the best available table
        Optional<RestaurantTable> bestTableOpt = tableAssignmentHelper.findBestTable(reservation);

        // If no suitable table is found, throw an exception
        if (bestTableOpt.isEmpty()) {
            throw new NoAvailableTableException("No available tables for the reservation time.");
        }

        // Assign the best table found
        RestaurantTable bestTable = bestTableOpt.get();
        reservation.setRestaurantTable(bestTable);

        // Mark table as occupied
        bestTable.setOccupied(true);
        tableRepository.save(bestTable);

        // Save the reservation and log it
        Reservation savedReservation = reservationRepository.save(reservation);
        logReservation(savedReservation, "CREATED");

        return savedReservation;
    }

    /**
     * Release a table after reservation is over.
     *
     * @param tableId ID of the table to release.
     */
    @Transactional
    public void releaseTable(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with ID: " + tableId));

        // Find the current reservation for the table
        Optional<Reservation> reservationOpt = reservationRepository.findByRestaurantTable(table);

        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();

            // Log the reservation completion
            logReservation(reservation, "COMPLETED");

            // Delete the reservation
            reservationRepository.delete(reservation);

            // Set the table to not occupied
            table.setOccupied(false);
            tableRepository.save(table);
        } else {
            throw new ResourceNotFoundException("No active reservation found for table ID: " + tableId);
        }
    }

    /**
     * Get all reservations.
     *
     * @return List of all reservations.
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Get reservation by ID.
     *
     * @param id Reservation ID.
     * @return Optional containing Reservation if found.
     */
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }


    /**
     * Log reservation status changes.
     *
     * @param reservation Reservation entity.
     * @param status Reservation status.
     */
    private void logReservation(Reservation reservation, String status) {
        ReservationLog log = ReservationLog.builder()
                .customerName(reservation.getCustomerName())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .numberOfPeople(reservation.getNumberOfPeople())
                .restaurantTable(reservation.getRestaurantTable())
                .status(status)
                .statusChangedTime(LocalDateTime.now())
                .build();
        reservationLogRepository.save(log);
    }
}
