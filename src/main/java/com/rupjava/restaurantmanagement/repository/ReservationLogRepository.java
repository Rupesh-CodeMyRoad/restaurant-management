package com.rupjava.restaurantmanagement.repository;

import com.rupjava.restaurantmanagement.model.ReservationLog;
import com.rupjava.restaurantmanagement.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationLogRepository extends JpaRepository<ReservationLog,Long> {

    List<ReservationLog> findByRestaurantTable(RestaurantTable table);

}
