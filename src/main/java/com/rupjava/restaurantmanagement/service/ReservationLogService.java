package com.rupjava.restaurantmanagement.service;

import com.rupjava.restaurantmanagement.model.ReservationLog;
import com.rupjava.restaurantmanagement.model.RestaurantTable;
import com.rupjava.restaurantmanagement.repository.ReservationLogRepository;
import com.rupjava.restaurantmanagement.repository.ReservationRepository;
import com.rupjava.restaurantmanagement.repository.RestaurantTableRepository;
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
