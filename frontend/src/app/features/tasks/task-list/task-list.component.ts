import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task, TaskStatus } from '../../../core/models/task.model';
import { TaskService } from '../task.service';
import { Router, RouterModule } from '@angular/router'; // Add RouterModule import
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
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

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
    RouterModule, // Add RouterModule to the imports
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatSnackBarModule,
    DatePipe,
    StatusFormatPipe,
    FormsModule,
    MatDialogModule // Make sure this is included
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
    private snackBar: MatSnackBar,
    private dialog: MatDialog // Add MatDialog
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
   * Opens delete confirmation dialog and deletes task if confirmed
   * @param taskId The ID of the task to delete
   */
  deleteTask(taskId: string): void {
    // Open dialog instead of using window.confirm
    const dialogRef = this.dialog.open(DeleteConfirmationDialog, {
      width: '350px',
      panelClass: 'glassmorphism-dialog',
      backdropClass: 'glassmorphism-backdrop',
      data: { taskId },
      // Ensure focus is properly managed
      autoFocus: true,
      restoreFocus: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.taskService.deleteTask(taskId).subscribe({
          next: () => {
            // Show success message
            this.snackBar.open('Task deleted successfully', 'Dismiss', { 
              duration: 3000,
              panelClass: ['success-snackbar']
            });
            // Refresh the task list after deletion
            this.loadTasks();
          },
          error: (error) => {
            this.errorMessage = 'Failed to delete task. ' + error.message;
            this.snackBar.open(this.errorMessage, 'Close', { 
              duration: 5000,
              panelClass: ['error-snackbar']
            });
          }
        });
      }
    });
  }

  /**
   * Navigates to task detail page
   */
  viewTask(id: string): void {
    if (id) {
      this.router.navigate([`/tasks/view/${id}`]);  // Add 'view' to the path
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

/**
 * Dialog component for delete confirmation
 */
@Component({
  selector: 'delete-confirmation-dialog',
  template: `
    <div class="dialog-container" role="dialog" aria-labelledby="delete-dialog-title">
      <h2 mat-dialog-title class="dialog-title" id="delete-dialog-title">Confirm Delete</h2>
      <mat-dialog-content class="dialog-content">
        Are you sure you want to delete this task?
      </mat-dialog-content>
      <mat-dialog-actions class="dialog-actions" align="end">
        <div class="button-wrapper">
          <button mat-button mat-dialog-close cdkFocusInitial class="cancel-button">Cancel</button>
        </div>
        <div class="button-wrapper">
          <button mat-raised-button [mat-dialog-close]="true" class="delete-button">Delete</button>
        </div>
      </mat-dialog-actions>
    </div>
  `,
  styles: [`
    :host {
      display: block;
      background: transparent !important;
    }
    
    .dialog-container {
      background: rgba(30, 30, 30, 0.8) !important;
      backdrop-filter: blur(20px) !important;
      border: 1px solid rgba(255, 255, 255, 0.2) !important;
      border-radius: 16px !important;
      padding: 20px !important;
      color: white !important;
    }

    .dialog-title {
      color: white !important;
      font-weight: 600 !important;
      font-size: 20px !important;
      margin-bottom: 16px !important;
      text-align: center !important;
    }

    .dialog-content {
      color: rgba(255, 255, 255, 0.9) !important;
      font-size: 16px !important;
      text-align: center !important;
      margin-bottom: 20px !important;
    }

    .dialog-actions {
      display: flex !important;
      justify-content: flex-end !important;
      gap: 12px !important;
      margin-top: 20px !important;
      align-items: center !important;
    }

    /* Button wrapper to control height */
    .button-wrapper {
      height: 40px !important;
      display: flex !important;
      align-items: center !important;
    }

    /* Force both buttons to fill the wrapper exactly */
    .cancel-button,
    .delete-button {
      height: 40px !important;
      line-height: 40px !important;
      padding: 0 20px !important;
      box-sizing: border-box !important;
      font-size: 14px !important;
      font-weight: 500 !important;
      border-radius: 12px !important;
      transition: all 0.3s ease !important;
      margin: 0 !important;
    }

    .cancel-button {
      color: rgba(255, 255, 255, 0.8) !important;
      border: 1px solid rgba(255, 255, 255, 0.3) !important;
      background: transparent !important;
    }

    .cancel-button:hover {
      background: rgba(255, 255, 255, 0.1) !important;
      color: white !important;
    }

    .delete-button {
      background: linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%) !important;
      color: white !important;
      box-shadow: 0 4px 15px rgba(255, 107, 107, 0.3) !important;
      border: none !important;
    }

    .delete-button:hover {
      transform: translateY(-2px) !important;
      box-shadow: 0 6px 20px rgba(255, 107, 107, 0.4) !important;
    }

    /* Override any Material internal padding */
    ::ng-deep .cancel-button .mdc-button__label,
    ::ng-deep .delete-button .mdc-button__label {
      margin: 0 !important;
      padding: 0 !important;
      display: flex !important;
      align-items: center !important;
      justify-content: center !important;
    }

    /* Additional overrides for Material's internals */
    ::ng-deep .cancel-button .mat-mdc-button-touch-target,
    ::ng-deep .delete-button .mat-mdc-button-touch-target {
      height: 40px !important;
    }

    /* Override internal Material flex styles */
    ::ng-deep .cancel-button .mdc-button__ripple,
    ::ng-deep .delete-button .mdc-button__ripple {
      height: 40px !important;
    }

    /* Responsive design */
    @media (max-width: 480px) {
      .dialog-container {
        padding: 16px !important;
      }
      
      .dialog-actions {
        flex-direction: column !important;
        gap: 12px !important;
      }
      
      .button-wrapper {
        width: 100% !important;
      }
      
      .cancel-button,
      .delete-button {
        width: 100% !important;
      }
    }
  `],
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, CommonModule]
})
export class DeleteConfirmationDialog {}