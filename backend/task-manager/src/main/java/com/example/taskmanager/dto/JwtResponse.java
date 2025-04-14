package com.example.taskmanager.dto;

import java.util.Set;

/**
 * Response DTO containing JWT authentication information.
 * Used to transfer authentication data from server to client after successful login.
 */
public class JwtResponse {
    /**
     * JWT token string for authenticated requests.
     */
    private String token;
    
    /**
     * Username of the authenticated user.
     */
    private String username;
    
    /**
     * Set of role names assigned to the authenticated user.
     */
    private Set<String> roles;

    /**
     * Default constructor.
     */
    public JwtResponse() {
    }

    /**
     * Creates a JWT response with authentication details.
     *
     * @param token JWT token for the authenticated session
     * @param username Username of the authenticated user
     * @param roles Set of roles granted to the user
     */
    public JwtResponse(String token, String username, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    /**
     * Gets the JWT token.
     *
     * @return The JWT token string
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token The JWT token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the username.
     *
     * @return The username of the authenticated user
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
     * Gets the user roles.
     *
     * @return Set of role names assigned to the user
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Sets the user roles.
     *
     * @param roles Set of role names to assign to the user
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}