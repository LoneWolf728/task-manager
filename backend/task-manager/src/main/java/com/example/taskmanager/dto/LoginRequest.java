package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for user login requests.
 * Contains validated credentials required for authentication.
 */
public class LoginRequest {

    /**
     * Username for authentication.
     * Must not be blank.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Password for authentication.
     * Must not be blank.
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Default constructor.
     */
    public LoginRequest() {
    }

    /**
     * Creates a login request with specified credentials.
     *
     * @param username The username for authentication
     * @param password The password for authentication
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return The username for authentication
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return The password for authentication
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}