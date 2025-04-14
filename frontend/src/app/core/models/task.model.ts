/**
 * Interface representing a task in the application
 */
export interface Task {
  id?: string;           // Optional unique identifier for the task
  title: string;         // Task title (required)
  description?: string;  // Optional task description
  status: TaskStatus;    // Current status of the task
  createdAt?: string;    // Optional timestamp of task creation
}

/**
 * Enum defining the possible states of a task
 */
export enum TaskStatus {
  TO_DO = 'TO_DO',             // Task not yet started
  IN_PROGRESS = 'IN_PROGRESS', // Task currently being worked on
  DONE = 'DONE',               // Task completed
}