package com.rupjava.resturantmanagement.controller;

import com.rupjava.resturantmanagement.exceptionHandler.ResourceNotFoundException;
import com.rupjava.resturantmanagement.model.RestaurantTable;
import com.rupjava.resturantmanagement.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    /**
     * Create a new table.
     *
     * @param table RestaurantTable details.
     * @return Created Restaurant Table.
     */
    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable table) {
        RestaurantTable createdTable = tableService.createTable(table);
        return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
    }

    /**
     * Get all tables.
     *
     * @return List of all Restaurant Tables.
     */
    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        List<RestaurantTable> tables = tableService.getAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    /**
     * Get table by ID.
     *
     * @param id Table ID.
     * @return Restaurant Table details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTable> getTableById(@PathVariable Long id) {
        RestaurantTable table = tableService.getTableById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with ID: " + id));
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    /**
     * Update table's occupied status.
     *
     * @param id       Table ID.
     * @param occupied Occupied status.
     * @return Updated Restaurant Table.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<RestaurantTable> updateTableStatus(@PathVariable Long id, @RequestParam boolean occupied) {
        RestaurantTable updatedTable = tableService.updateTableStatus(id, occupied);
        return new ResponseEntity<>(updatedTable, HttpStatus.OK);
    }
}
