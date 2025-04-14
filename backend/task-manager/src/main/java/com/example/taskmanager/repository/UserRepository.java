package com.example.taskmanager.repository;

import com.example.taskmanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for User entities.
 * Provides methods to query and manipulate users in the MongoDB database.
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    /**
     * Finds a user by their username.
     * Used during authentication to locate user accounts.
     *
     * @param username The username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Checks if a user with the specified username exists.
     * Used during registration to prevent duplicate usernames.
     *
     * @param username The username to check
     * @return true if a user with the username exists, false otherwise
     */
    boolean existsByUsername(String username);
}