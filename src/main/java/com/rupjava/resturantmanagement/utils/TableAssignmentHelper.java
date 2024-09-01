package com.rupjava.resturantmanagement.utils;

import com.rupjava.resturantmanagement.exceptionHandler.NoAvailableTableException;
import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.repository.ReservationLogRepository;
import com.rupjava.resturantmanagement.repository.ReservationRepository;
import com.rupjava.resturantmanagement.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TableAssignmentHelper {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;

    /**
     * Find the best available table for a reservation request.
     *
     * @param reservation Reservation details.
     * @return Optional containing the best RestaurantTable if available.
     */
    public Optional<RestaurantTable> findBestTable(Reservation reservation) {
        // Retrieve all tables
        List<RestaurantTable> allTables = tableRepository.findAll();

        // Check for unoccupied tables
        List<RestaurantTable> unoccupiedTables = allTables.stream()
                .filter(table -> !table.isOccupied() && table.getCapacity() >= reservation.getNumberOfPeople())
                .collect(Collectors.toList());

        // Check for potential reshuffling
        // (smallest to largest) sorting
        unoccupiedTables.sort(Comparator.comparingInt(RestaurantTable::getCapacity));

        // unoccupied tables, potential reshuffling
        if (!unoccupiedTables.isEmpty()) {
            // list of currently occupied tables with reservations starting in the future
            List<RestaurantTable> reshuffleCandidates = allTables.stream()
                    .filter(table -> table.isOccupied())
                    .collect(Collectors.toList());

            // Prepare a list of reshuffle
            for (RestaurantTable reservedTable : reshuffleCandidates) {
                Optional<Reservation> currentReservationOpt = reservationRepository.findByRestaurantTable(reservedTable);

                if (currentReservationOpt.isPresent()) {
                    Reservation currentReservation = currentReservationOpt.get();

                    // Only consider reshuffling if the reservation start time hasn't started
                    if (currentReservation.getStartTime().isAfter(LocalDateTime.now())) {
                        for (RestaurantTable availableTable : unoccupiedTables) {
                            if (currentReservation.getNumberOfPeople() <= availableTable.getCapacity() &&
                                    reservation.getNumberOfPeople() > reservedTable.getCapacity()) {

                                // Perform reshuffling
                                currentReservation.setRestaurantTable(availableTable);
                                availableTable.setOccupied(true);
                                reservedTable.setOccupied(false);

                                // Save the changes to the database
                                reservationRepository.save(currentReservation);
                                tableRepository.save(availableTable);
                                tableRepository.save(reservedTable);

                                // Return the table for the new reservation
                                return Optional.of(reservedTable);
                            }
                        }
                    }
                }
            }

            // suitable table, return it
            return Optional.of(unoccupiedTables.get(0));
        }

        // No table found
        return Optional.empty();
    }
}
