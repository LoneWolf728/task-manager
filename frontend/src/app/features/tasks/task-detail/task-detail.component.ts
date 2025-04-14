import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../task.service';
import { Task } from '../../../core/models/task.model';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';
import { StatusFormatPipe } from '../../../shared/pipes/status-format.pipe';

/**
 * Component that displays the detailed view of a task
 */
@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.scss'],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    RouterModule,
    DatePipe,
    StatusFormatPipe
  ],
  standalone: true
})
export class TaskDetailComponent implements OnInit {
  task: Task | null = null;  // Task data to display

  constructor(private route: ActivatedRoute, private taskService: TaskService) {}

  /**
   * Fetch task details from the service when component initializes
   */
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.taskService.getTaskById(id).subscribe({
        next: (task) => (this.task = task),
        error: (err) => console.error(err),
      });
    }
  }
}