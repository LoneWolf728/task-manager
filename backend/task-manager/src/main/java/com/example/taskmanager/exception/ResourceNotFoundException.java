package com.example.taskmanager.exception;

/**
 * Exception thrown when a requested resource cannot be found.
 * This exception is used to indicate HTTP 404 Not Found scenarios.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message The detail message explaining which resource was not found
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}