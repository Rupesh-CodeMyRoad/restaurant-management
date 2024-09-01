package com.rupjava.restaurantmanagement.exceptionHandler;

public class NoAvailableTableException extends RuntimeException{

    public NoAvailableTableException(String message) {
        super(message);
    }
}
