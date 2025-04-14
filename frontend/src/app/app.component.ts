import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './shared/components/header/header.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { AlertComponent } from './shared/components/alert/alert.component';

/**
 * Root component of the application
 * Serves as the main container for all other components
 */
@Component({
  selector: 'app-root',                // HTML selector used to place this component
  templateUrl: './app.component.html', // External HTML template
  styleUrls: ['./app.component.scss'], // Component-specific styles
  imports: [
    RouterModule,                      // For routing functionality
    HeaderComponent,                   // Application header
    FooterComponent,                   // Application footer
    AlertComponent,                    // For displaying alerts/notifications
  ],
  standalone: true                     // Component does not require NgModule
})
export class AppComponent {
  title = 'Task Manager';              // Application title
}