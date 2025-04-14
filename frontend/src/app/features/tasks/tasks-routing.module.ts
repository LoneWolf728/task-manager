import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import { AuthGuard } from '../../core/auth/auth.guard';

/**
 * Routes configuration for the Tasks feature module
 */
const routes: Routes = [
  { path: '', component: TaskListComponent, canActivate: [AuthGuard] },        // Main tasks list view
  { path: 'new', component: TaskFormComponent, canActivate: [AuthGuard] },     // Create new task form
  { path: 'edit/:id', component: TaskFormComponent, canActivate: [AuthGuard] }, // Edit existing task form
  { path: ':id', component: TaskDetailComponent, canActivate: [AuthGuard] }     // View task details
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TasksRoutingModule {}