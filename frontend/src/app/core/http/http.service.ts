import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Service providing wrapper methods for HttpClient
 * Centralizes HTTP requests for the application
 */
@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}

  /**
   * Perform HTTP GET request
   * @param url The endpoint URL
   * @returns Observable of the response data
   */
  get<T>(url: string): Observable<T> {
    return this.http.get<T>(url);
  }

  /**
   * Perform HTTP POST request
   * @param url The endpoint URL
   * @param body The request payload
   * @returns Observable of the response data
   */
  post<T>(url: string, body: any): Observable<T> {
    return this.http.post<T>(url, body);
  }

  /**
   * Perform HTTP PUT request
   * @param url The endpoint URL
   * @param body The request payload
   * @returns Observable of the response data
   */
  put<T>(url: string, body: any): Observable<T> {
    return this.http.put<T>(url, body);
  }

  /**
   * Perform HTTP DELETE request
   * @param url The endpoint URL
   * @returns Observable of the response data
   */
  delete<T>(url: string): Observable<T> {
    return this.http.delete<T>(url);
  }
}