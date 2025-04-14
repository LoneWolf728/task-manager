import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/auth/auth.guard';

/**
 * Application route configuration
 */
const routes: Routes = [
  // Default route - redirect to tasks list
  { path: '', redirectTo: '/tasks', pathMatch: 'full' },
  
  // Authentication feature module - lazy loaded
  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.module').then((m) => m.AuthModule),
  },
  
  // Tasks feature module - protected by authentication guard
  {
    path: 'tasks',
    loadChildren: () => import('./features/tasks/tasks.module').then((m) => m.TasksModule),
    canActivate: [AuthGuard], // Requires authentication to access
  },
  
  // Wildcard route - handle unknown paths
  { path: '**', redirectTo: '/tasks' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}