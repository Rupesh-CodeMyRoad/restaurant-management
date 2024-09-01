package com.rupjava.resturantmanagement.exceptionHandler;

public class InvalidTableCapacityException extends RuntimeException{
    public InvalidTableCapacityException(String message) {
        super(message);
    }
}
