package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST controller for direct task endpoints (without /api prefix).
 * Handles frontend calls to /tasks/** endpoints.
 */
@RestController
@RequestMapping("/tasks")
public class TaskControllerDirect {

    private final TaskService taskService;

    public TaskControllerDirect(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get all tasks for the authenticated user.
     */
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam(required = false) String status,
            Authentication authentication) {
        try {
            if (status != null && !status.isEmpty()) {
                // Filter by status if provided
                Task.Status taskStatus = Task.Status.valueOf(status.toUpperCase());
                List<TaskDTO> tasks = taskService.getTasksByStatus(taskStatus, authentication.getName());
                return ResponseEntity.ok(tasks);
            } else {
                // Get all tasks
                List<TaskDTO> tasks = taskService.getAllTasks(authentication.getName());
                return ResponseEntity.ok(tasks);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get a specific task by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(
            @PathVariable String id, 
            Authentication authentication) {
        try {
            TaskDTO task = taskService.getTaskById(id, authentication.getName());
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new task.
     */
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(
            @Valid @RequestBody CreateTaskDTO createTaskDTO,
            Authentication authentication) {
        try {
            TaskDTO task = taskService.createTask(createTaskDTO, authentication.getName());
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing task.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable String id,
            @Valid @RequestBody UpdateTaskDTO updateTaskDTO,
            Authentication authentication) {
        try {
            TaskDTO task = taskService.updateTask(id, updateTaskDTO, authentication.getName());
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a task.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable String id,
            Authentication authentication) {
        try {
            taskService.deleteTask(id, authentication.getName());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}