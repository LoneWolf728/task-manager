import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// Angular Material UI components
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
// Shared components
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AlertComponent } from './components/alert/alert.component';
// Custom pipes
import { StatusFormatPipe } from './pipes/status-format.pipe';
import { RouterModule } from '@angular/router';

/**
 * Shared module containing common components, pipes and Material modules
 * used throughout the application
 */
@NgModule({
  declarations: [], // No declarations as components are standalone
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatSnackBarModule,
    RouterModule,
    HeaderComponent,
    FooterComponent,
    AlertComponent,
    StatusFormatPipe
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    AlertComponent,
    StatusFormatPipe
  ],
})
export class SharedModule {}