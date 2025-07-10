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
      width: '350px',
      panelClass: 'glassmorphism-dialog', // Add custom panel class
      backdropClass: 'glassmorphism-backdrop'
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
    <div class="dialog-container">
      <h2 mat-dialog-title class="dialog-title">Confirm Logout</h2>
      <mat-dialog-content class="dialog-content">
        Are you sure you want to log out?
      </mat-dialog-content>
      <mat-dialog-actions class="dialog-actions" align="end">
        <div class="button-wrapper">
          <button mat-button mat-dialog-close class="cancel-button">Cancel</button>
        </div>
        <div class="button-wrapper">
          <button mat-raised-button [mat-dialog-close]="true" class="logout-button">Yes, Logout</button>
        </div>
      </mat-dialog-actions>
    </div>
  `,
  styles: [`
    :host {
      display: block;
      background: transparent !important;
    }
    
    .dialog-container {
      background: rgba(30, 30, 30, 0.8) !important;
      backdrop-filter: blur(20px) !important;
      border: 1px solid rgba(255, 255, 255, 0.2) !important;
      border-radius: 16px !important;
      padding: 20px !important;
      color: white !important;
    }

    .dialog-title {
      color: white !important;
      font-weight: 600 !important;
      font-size: 20px !important;
      margin-bottom: 16px !important;
      text-align: center !important;
    }

    .dialog-content {
      color: rgba(255, 255, 255, 0.9) !important;
      font-size: 16px !important;
      text-align: center !important;
      margin-bottom: 20px !important;
    }

    .dialog-actions {
      display: flex !important;
      justify-content: flex-end !important;
      gap: 12px !important;
      margin-top: 20px !important;
      align-items: center !important;
    }

    /* Added button wrapper to control height */
    .button-wrapper {
      height: 40px !important;
      display: flex !important;
      align-items: center !important;
    }

    /* Force both buttons to fill the wrapper exactly */
    .cancel-button,
    .logout-button {
      height: 40px !important;
      line-height: 40px !important;
      padding: 0 20px !important;
      box-sizing: border-box !important;
      font-size: 14px !important;
      font-weight: 500 !important;
      border-radius: 12px !important;
      transition: all 0.3s ease !important;
      margin: 0 !important;
    }

    .cancel-button {
      color: rgba(255, 255, 255, 0.8) !important;
      border: 1px solid rgba(255, 255, 255, 0.3) !important;
      background: transparent !important;
    }

    .cancel-button:hover {
      background: rgba(255, 255, 255, 0.1) !important;
      color: white !important;
    }

    .logout-button {
      background: linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%) !important;
      color: white !important;
      box-shadow: 0 4px 15px rgba(255, 107, 107, 0.3) !important;
      border: none !important;
    }

    .logout-button:hover {
      transform: translateY(-2px) !important;
      box-shadow: 0 6px 20px rgba(255, 107, 107, 0.4) !important;
    }

    /* Override any Material internal padding */
    ::ng-deep .cancel-button .mdc-button__label,
    ::ng-deep .logout-button .mdc-button__label {
      margin: 0 !important;
      padding: 0 !important;
      display: flex !important;
      align-items: center !important;
      justify-content: center !important;
    }

    /* Additional overrides for Material's internals */
    ::ng-deep .cancel-button .mat-mdc-button-touch-target,
    ::ng-deep .logout-button .mat-mdc-button-touch-target {
      height: 40px !important;
    }

    /* Override internal Material flex styles */
    ::ng-deep .cancel-button .mdc-button__ripple,
    ::ng-deep .logout-button .mdc-button__ripple {
      height: 40px !important;
    }

    /* Responsive design */
    @media (max-width: 480px) {
      .dialog-container {
        padding: 16px !important;
      }
      
      .dialog-actions {
        flex-direction: column !important;
        gap: 12px !important;
      }
      
      .button-wrapper {
        width: 100% !important;
      }
      
      .cancel-button,
      .logout-button {
        width: 100% !important;
      }
    }
  `],
  standalone: true,
  imports: [MatDialogModule, MatButtonModule]
})
export class LogoutConfirmationDialog {}