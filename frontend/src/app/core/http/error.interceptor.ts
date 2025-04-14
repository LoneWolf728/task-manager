import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

/**
 * HTTP interceptor that handles error responses globally
 */
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService) {}

  /**
   * Intercepts HTTP requests and handles error responses
   * @param request The outgoing HTTP request
   * @param next The next interceptor in the chain
   * @returns An observable of the HTTP event stream
   */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Handle unauthorized errors by logging out and redirecting to login
        if (error.status === 401) {
          this.authService.logout();
          this.router.navigate(['/auth/login']);
        }
        // Extract error message from response or use default
        const errorMessage = error.error?.error || 'An error occurred';
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}