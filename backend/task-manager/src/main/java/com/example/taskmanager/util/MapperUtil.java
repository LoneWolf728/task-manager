package com.example.taskmanager.util;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Utility component for mapping between domain entities and DTOs.
 * Centralizes object conversion logic to maintain consistency across the application.
 */
@Component
public class MapperUtil {

    /**
     * Converts a Task entity to a TaskDTO.
     * Used when sending task data to clients.
     *
     * @param task The Task entity to convert
     * @return TaskDTO containing the task data, or null if input is null
     */
    public TaskDTO toTaskDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getUserId(),
                task.getCreatedAt()
        );
    }

    /**
     * Converts a CreateTaskDTO to a Task entity.
     * Used when creating new tasks from client requests.
     * Sets default status to TO_DO if not specified and initializes creation timestamp.
     *
     * @param createTaskDTO The DTO containing task creation data
     * @return Task entity ready for persistence, or null if input is null
     */
    public Task toTask(CreateTaskDTO createTaskDTO) {
        if (createTaskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());
        task.setStatus(createTaskDTO.getStatus() != null ? createTaskDTO.getStatus() : Task.Status.TO_DO);
        task.setCreatedAt(LocalDateTime.now());
        return task;
    }

    /**
     * Converts an UpdateTaskDTO to a Task entity.
     * Used for mapping update requests to entity objects.
     * Note: This creates a new Task with only the fields to be updated.
     *
     * @param updateTaskDTO The DTO containing task update data
     * @return Task entity with updated fields, or null if input is null
     */
    public Task toTask(UpdateTaskDTO updateTaskDTO) {
        if (updateTaskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(updateTaskDTO.getTitle());
        task.setDescription(updateTaskDTO.getDescription());
        task.setStatus(updateTaskDTO.getStatus());
        return task;
    }
}