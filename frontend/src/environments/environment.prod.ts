/**
 * Production environment configuration
 * Contains environment-specific variables used throughout the application
 */
export const environment = {
  production: true,
  apiUrl: process.env['NG_APP_API_URL'] || 'https://task-manager-heroku-7528c7041b91.herokuapp.com/'
};
