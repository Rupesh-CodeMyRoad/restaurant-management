package com.rupjava.restaurantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResturantManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResturantManagementApplication.class, args);
    }

}
