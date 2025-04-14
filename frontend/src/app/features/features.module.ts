import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthModule } from './auth/auth.module';
import { TasksModule } from './tasks/tasks.module';

/**
 * Module that consolidates all feature modules
 * Includes authentication and tasks functionality
 */
@NgModule({
  imports: [CommonModule, AuthModule, TasksModule],
})
export class FeaturesModule {}