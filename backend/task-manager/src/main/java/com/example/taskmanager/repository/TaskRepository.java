package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for Task entities.
 * Provides methods to query and manipulate tasks in the MongoDB database.
 */
public interface TaskRepository extends MongoRepository<Task, String> {
    
    /**
     * Finds all tasks with the specified status.
     *
     * @param status The task status to filter by
     * @return List of tasks matching the given status
     */
    List<Task> findByStatus(Task.Status status);
    
    /**
     * Finds all tasks with titles containing the specified text (case-insensitive).
     *
     * @param title The text to search for in task titles
     * @return List of tasks with matching titles
     */
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    /**
     * Finds all tasks owned by the specified user.
     *
     * @param userId The identifier of the task owner
     * @return List of tasks belonging to the user
     */
    List<Task> findByUserId(String userId);
    
    /**
     * Finds all tasks owned by the specified user with the specified status.
     *
     * @param userId The identifier of the task owner
     * @param status The task status to filter by
     * @return List of tasks belonging to the user with the matching status
     */
    List<Task> findByUserIdAndStatus(String userId, Task.Status status);
}