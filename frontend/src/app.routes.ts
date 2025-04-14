import { Routes } from '@angular/router';

/**
 * Application route configuration
 * Defines the main navigation paths and module loading strategy
 */
export const routes: Routes = [
  
  {
    path: 'tasks',
    // Lazy loads the tasks module to improve initial load performance
    loadChildren: () => import('app/features/tasks/tasks-routing.module')
      .then(m => m.TasksRoutingModule)
  },
  {
    path: 'auth',
    // Lazy loads the authentication module when requested
    loadChildren: () => import('app/features/auth/auth-routing.module')
      .then(m => m.AuthRoutingModule)
  },
  {
    path: '',
    // Redirects empty path to the tasks feature module
    // This serves as the default landing page
    redirectTo: 'tasks',
    pathMatch: 'full'
  }
];