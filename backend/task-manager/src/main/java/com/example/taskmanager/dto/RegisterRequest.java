package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for user registration requests.
 * Contains validated user credentials required for account creation.
 */
public class RegisterRequest {

    /**
     * Username for the new account.
     * Must not be blank.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Password for the new account.
     * Must not be blank.
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Default constructor.
     */
    public RegisterRequest() {
    }

    /**
     * Creates a registration request with specified credentials.
     *
     * @param username The username for the new account
     * @param password The password for the new account
     */
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return The username for the new account
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
     * @return The password for the new account
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