package com.example.taskmanager.dto;

import com.example.taskmanager.model.Task;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for updating task information.
 * Contains the fields that can be modified during a task update operation.
 */
public class UpdateTaskDTO {
    /**
     * Updated title of the task.
     * Optional field that must not exceed 25 characters if provided.
     */
    @Size(max = 25, message = "Title cannot exceed 25 characters")
    private String title;

    /**
     * Updated description of the task.
     * Optional field that must not exceed 500 characters if provided.
     */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    /**
     * Updated status of the task.
     * Can be TO_DO, IN_PROGRESS, or DONE.
     */
    private Task.Status status;

    /**
     * Gets the task title.
     *
     * @return The updated title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title.
     *
     * @param title The title to update
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the task description.
     *
     * @return The updated description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task description.
     *
     * @param description The description to update
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the task status.
     *
     * @return The updated status of the task
     */
    public Task.Status getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     *
     * @param status The status to update
     */
    public void setStatus(Task.Status status) {
        this.status = status;
    }
}