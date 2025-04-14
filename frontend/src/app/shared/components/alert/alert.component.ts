import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

/**
 * Component for displaying alert/notification messages
 * Uses Material Snackbar for temporary notifications
 */
@Component({
  selector: 'app-alert',
  template: '', // Empty template as this is a service-like component
})
export class AlertComponent {
  constructor(private snackBar: MatSnackBar) {}

  /**
   * Displays a message in a snackbar
   * @param message The text message to display
   * @param action The action button text
   * @param duration How long to show the message in milliseconds
   */
  showMessage(message: string, action: string = 'Close', duration: number = 3000): void {
    this.snackBar.open(message, action, { duration });
  }
}