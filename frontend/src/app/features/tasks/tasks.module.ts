import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
// Material UI components
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
// Feature modules
import { TasksRoutingModule } from './tasks-routing.module';
// Components
import { TaskListComponent } from './task-list/task-list.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
// Shared module
import { SharedModule } from '../../shared/shared.module';

/**
 * Module for task management features
 */
@NgModule({
  declarations: [], // No declarations as components are standalone
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    TasksRoutingModule,
    SharedModule,
    TaskListComponent,
    TaskFormComponent,
    TaskDetailComponent,
  ],
})
export class TasksModule {}