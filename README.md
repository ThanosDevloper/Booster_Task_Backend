# Booster Tasks - Backend API

This is the backend REST API for the Booster Tasks application. It is built using Java 17 and Spring Boot.

## Features
- **RESTful Endpoints**: Full CRUD capabilities for task management.
- **In-Memory Storage**: Thread-safe in-memory task collection utilizing `CopyOnWriteArrayList` and `AtomicLong` for ID generation (no external database configuration needed).
- **Validation**: Request body input validation (Task Name is mandatory).
- **CORS Enabled**: Configured to allow frontend requests from local development servers.
- **Unit & Integration Tests**: Built-in test suite for controller and service layers using JUnit 5 and MockMvc.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven (or use the included Maven wrapper `./mvnw`)

### Running the Application
To start the backend development server on port `8080`, run:
```bash
./mvnw spring-boot:run
```

### Running Tests
To execute the automated unit and integration tests:
```bash
./mvnw clean test
```

## API Endpoints

- `GET /api/tasks` - Get all tasks.
- `POST /api/tasks` - Create a new task (Validates name is not empty).
- `PUT /api/tasks/{id}/status` - Update task status (JSON body: `{"status": "COMPLETED" | "PENDING"}`).
- `DELETE /api/tasks/{id}` - Delete task by ID.
