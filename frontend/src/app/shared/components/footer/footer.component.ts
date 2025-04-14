import { Component } from '@angular/core';

/**
 * Footer component that displays at the bottom of the application
 */
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
  standalone: true
})
export class FooterComponent {
  // Current year for copyright notice
  currentYear = new Date().getFullYear();
}