# Task Manager

Task Manager is a full-stack web application designed to help users efficiently create, manage, and track tasks with a clean and intuitive interface. Built with Angular for the frontend and Spring Boot for the backend, it features secure user authentication, task filtering, and input validation, all containerized with Docker for easy deployment. The app uses MongoDB for fast, scalable data storage.

## Features

- **Task Management:**
  - Create, edit, view, and delete tasks
  - Tasks include title (max 25 characters), description (max 500 characters), status, and creation timestamp

- **Status Filtering:**
  - Filter tasks by status: To Do, In Progress, Done, or view all tasks

- **Secure Authentication:**
  - JWT-based authentication for user login and access control

- **Input Validation:**
  - Enforced limits: 25 characters for titles, 500 for descriptions
  - Client-side and server-side validation for data integrity

- **Responsive UI:**
  - Material Design components for a modern, user-friendly experience
  - Status formatting for readable display (e.g., "To Do" instead of "TO_DO")

- **Cross-Origin Support:**
  - Configured CORS for seamless frontend-backend communication

- **Containerized Deployment:**
  - Dockerized frontend, backend, and MongoDB for consistent environments

## Tech Stack

### Frontend:
- Angular 17
- Angular Material
- TypeScript
- RxJS

### Backend:
- Spring Boot 3
- Spring Security (JWT)
- Spring Data MongoDB
- Java 17

### Database:
- MongoDB (configurable)

### DevOps:
- Docker & Docker Compose
- Maven (backend build)
- npm (frontend build)

### Other:
- Git (version control)
- cURL (API testing)

## Prerequisites

- **Docker & Docker Compose:** For containerized deployment
- **Node.js & npm:** For frontend development (optional, if not using Docker)
- **Java 17 & Maven:** For backend development (optional, if not using Docker)
- **Git:** For cloning the repository
- **MongoDB:** If running without Docker (default port 27017)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/LoneWolf728/task-manager.git
cd task-manager
```

### 2. Run with Docker
```bash
docker-compose up --build
```

#### Access:
- **Frontend:** http://localhost:4200
- **Backend API:** http://localhost:8080
- **MongoDB:** localhost:27017 (inside Docker network)

### 3. Run Without Docker (Optional)

#### Backend:
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Ensure MongoDB is running locally (`mongod`).

#### Frontend:
```bash
cd frontend
npm install
ng serve
```

### 4. Test the Application

#### Login:

Register via http://localhost:4200/register or use secure credentials.

API: `POST http://localhost:8080/api/auth/login`

#### Tasks:

- Create a task at http://localhost:4200/tasks/new
- Filter tasks by status
- Edit/delete tasks

#### API Test:
```bash
curl -H "Authorization: Bearer <your-jwt-token>" http://localhost:8080/api/tasks
```

## Project Structure
```
task-manager/
├── backend/
│   ├── src/main/java/com/example/taskmanager/
│   │   ├── config/               # Security configuration
│   │   ├── controller/           # REST API endpoints
│   │   ├── dto/                  # Data transfer objects
│   │   ├── exception/            # Custom exception handlers
│   │   ├── model/                # Domain entities
│   │   ├── repository/           # Data access layer
│   │   ├── security/             # JWT authentication
│   │   ├── service/              # Business logic
│   │   └── util/                 # Helper utilities
│   ├── src/main/resources/
│   │   └── application.properties # Application configuration
│   └── pom.xml                   # Maven dependencies
├── frontend/
│   ├── src/app/
│   │   ├── core/
│   │   │   ├── auth/             # Authentication services & guards
│   │   │   ├── http/             # HTTP interceptors
│   │   │   └── models/           # Data models
│   │   ├── features/
│   │   │   ├── auth/             # Login & registration
│   │   │   └── tasks/            # Task management components
│   │   └── shared/
│   │       ├── components/       # Reusable UI components
│   │       └── pipes/            # Custom pipes
│   ├── src/environments/         # Environment configuration
│   ├── angular.json              # Angular configuration
│   └── package.json              # npm dependencies
├── docker-compose.yml            # Container orchestration
└── README.md                     # Project documentation
```

## API Endpoints

### Authentication:
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Get JWT token

### Tasks:
- `GET /api/tasks` - List all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task
- `GET /api/tasks/status/{status}` - Filter tasks by status

## Security

### JWT Authentication:
- Tokens issued on login, stored in localStorage
- Required for all `/api/tasks` endpoints

### CORS:
- Configured for http://localhost:4200
- Supports credentials for secure API calls

### Validation:
- Title: Max 25 characters, required
- Description: Max 500 characters

## Troubleshooting

### CORS Errors:
- Verify http://localhost:4200 in `CorsConfig.java`
- Check backend logs: `docker-compose logs backend`

### MongoDB Issues:
- Ensure MongoDB is running (`mongod` or Docker)

### JWT Errors:
- Check backend logs for authentication issues

### Frontend Errors:
- Run `npm install` in frontend directory
- Clear browser cache

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -m "Add your feature"`
4. Push to branch: `git push origin feature/your-feature`
5. Open a pull request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

Built with ❤️ using Angular, Spring Boot, MongoDB, and Docker.
Thanks to the open-source community for tools and libraries.