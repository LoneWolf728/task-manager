/**
 * Interface for JWT authentication response from the server
 */
export interface JwtResponse {
  token: string;     // JWT token for authenticated requests
  username: string;  // Username of the authenticated user
  roles: string[];   // User roles for authorization
}