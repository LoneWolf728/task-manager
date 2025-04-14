package com.example.taskmanager.exception;

/**
 * Exception thrown when a client sends an invalid request.
 * This exception is used to indicate HTTP 400 Bad Request scenarios.
 */
public class BadRequestException extends RuntimeException {
    
    /**
     * Constructs a new BadRequestException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception
     */
    public BadRequestException(String message) {
        super(message);
    }
}