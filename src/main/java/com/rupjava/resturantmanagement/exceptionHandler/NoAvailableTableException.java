package com.rupjava.resturantmanagement.exceptionHandler;

public class NoAvailableTableException extends RuntimeException{

    public NoAvailableTableException(String message) {
        super(message);
    }
}
