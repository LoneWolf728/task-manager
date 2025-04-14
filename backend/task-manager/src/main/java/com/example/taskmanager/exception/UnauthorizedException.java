package com.example.taskmanager.exception;

/**
 * Exception thrown when a user attempts to access a resource without proper authorization.
 * This exception is used to indicate HTTP 401 Unauthorized scenarios.
 */
public class UnauthorizedException extends RuntimeException {
    
    /**
     * Constructs a new UnauthorizedException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the unauthorized access
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}