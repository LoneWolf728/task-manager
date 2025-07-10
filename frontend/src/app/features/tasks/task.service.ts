import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Task, TaskStatus } from '../../core/models/task.model';

/**
 * Service for managing task data with the API
 */
@Injectable({
  providedIn: 'root',
})
export class TaskService {
  // Update the base API URL to include the correct path
  private apiUrl = `${environment.apiUrl}/api/tasks`;

  constructor(private http: HttpClient) {}

  /**
   * Retrieves all tasks from the API
   */
  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }

  /**
   * Retrieves a specific task by its ID
   * @param id The unique identifier of the task
   */
  getTaskById(id: string): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

  /**
   * Creates a new task
   * @param task The task data to create
   */
  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task, { headers: this.getAuthHeaders() });
  }

  /**
   * Updates an existing task
   * @param id The unique identifier of the task to update
   * @param task The partial task data to update
   */
  updateTask(id: string, task: Partial<Task>): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/${id}`, task, { headers: this.getAuthHeaders() });
  }

  /**
   * Deletes a task by its ID
   * @param id The unique identifier of the task to delete
   */
  deleteTask(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

  /**
   * Retrieves tasks filtered by status
   * @param status The status to filter tasks by
   */
  getTasksByStatus(status: TaskStatus): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/status/${status}`, { headers: this.getAuthHeaders() });
  }

  /**
   * Creates HTTP headers with authentication token
   */
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      'Authorization': `Bearer ${token || ''}`,
      'Content-Type': 'application/json'
    });
  }
}