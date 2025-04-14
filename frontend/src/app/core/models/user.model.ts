/**
 * Interface representing a user in the application
 */
export interface User {
  id?: string;         // Optional unique identifier for the user
  username: string;    // Username for authentication and display
  roles?: string[];    // Optional array of roles for user authorization
}