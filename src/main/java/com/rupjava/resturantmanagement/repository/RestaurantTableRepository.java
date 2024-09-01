package com.rupjava.resturantmanagement.repository;

import com.rupjava.resturantmanagement.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {

    List<RestaurantTable> findByOccupied(boolean occupied);
}
