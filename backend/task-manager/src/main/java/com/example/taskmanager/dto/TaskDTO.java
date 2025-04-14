package com.example.taskmanager.dto;

import com.example.taskmanager.model.Task;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for task information.
 * Used to transfer task data between layers without exposing the internal model.
 */
public class TaskDTO {
    /**
     * Unique identifier of the task.
     */
    private String id;
    
    /**
     * Title/name of the task.
     */
    private String title;
    
    /**
     * Detailed description of the task.
     */
    private String description;
    
    /**
     * Current status of the task (TO_DO, IN_PROGRESS, DONE).
     */
    private Task.Status status;
    
    /**
     * Identifier of the user who owns the task.
     */
    private String userId;
    
    /**
     * Timestamp when the task was created.
     */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public TaskDTO() {
    }

    /**
     * Creates a task DTO with all properties.
     *
     * @param id Unique identifier of the task
     * @param title Title of the task
     * @param description Detailed description of the task
     * @param status Current status of the task
     * @param userId Identifier of the task owner
     * @param createdAt Timestamp when the task was created
     */
    public TaskDTO(String id, String title, String description, Task.Status status, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    /**
     * Gets the task identifier.
     *
     * @return The unique identifier of the task
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the task identifier.
     *
     * @param id The unique identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the task title.
     *
     * @return The title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title.
     *
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the task description.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task description.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the task status.
     *
     * @return The current status of the task
     */
    public Task.Status getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     *
     * @param status The status to set
     */
    public void setStatus(Task.Status status) {
        this.status = status;
    }

    /**
     * Gets the user identifier.
     *
     * @return The identifier of the task owner
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user identifier.
     *
     * @param userId The user identifier to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The timestamp when the task was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}