import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../core/auth/auth.service';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

/**
 * Header component for application navigation
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  imports: [CommonModule, RouterModule, MatButtonModule, MatToolbarModule, MatDialogModule],
  standalone: true
})
export class HeaderComponent {
  constructor(
    public authService: AuthService, // Injected as public to use in template
    private router: Router,
    private dialog: MatDialog
  ) {}

  /**
   * Handles user logout with confirmation dialog
   */
  logout(): void {
    const dialogRef = this.dialog.open(LogoutConfirmationDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authService.logout();
        this.router.navigate(['/auth/login']);
      }
    });
  }
}

/**
 * Dialog component for logout confirmation
 */
@Component({
  selector: 'logout-confirmation-dialog',
  template: `
    <h2 mat-dialog-title>Confirm Logout</h2>
    <mat-dialog-content>Are you sure you want to log out?</mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close class="cancel-button">Cancel</button>
      <button mat-raised-button [mat-dialog-close]="true" color="primary" class="logout-button">Yes, Logout</button>
    </mat-dialog-actions>
  `,
  styles: [`
    .logout-button {
      color: white;
      background-color: #3f51b5;
    }
    .cancel-button {
      color: rgba(0, 0, 0, 0.87);
      background-color: #e0e0e0;
    }
  `],
  standalone: true,
  imports: [MatDialogModule, MatButtonModule]
})
export class LogoutConfirmationDialog {}