/**
 * Main entry point for the Angular application
 * This file bootstraps the root component with the application configuration
 */

// Import Angular bootstrapping functionality
import { bootstrapApplication } from '@angular/platform-browser';
// Import the root component of the application
import { AppComponent } from './app/app.component';
// Import the application configuration containing providers and other settings
import { appConfig } from './app/app.module';

// Bootstrap the application with the root component and configuration
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err)); // Log any bootstrap errors to the console