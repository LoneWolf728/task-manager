package com.example.taskmanager.service;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;

import java.util.List;

/**
 * Service interface for task management operations.
 * Provides methods for creating, reading, updating, and deleting tasks.
 */
public interface TaskService {
    /**
     * Retrieves all tasks for a specific user.
     *
     * @param userId The identifier of the user whose tasks to retrieve
     * @return List of task DTOs belonging to the specified user
     */
    List<TaskDTO> getAllTasks(String userId);
    
    /**
     * Retrieves a specific task by ID if it belongs to the specified user.
     *
     * @param id The identifier of the task to retrieve
     * @param userId The identifier of the user who should own the task
     * @return The task DTO if found and owned by the user
     * @throws com.example.taskmanager.exception.ResourceNotFoundException if task not found
     * @throws com.example.taskmanager.exception.UnauthorizedException if task doesn't belong to the user
     */
    TaskDTO getTaskById(String id, String userId);
    
    /**
     * Creates a new task for a specific user.
     *
     * @param createTaskDTO DTO containing task details to create
     * @param userId The identifier of the user who will own the task
     * @return The created task DTO with generated ID
     */
    TaskDTO createTask(CreateTaskDTO createTaskDTO, String userId);
    
    /**
     * Updates an existing task if it belongs to the specified user.
     *
     * @param id The identifier of the task to update
     * @param updateTaskDTO DTO containing task details to update
     * @param userId The identifier of the user who should own the task
     * @return The updated task DTO
     * @throws com.example.taskmanager.exception.ResourceNotFoundException if task not found
     * @throws com.example.taskmanager.exception.UnauthorizedException if task doesn't belong to the user
     */
    TaskDTO updateTask(String id, UpdateTaskDTO updateTaskDTO, String userId);
    
    /**
     * Deletes a task if it belongs to the specified user.
     *
     * @param id The identifier of the task to delete
     * @param userId The identifier of the user who should own the task
     * @throws com.example.taskmanager.exception.ResourceNotFoundException if task not found
     * @throws com.example.taskmanager.exception.UnauthorizedException if task doesn't belong to the user
     */
    void deleteTask(String id, String userId);
    
    /**
     * Retrieves tasks with a specific status for a specific user.
     *
     * @param status The status to filter tasks by
     * @param userId The identifier of the user whose tasks to retrieve
     * @return List of task DTOs with the specified status belonging to the user
     */
    List<TaskDTO> getTasksByStatus(Task.Status status, String userId);
}