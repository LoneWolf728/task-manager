import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LoginRequest } from '../models/login-request.model';
import { JwtResponse } from '../models/jwt-response.model';
import { RegisterRequest } from '../models/register-request.model';

/**
 * Service for handling authentication-related operations
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}

  /**
   * Authenticates a user and stores the JWT token
   * @param loginRequest User credentials
   * @returns Observable with JWT response
   */
  login(loginRequest: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/login`, loginRequest).pipe(
      tap((response) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('username', response.username);
        // localStorage.setItem('roles', JSON.stringify(response.roles));
      })
    );
  }

  /**
   * Registers a new user
   * @param registerRequest User registration data
   * @returns Observable that completes when registration is successful
   */
  register(registerRequest: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, registerRequest);
  }

  /**
   * Logs out the current user by removing stored authentication data
   */
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('roles');
  }

  /**
   * Checks if a user is currently logged in
   * @returns True if the user is logged in, false otherwise
   */
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  /**
   * Retrieves the stored authentication token
   * @returns The JWT token or null if not logged in
   */
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  /**
   * Gets the username of the currently logged in user
   * @returns The username or null if not logged in
   */
  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  /**
   * Gets the roles of the currently logged in user
   * @returns Array of role strings or empty array if no roles found
   */
  getRoles(): string[] {
    const roles = localStorage.getItem('roles');
    return roles ? JSON.parse(roles) : [];
  }
}