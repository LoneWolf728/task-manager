package com.example.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Represents a task entity in the task management system.
 * Tasks are the core domain objects representing work items assigned to users.
 */
@Document(collection = "tasks")
public class Task {

    /**
     * Unique identifier for the task.
     */
    @Id
    private String id;

    /**
     * The title or name of the task.
     * Required field that cannot be blank.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * Detailed description of the task.
     * Optional field providing additional information.
     */
    private String description;

    /**
     * Current status of the task in its workflow.
     * @see Status
     */
    private Status status;

    /**
     * Identifier of the user who owns this task.
     * Establishes ownership relationship between users and tasks.
     */
    private String userId;

    /**
     * Timestamp when the task was created.
     * Automatically set during task creation.
     */
    private LocalDateTime createdAt;

    /**
     * Enumeration of possible task statuses representing the workflow states.
     */
    public enum Status {
        /**
         * Initial state for new tasks that haven't been started.
         */
        TO_DO,
        
        /**
         * State for tasks that are currently being worked on.
         */
        IN_PROGRESS,
        
        /**
         * Final state for completed tasks.
         */
        DONE
    }

    /**
     * Default constructor that initializes a new task with default values.
     * Sets creation time to current time and status to TO_DO.
     */
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.status = Status.TO_DO;
    }

    /**
     * Creates a new task with specified properties.
     *
     * @param title The title or name of the task
     * @param description The detailed description of the task
     * @param status The initial status of the task (defaults to TO_DO if null)
     * @param userId The identifier of the user who owns the task
     */
    public Task(String title, String description, Status status, String userId) {
        this.title = title;
        this.description = description;
        this.status = status != null ? status : Status.TO_DO;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
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
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     *
     * @param status The status to set
     */
    public void setStatus(Status status) {
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