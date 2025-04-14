import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

/**
 * Routes configuration for authentication feature
 */
const routes: Routes = [
  { path: 'login', component: LoginComponent },     // Login page route
  { path: 'register', component: RegisterComponent }, // Registration page route
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Default route redirects to login
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}