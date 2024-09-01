package com.rupjava.resturantmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private boolean occupied = false;
}
