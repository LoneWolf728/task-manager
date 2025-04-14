package com.example.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a user entity in the task management system.
 * Implements Spring Security's UserDetails interface for authentication and authorization.
 */
@Document(collection = "users")
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    private String id;

    /**
     * Username for authentication.
     * Required field that cannot be blank.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Password for authentication, stored in encoded form.
     * Required field that cannot be blank.
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Set of roles assigned to this user.
     * Determines user permissions in the system.
     */
    private Set<Role> roles = new HashSet<>();

    /**
     * Default constructor required by MongoDB.
     */
    public User() {
    }

    /**
     * Creates a user with specified credentials and roles.
     *
     * @param username The username for authentication
     * @param password The password (should be encoded before setting)
     * @param roles Set of roles assigned to the user
     */
    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Returns the authorities granted to the user.
     * Converts the user's roles to Spring Security GrantedAuthority objects.
     *
     * @return A collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the password used for authentication.
     *
     * @return The user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used for authentication.
     *
     * @return The user's username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired.
     * In this implementation, accounts never expire.
     *
     * @return true if the account is valid (not expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * In this implementation, accounts are never locked.
     *
     * @return true if the account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials have expired.
     * In this implementation, credentials never expire.
     *
     * @return true if credentials are valid (not expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * In this implementation, all users are enabled.
     *
     * @return true if the user is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets the user identifier.
     *
     * @return The unique identifier of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user identifier.
     *
     * @param id The unique identifier to set
     */
    public void setId(String id) {
        this.id = id;
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
     * Sets the password.
     * Note: Password should be encoded before setting.
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's roles.
     *
     * @return Set of roles assigned to the user
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the user's roles.
     *
     * @param roles Set of roles to assign to the user
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}