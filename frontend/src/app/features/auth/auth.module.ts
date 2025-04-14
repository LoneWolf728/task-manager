import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
// Angular Material UI components
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
// Feature routing and components
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
// Shared module for common functionality
import { SharedModule } from '../../shared/shared.module';

/**
 * Module for authentication features
 * Includes login and registration functionality
 */
@NgModule({
  declarations: [], // No declarations as components are standalone
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    AuthRoutingModule,
    SharedModule,
    LoginComponent,
    RegisterComponent,
  ],
})
export class AuthModule {}