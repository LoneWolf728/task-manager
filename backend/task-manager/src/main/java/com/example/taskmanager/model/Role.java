package com.example.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a role entity for authorization in the application.
 * Roles define permissions and access levels for users.
 */
@Document(collection = "roles")
public class Role {

    /**
     * Unique identifier for the role.
     */
    @Id
    private String id;

    /**
     * The name of the role that defines its permissions.
     * @see RoleName
     */
    private RoleName name;

    /**
     * Enumeration of available role types in the system.
     * Each role grants different levels of access and permissions.
     */
    public enum RoleName {
        /**
         * Standard user role with basic permissions.
         */
        ROLE_USER,
        
        /**
         * Administrative role with elevated privileges.
         */
        ROLE_ADMIN
    }

    /**
     * Default constructor required by MongoDB.
     */
    public Role() {
    }

    /**
     * Creates a role with the specified role name.
     *
     * @param name The role name to assign
     */
    public Role(RoleName name) {
        this.name = name;
    }

    /**
     * Gets the role identifier.
     *
     * @return The unique identifier of the role
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the role identifier.
     *
     * @param id The unique identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the role name.
     *
     * @return The name of this role
     */
    public RoleName getName() {
        return name;
    }

    /**
     * Sets the role name.
     *
     * @param name The role name to set
     */
    public void setName(RoleName name) {
        this.name = name;
    }
}