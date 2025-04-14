import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task, TaskStatus } from '../../../core/models/task.model';
import { TaskService } from '../task.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { DatePipe } from '@angular/common';
import { StatusFormatPipe } from '../../../shared/pipes/status-format.pipe';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

/**
 * Component for displaying and managing the list of tasks
 */
@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatSnackBarModule,
    DatePipe,
    StatusFormatPipe,
    FormsModule
  ]
})
export class TaskListComponent implements OnInit, OnDestroy {
  tasks: Task[] = [];                                      // Collection of tasks to display
  displayedColumns: string[] = ['position', 'title', 'status', 'createdAt', 'actions']; // Columns for the table
  taskStatus = TaskStatus;                                 // Enum made available to template
  selectedStatus: TaskStatus | '' = '';                    // Current status filter selection
  errorMessage: string | null = null;                      // Error message to display
  private destroy$ = new Subject<void>();                  // Subject for handling component destruction

  constructor(
    private taskService: TaskService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  /**
   * Loads tasks from the service, applying status filter if selected
   */
  loadTasks(): void {
    console.log('Loading tasks with status:', this.selectedStatus);
    this.errorMessage = null;
    const observable = this.selectedStatus
      ? this.taskService.getTasksByStatus(this.selectedStatus)
      : this.taskService.getAllTasks();

    observable.pipe(takeUntil(this.destroy$)).subscribe({
      next: (tasks) => {
        console.log('Tasks loaded:', tasks);
        this.tasks = tasks;
      },
      error: (err) => {
        console.error('Error loading tasks:', err);
        this.errorMessage = 'Failed to load tasks. Please try again.';
        this.snackBar.open(this.errorMessage, 'Close', { duration: 5000 });
      }
    });
  }

  /**
   * Applies status filter and reloads tasks
   */
  filterByStatus(status: string): void {
    this.selectedStatus = status as TaskStatus;
    this.loadTasks();
  }

  /**
   * Navigates to task edit page
   */
  editTask(id: string): void {
    if (id) {
      this.router.navigate([`/tasks/edit/${id}`]);
    }
  }

  /**
   * Deletes a task after confirmation
   */
  deleteTask(id: string): void {
    if (id && confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(id).pipe(takeUntil(this.destroy$)).subscribe({
        next: () => {
          console.log('Task deleted:', id);
          this.loadTasks();
          this.snackBar.open('Task deleted successfully', 'Close', { duration: 3000 });
        },
        error: (err) => {
          console.error('Error deleting task:', err);
          this.snackBar.open('Failed to delete task', 'Close', { duration: 5000 });
        }
      });
    }
  }

  /**
   * Navigates to task detail page
   */
  viewTask(id: string): void {
    if (id) {
      this.router.navigate([`/tasks/${id}`]);
    }
  }

  /**
   * Navigates to new task creation page
   */
  navigateToNewTask(): void {
    this.router.navigate(['/tasks/new']);
  }

  /**
   * Returns CSS class based on task status
   */
  getStatusClass(status: string): string {
    switch (status) {
      case TaskStatus.TO_DO:
        return 'status-todo-row';
      case TaskStatus.IN_PROGRESS:
        return 'status-in-progress-row';
      case TaskStatus.DONE:
        return 'status-done-row';
      default:
        return '';
    }
  }

  /**
   * Cleanup resources when component is destroyed
   */
  ngOnDestroy(): void {
    console.log('TaskListComponent destroyed');
    this.destroy$.next();
    this.destroy$.complete();
  }
}