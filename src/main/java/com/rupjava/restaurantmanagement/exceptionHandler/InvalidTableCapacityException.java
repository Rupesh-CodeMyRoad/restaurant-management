package com.rupjava.restaurantmanagement.exceptionHandler;

public class InvalidTableCapacityException extends RuntimeException{
    public InvalidTableCapacityException(String message) {
        super(message);
    }
}
