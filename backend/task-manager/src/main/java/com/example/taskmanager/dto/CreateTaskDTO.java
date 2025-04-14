package com.example.taskmanager.dto;

import com.example.taskmanager.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for creating a new task.
 * Contains validated fields needed for task creation.
 */
public class CreateTaskDTO {
    /**
     * The title of the task.
     * Required and must not exceed 25 characters.
     */
    @NotBlank(message = "Title is required")
    @Size(max = 25, message = "Title cannot exceed 25 characters")
    private String title;

    /**
     * The detailed description of the task.
     * Optional but cannot exceed 500 characters if provided.
     */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    /**
     * The current status of the task.
     * Can be TO_DO, IN_PROGRESS, or DONE.
     */
    private Task.Status status;

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
     * @return The status of the task
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
}