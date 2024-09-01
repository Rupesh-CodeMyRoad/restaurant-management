package com.rupjava.resturantmanagement.repository;

import com.rupjava.resturantmanagement.model.Reservation;
import com.rupjava.resturantmanagement.model.ReservationLog;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationLogRepository extends JpaRepository<ReservationLog,Long> {

    List<ReservationLog> findByRestaurantTable(RestaurantTable table);

}
