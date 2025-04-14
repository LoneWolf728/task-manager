import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { ApplicationConfig } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { TokenInterceptor } from './core/auth/token.interceptor';
import { ErrorInterceptor } from './core/http/error.interceptor';
import { routes } from '../app.routes';


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),                                  // Configure application routing
    provideHttpClient(withInterceptorsFromDi()),            // Configure HTTP client with DI-based interceptors
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },  // Add token handling interceptor
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },  // Add error handling interceptor
    provideAnimations()                                     // Enable animations for the application
  ]
};