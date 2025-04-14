package com.example.taskmanager.service;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the TaskService interface.
 * Provides task management operations including CRUD functionality.
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * Logger for recording service operations and errors.
     */
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    
    /**
     * Repository for task data access operations.
     */
    private final TaskRepository taskRepository;
    
    /**
     * Utility for mapping between entity and DTO objects.
     */
    private final MapperUtil mapperUtil;

    /**
     * Constructs the service with required dependencies.
     *
     * @param taskRepository Repository for task database operations
     * @param mapperUtil Utility for entity-DTO conversions
     */
    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieves all tasks for a specific user.
     * Converts task entities to DTOs for client consumption.
     *
     * @param userId The identifier of the user whose tasks to retrieve
     * @return List of task DTOs belonging to the specified user
     */
    @Override
    public List<TaskDTO> getAllTasks(String userId) {
        logger.info("Fetching tasks for user: {}", userId);
        return taskRepository.findByUserId(userId).stream()
                .map(mapperUtil::toTaskDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific task by ID if it belongs to the specified user.
     * Validates that the task exists and belongs to the user.
     *
     * @param id The identifier of the task to retrieve
     * @param userId The identifier of the user who should own the task
     * @return The task DTO if found and owned by the user
     * @throws ResourceNotFoundException if task not found or doesn't belong to the user
     */
    @Override
    public TaskDTO getTaskById(String id, String userId) {
        logger.info("Fetching task with id: {} for user: {}", id, userId);
        Task task = taskRepository.findById(id)
                .filter(t -> t.getUserId().equals(userId)) // Ensure task belongs to the user
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return mapperUtil.toTaskDTO(task);
    }

    /**
     * Creates a new task for a specific user.
     * Maps the DTO to an entity, assigns the user ID, and persists to database.
     *
     * @param createTaskDTO DTO containing task details to create
     * @param userId The identifier of the user who will own the task
     * @return The created task DTO with generated ID
     */
    @Override
    public TaskDTO createTask(CreateTaskDTO createTaskDTO, String userId) {
        try {
            logger.info("Creating task: {} for user: {}", createTaskDTO.getTitle(), userId);
            // Convert DTO to entity
            Task task = mapperUtil.toTask(createTaskDTO);
            task.setUserId(userId);
            // Save to database
            task = taskRepository.save(task);
            logger.info("Task saved with id: {}", task.getId());
            // Convert back to DTO for response
            return mapperUtil.toTaskDTO(task);
        } catch (Exception e) {
            logger.error("Error creating task: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Updates an existing task if it belongs to the specified user.
     * Only updates fields that are provided in the update DTO.
     *
     * @param id The identifier of the task to update
     * @param updateTaskDTO DTO containing task details to update
     * @param userId The identifier of the user who should own the task
     * @return The updated task DTO
     * @throws ResourceNotFoundException if task not found or doesn't belong to the user
     */
    @Override
    public TaskDTO updateTask(String id, UpdateTaskDTO updateTaskDTO, String userId) {
        logger.info("Updating task with id: {} for user: {}", id, userId);
        // Find and verify task belongs to user
        Task task = taskRepository.findById(id)
                .filter(t -> t.getUserId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        // Only update fields that are provided (not null)
        if (updateTaskDTO.getTitle() != null) {
            task.setTitle(updateTaskDTO.getTitle());
        }
        if (updateTaskDTO.getDescription() != null) {
            task.setDescription(updateTaskDTO.getDescription());
        }
        if (updateTaskDTO.getStatus() != null) {
            task.setStatus(updateTaskDTO.getStatus());
        }

        // Save updated task
        task = taskRepository.save(task);
        return mapperUtil.toTaskDTO(task);
    }

    /**
     * Deletes a task if it belongs to the specified user.
     * Verifies task ownership before deletion.
     *
     * @param id The identifier of the task to delete
     * @param userId The identifier of the user who should own the task
     * @throws ResourceNotFoundException if task not found or doesn't belong to the user
     */
    @Override
    public void deleteTask(String id, String userId) {
        logger.info("Deleting task with id: {} for user: {}", id, userId);
        // Verify task exists and belongs to user before deletion
        taskRepository.findById(id)
                .filter(t -> t.getUserId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        // Delete the task
        taskRepository.deleteById(id);
    }

    /**
     * Retrieves tasks with a specific status for a specific user.
     * Allows filtering tasks by their current workflow state.
     *
     * @param status The status to filter tasks by
     * @param userId The identifier of the user whose tasks to retrieve
     * @return List of task DTOs with the specified status belonging to the user
     */
    @Override
    public List<TaskDTO> getTasksByStatus(Task.Status status, String userId) {
        logger.info("Fetching tasks with status: {} for user: {}", status, userId);
        return taskRepository.findByUserIdAndStatus(userId, status).stream()
                .map(mapperUtil::toTaskDTO)
                .collect(Collectors.toList());
    }
}