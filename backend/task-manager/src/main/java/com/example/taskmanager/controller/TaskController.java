package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST controller for task management endpoints.
 * Provides CRUD operations for tasks with user-specific access control.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    /**
     * Constructs TaskController with required task service.
     *
     * @param taskService Service handling task business logic
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Retrieves all tasks for the authenticated user.
     *
     * @param auth Authentication object containing user details
     * @return List of task DTOs belonging to the user
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TaskDTO>> getAllTasks(Authentication auth) {
        String userId = auth.getName();
        logger.info("Fetching tasks for user: {}", userId);
        return ResponseEntity.ok(taskService.getAllTasks(userId));
    }

    /**
     * Retrieves a specific task by ID if it belongs to the authenticated user.
     *
     * @param id Task identifier
     * @param auth Authentication object containing user details
     * @return Task DTO if found and owned by user
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable String id, Authentication auth) {
        String userId = auth.getName();
        logger.info("Fetching task with id: {} for user: {}", id, userId);
        return ResponseEntity.ok(taskService.getTaskById(id, userId));
    }

    /**
     * Creates a new task for the authenticated user.
     *
     * @param createTaskDTO Task creation data transfer object
     * @param auth Authentication object containing user details
     * @return Created task DTO with HTTP 201 status, or error message
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createTask(@Valid @RequestBody CreateTaskDTO createTaskDTO, Authentication auth) {
        try {
            String userId = auth.getName();
            logger.info("Creating task: {} for user: {}", createTaskDTO.getTitle(), userId);
            TaskDTO createdTask = taskService.createTask(createTaskDTO, userId);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating task: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating task: " + e.getMessage());
        }
    }

    /**
     * Updates an existing task if it belongs to the authenticated user.
     *
     * @param id Task identifier
     * @param updateTaskDTO Task update data transfer object
     * @param auth Authentication object containing user details
     * @return Updated task DTO
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String id, @RequestBody UpdateTaskDTO updateTaskDTO, Authentication auth) {
        String userId = auth.getName();
        logger.info("Updating task with id: {} for user: {}", id, userId);
        return ResponseEntity.ok(taskService.updateTask(id, updateTaskDTO, userId));
    }

    /**
     * Deletes a task if it belongs to the authenticated user.
     *
     * @param id Task identifier
     * @param auth Authentication object containing user details
     * @return Empty response with HTTP 204 status
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteTask(@PathVariable String id, Authentication auth) {
        String userId = auth.getName();
        logger.info("Deleting task with id: {} for user: {}", id, userId);
        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves tasks filtered by status for the authenticated user.
     *
     * @param status Task status filter
     * @param auth Authentication object containing user details
     * @return List of filtered task DTOs
     */
    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable Task.Status status, Authentication auth) {
        String userId = auth.getName();
        logger.info("Fetching tasks with status: {} for user: {}", status, userId);
        return ResponseEntity.ok(taskService.getTasksByStatus(status, userId));
    }
}