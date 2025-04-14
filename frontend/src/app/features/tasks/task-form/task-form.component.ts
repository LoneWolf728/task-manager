import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../task.service';
import { Task, TaskStatus } from '../../../core/models/task.model';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { StatusFormatPipe } from '../../../shared/pipes/status-format.pipe';

/**
 * Component for creating and editing tasks
 */
@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.scss'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatSnackBarModule,
    RouterModule,
    StatusFormatPipe
  ],
  standalone: true
})
export class TaskFormComponent implements OnInit {
  taskForm: FormGroup;                  // Form group for task data
  taskStatus = TaskStatus;              // Task status enum for the template
  isEditMode = false;                   // Whether we're editing an existing task
  taskId: string | null = null;         // ID of the task being edited (if any)

  constructor(
    private fb: FormBuilder,
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    // Initialize the form with validators
    this.taskForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(25)]],
      description: ['', [Validators.maxLength(500)]],
      status: [TaskStatus.TO_DO]
    });
  }

  /**
   * Initialize component - check if in edit mode and load task data if so
   */
  ngOnInit(): void {
    this.taskId = this.route.snapshot.paramMap.get('id');
    if (this.taskId) {
      this.isEditMode = true;
      this.taskService.getTaskById(this.taskId).subscribe({
        next: (task) => this.taskForm.patchValue(task),
        error: (err) => {
          console.error('Error loading task:', err);
          this.snackBar.open('Failed to load task', 'Close', { duration: 5000 });
        }
      });
    }
  }

  /**
   * Form submission handler - creates or updates a task
   */
  onSubmit(): void {
    if (this.taskForm.valid) {
      const task: Partial<Task> = this.taskForm.value;
      const operation = this.isEditMode && this.taskId
        ? this.taskService.updateTask(this.taskId, task)
        : this.taskService.createTask(task as Task);

      operation.subscribe({
        next: () => {
          this.snackBar.open(this.isEditMode ? 'Task updated' : 'Task created', 'Close', { duration: 3000 });
          this.router.navigate(['/tasks']);
        },
        error: (err) => {
          console.error('Error saving task:', err);
          const message = err.error?.message || 'Failed to save task';
          this.snackBar.open(message, 'Close', { duration: 5000 });
        }
      });
    }
  }
}