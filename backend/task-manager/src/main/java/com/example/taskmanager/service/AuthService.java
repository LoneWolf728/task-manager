package com.example.taskmanager.service;

import com.example.taskmanager.dto.JwtResponse;
import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.RegisterRequest;

/**
 * Service interface for authentication operations.
 * Provides methods for user login and registration.
 */
public interface AuthService {
    
    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param loginRequest DTO containing username and password credentials
     * @return JWT response containing authentication token and user details
     * @throws org.springframework.security.authentication.BadCredentialsException if credentials are invalid
     */
    JwtResponse login(LoginRequest loginRequest);
    
    /**
     * Registers a new user in the system.
     *
     * @param registerRequest DTO containing new user details
     * @throws com.example.taskmanager.exception.BadRequestException if username already exists
     */
    void register(RegisterRequest registerRequest);
}