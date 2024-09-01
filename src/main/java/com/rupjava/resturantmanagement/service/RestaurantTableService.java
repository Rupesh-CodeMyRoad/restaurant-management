package com.rupjava.resturantmanagement.service;

import com.rupjava.resturantmanagement.exceptionHandler.InvalidTableCapacityException;
import com.rupjava.resturantmanagement.exceptionHandler.ResourceNotFoundException;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;

    /**
     * Create a new table.
     *
     * @param table Restaurant Table entity to be created.
     * @return Created Restaurant Table.
     */
    public RestaurantTable createTable(RestaurantTable table) {
        if (table.getCapacity() <= 0) {
            throw new InvalidTableCapacityException("Capacity must be greater than 0");
        }

        return tableRepository.save(table);
    }

    /**
     * Get all tables.
     *
     * @return List of all Restaurant Tables.
     */
    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    /**
     * Get table by ID.
     *
     * @param id Table ID.
     * @return Restaurant Table if found.
     */
    public Optional<RestaurantTable> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    /**
     * Update table's occupied status.
     *
     * @param tableId  ID of the table.
     * @param occupied Occupied status to set.
     * @return Updated RestaurantTable.
     */
    public RestaurantTable updateTableStatus(Long tableId, boolean occupied) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with ID: " + tableId));
        table.setOccupied(occupied);
        return tableRepository.save(table);
    }
}
