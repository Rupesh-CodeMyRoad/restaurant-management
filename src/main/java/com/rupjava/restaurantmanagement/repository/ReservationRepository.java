package com.rupjava.restaurantmanagement.repository;

import com.rupjava.restaurantmanagement.model.Reservation;
import com.rupjava.restaurantmanagement.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByEndTimeBefore(LocalDateTime now);

    Optional<Reservation> findByRestaurantTable(RestaurantTable table);
}
