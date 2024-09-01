package com.rupjava.resturantmanagement.controller;

import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.model.ReservationLog;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.service.ReservationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation-logs")
@RequiredArgsConstructor
public class ReservationLogController {

    private final ReservationLogService reservationLogService;

    /**
     * Get all reservation logs.
     *
     * @return List of all reservation logs.
     */
    @GetMapping
    public ResponseEntity<List<ReservationLog>> getAllLogs() {
        List<ReservationLog> logs = reservationLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    /**
     * Find reservation logs by reservation and restaurant table.
     *
     * @param tableId       ID of the restaurant table.
     * @return List of reservation logs matching the criteria.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ReservationLog>> getLogsByReservationAndTable(
            @RequestParam Long tableId) {

        // Fetch Reservation and RestaurantTable by ID
        Optional<RestaurantTable> tableOpt = reservationLogService.getTableById(tableId);

        List<ReservationLog> logs = reservationLogService.getLogsByTable(tableOpt.get());
        return ResponseEntity.ok(logs);
    }
}
