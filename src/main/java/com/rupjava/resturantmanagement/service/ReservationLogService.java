package com.rupjava.resturantmanagement.service;

import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.model.ReservationLog;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.repository.ReservationLogRepository;
import com.rupjava.resturantmanagement.repository.ReservationRepository;
import com.rupjava.resturantmanagement.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationLogService {

    private final ReservationLogRepository reservationLogRepository;
    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;

    public List<ReservationLog> getAllLogs() {
        return reservationLogRepository.findAll();
    }

    public List<ReservationLog> getLogsByTable(RestaurantTable table) {
        return reservationLogRepository.findByRestaurantTable(table);
    }

    public Optional<RestaurantTable> getTableById(Long id) {
        return tableRepository.findById(id);
    }
}
